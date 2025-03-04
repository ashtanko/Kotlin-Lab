/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.github.data.network

import com.skydoves.sandwich.ApiResponse
import dev.shtanko.github.data.model.Order
import dev.shtanko.github.data.model.Sort
import dev.shtanko.github.data.network.di.provideHttpLoggingInterceptor
import dev.shtanko.github.data.network.di.provideOkHttp
import dev.shtanko.github.data.network.di.provideSearchService
import dev.shtanko.github.data.network.service.SearchService
import java.io.IOException
import java.net.HttpURLConnection
import kotlin.test.assertNotNull
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertInstanceOf

class SearchServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var searchService: SearchService
    private val json = Json { ignoreUnknownKeys = true }

    @BeforeEach
    @Throws(IOException::class)
    fun setUp() {
        mockWebServer = MockWebServer()

        searchService = provideSearchService(
            networkJson = json,
            okHttp = mockHttpClient,
            baseUrl = mockWebServer.url("/").toString(),
            defaultRetrofit = null,
        ).value
    }

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `create http client test`() {
        assertNotNull(mockHttpClient)
    }

    @Test
    fun `http client has interceptors test`() {
        assertTrue(mockHttpClient.networkInterceptors.isNotEmpty())
    }

    @Test
    fun `http client has logging interceptors test`() {
        assertTrue(mockHttpClient.networkInterceptors.any { it is HttpLoggingInterceptor })
    }

    @Test
    fun `verify provideSearchService creates instance`() {
        val serviceLazy = provideSearchService(
            networkJson = json,
            okHttp = mockHttpClient,
            baseUrl = mockWebServer.url("/").toString(),
        )

        assertNotNull(serviceLazy)
        assertNotNull(serviceLazy.value)
    }

    @Test
    fun `search repositories - successful response`() = runTest {
        val mockResponse = """
            {
                "total_count": 42,
                "incomplete_results": false,
                "items": [
                    {
                        "id": 1,
                        "name": "Repo1"
                    }
                ]
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(HttpURLConnection.HTTP_OK))

        val result = searchService.search(
            query = "kotlin",
            order = Order.DESC,
            perPage = 10,
            sort = Sort.STARS,
        )
        assertInstanceOf<ApiResponse.Success<*>>(result)
        if (result is ApiResponse.Success) {
            val data = result.data
            assertNotNull(data)
            assertEquals(42, data.totalCount)
            assertEquals(false, data.incompleteResults)
            assertNotNull(data.items)
            assertEquals(1, data.items.size)
            assertEquals(1, data.items.firstOrNull()?.id)
            assertEquals("Repo1", data.items.firstOrNull()?.name)
        }
    }

    @Test
    fun `search repositories - error response`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST))

        val result = searchService.search(
            query = "kotlin",
            order = Order.DESC,
        )

        assertInstanceOf<ApiResponse.Failure<*>>(result)

        assertTrue(
            (result as ApiResponse.Failure<*>).toString().contains(HttpURLConnection.HTTP_BAD_REQUEST.toString()),
        )
    }

    @Test
    fun `search repositories - empty response`() = runTest {
        val mockResponse = """
            {
                "total_count": 0,
                "incomplete_results": false,
                "items": []
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(HttpURLConnection.HTTP_OK))

        val result = searchService.search(
            query = "kotlin",
            order = Order.DESC,
            perPage = 10,
        )

        assertInstanceOf<ApiResponse.Success<*>>(result)
        if (result is ApiResponse.Success) {
            val data = result.data
            assertNotNull(data)
            assertEquals(0, data.totalCount)
            assertEquals(false, data.incompleteResults)
            assertNotNull(data.items)
            assertEquals(0, data.items.size)
        }
    }

    private val mockHttpClient = provideOkHttp(provideHttpLoggingInterceptor().value).value
}
