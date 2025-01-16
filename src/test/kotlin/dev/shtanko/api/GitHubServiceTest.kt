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

import java.io.IOException
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import retrofit2.Retrofit

class GitHubServiceTest {

    private val mockWebServer = MockWebServer()
    private lateinit var service: GitHubService

    @BeforeEach
    @Throws(IOException::class)
    fun setUp() {
        mockWebServer.start()
        service = mockGitHubService()
    }

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `create http client test`() {
        val httpClient = mockHttpClient
        assertThat(httpClient.interceptors).isNotEmpty
    }

    @Test
    fun `create retrofit default url test`() {
        val retrofit = createRetrofit(mockHttpClient)
        assertThat(retrofit.baseUrl().toString()).isEqualTo("https://api.github.com/")
    }

    @Test
    @Disabled("rework needed")
    fun `create retrofit test`() {
        val retrofit = mockRetrofit()
        assertThat(retrofit.baseUrl().toString()).isEqualTo("https://api.myservice.com/")
    }

    @Test
    fun `createGitHubService should return a GitHubService instance`() {
        val service = mockGitHubService("mock_username", "mock_password")
        assertThat(service).isNotNull
    }

    @Test
    @Disabled("rework needed")
    fun `test getOrgReposCall`() {
        val mockResponse = MockResponse()
            .setBody("[{\"id\": 1, \"name\": \"repo1\"}]")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val response = service.getOrgReposCall("org").execute()
        val repos = response.body()

        assertThat(repos).isNotNull

        //assertEquals(1, repos.size)
        //assertEquals(1L, repos[0].id)
        //assertEquals("repo1", repos[0].name)
    }

    private val mockHttpClient = createHttpClient("test")

    private fun mockRetrofit(baseUrl: String = "https://api.github.com/"): Retrofit {
        return createRetrofit(mockHttpClient, baseUrl.toHttpUrl())
    }

    private fun mockGitHubService(
        username: String = "testuser",
        password: String = "testpass",
        httpClient: OkHttpClient = createHttpClient("Basic dGVzdHVzZXI6dGVzdHBhc3M="),
        retrofit: Retrofit = mockRetrofit(),
    ): GitHubService {
        return createGitHubService(username, password, httpClient, retrofit)
    }
}
