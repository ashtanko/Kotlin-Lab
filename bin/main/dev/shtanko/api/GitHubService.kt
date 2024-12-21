/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.api

import java.lang.String.format
import java.util.Base64
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("orgs/{org}/repos?per_page=100")
    fun getOrgReposCall(
        @Path("org") org: String,
    ): Call<List<Repo>>

    @GET("repos/{owner}/{repo}/contributors?per_page=100")
    fun getRepoContributorsCall(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Call<List<User>>

    @GET("orgs/{org}/repos?per_page=100")
    suspend fun getOrgRepos(
        @Path("org") org: String,
    ): Response<List<Repo>>

    @GET("repos/{owner}/{repo}/contributors?per_page=100")
    suspend fun getRepoContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Response<List<User>>
}

@Serializable
data class Repo(
    val id: Long,
    val name: String,
)

@Serializable
data class User(
    val login: String,
    val contributions: Int,
)

@Serializable
data class RequestData(
    val username: String,
    val password: String,
    val org: String,
)

private val json = Json { ignoreUnknownKeys = true }

private const val BASE_URL = "https://api.github.com/"
private const val JSON_CONTENT_TYPE = "application/json"
private const val AUTH_HEADER = "Authorization"
private const val ACCEPT_HEADER = "Accept"
private const val ACCEPT_HEADER_VALUE = "application/vnd.github.v3+json"
private const val AUTH_BASIC = "Basic %s"

fun createHttpClient(authToken: String): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
                .header(ACCEPT_HEADER, ACCEPT_HEADER_VALUE)
                .header(AUTH_HEADER, authToken)
            val request = builder.build()
            chain.proceed(request)
        }
        .build()
}

@OptIn(ExperimentalSerializationApi::class)
fun createRetrofit(httpClient: OkHttpClient, baseUrl: HttpUrl = BASE_URL.toHttpUrl()): Retrofit {
    val contentType = JSON_CONTENT_TYPE.toMediaType()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .build()
}

fun createGitHubService(
    username: String,
    password: String,
    httpClient: OkHttpClient = createHttpClient(
        format(
            AUTH_BASIC,
            Base64.getEncoder().encode("$username:$password".toByteArray()).toString(Charsets.UTF_8),
        ),
    ),
    retrofit: Retrofit = createRetrofit(httpClient),
): GitHubService {
    return retrofit.create(GitHubService::class.java)
}
