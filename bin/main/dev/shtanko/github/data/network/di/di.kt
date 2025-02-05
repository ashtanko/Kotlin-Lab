/*
 * Copyright 2025 Oleksii Shtanko
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

@file:Suppress("MagicNumber")
package dev.shtanko.github.data.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dev.shtanko.github.data.network.service.SearchService
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

fun providesNetworkJson(): Lazy<Json> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
}

fun provideHttpLoggingInterceptor(): Lazy<Interceptor> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
        redactHeader("Authorization")
    }
}

fun provideOkHttp(
    httpLoggingInterceptor: Interceptor,
): Lazy<OkHttpClient> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    OkHttpClient.Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .build()
}

private const val BASE_URL = "https://api.github.com/"
private const val JSON_CONTENT_TYPE = "application/json"

fun provideRetrofit(
    networkJson: Json = providesNetworkJson().value,
    okHttp: OkHttpClient = provideOkHttp(provideHttpLoggingInterceptor().value).value,
): Lazy<Retrofit> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    val contentType = JSON_CONTENT_TYPE.toMediaType()
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttp)
        .addConverterFactory(networkJson.asConverterFactory(contentType))
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
}

fun provideSearchService(
    networkJson: Json = providesNetworkJson().value,
    okHttp: OkHttpClient = provideOkHttp(provideHttpLoggingInterceptor().value).value,
    baseUrl: String = BASE_URL,
    defaultRetrofit: Retrofit? = provideRetrofit().value,
): Lazy<SearchService> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    createService(
        networkJson = networkJson,
        okHttp = okHttp,
        baseUrl = baseUrl,
        retrofit = defaultRetrofit,
    )
}

private inline fun <reified T> createService(
    networkJson: Json,
    okHttp: OkHttpClient,
    retrofit: Retrofit?,
    baseUrl: String = BASE_URL,
): T {
    val contentType = JSON_CONTENT_TYPE.toMediaType()
    return if (retrofit != null) {
        retrofit.create(T::class.java)
    } else {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(networkJson.asConverterFactory(contentType))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(T::class.java)
    }
}
