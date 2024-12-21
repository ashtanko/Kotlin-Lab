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
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GitHubServiceTest {

    private val mockWebServer = MockWebServer()

    @BeforeEach
    @Throws(IOException::class)
    fun setUp() {
        mockWebServer.start()
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
    fun `create retrofit test`() {
        val retrofit = createRetrofit(mockHttpClient, "https://api.myservice.com/".toHttpUrl())
        assertThat(retrofit.baseUrl().toString()).isEqualTo("https://api.myservice.com/")
    }

    private val mockHttpClient = createHttpClient("test")
}
