package dev.shtanko.api.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ConnectTimeoutException
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class KtorInterceptorsTest {
    @TestFactory
    @DisplayName("Dynamic tests for different HTTP methods")
    fun testHttpMethods() = listOf("GET", "POST", "PUT", "DELETE", "PATCH").map { method ->
        DynamicTest.dynamicTest("Test $method with interceptors") {
            runTest {
                val capturedRequests = mutableListOf<HttpRequestData>()

                val mockEngine = MockEngine { request ->
                    capturedRequests.add(request)
                    respond(
                        content = """{"method": "${request.method.value}"}""",
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }

                val client = HttpClient(mockEngine) {
                    install(HttpLogInterceptor) {
                        logRequest = true
                        logResponse = true
                    }
                    install(ApiKeyInterceptor) {
                        apiKey = "integration-test-key"
                    }
                }

                when (method) {
                    "GET" -> client.get("https://api.test.com/endpoint")
                    "POST" -> client.post("https://api.test.com/endpoint") { setBody("test data") }
                    "PUT" -> client.put("https://api.test.com/endpoint") { setBody("test data") }
                    "DELETE" -> client.delete("https://api.test.com/endpoint")
                    "PATCH" -> client.patch("https://api.test.com/endpoint") { setBody("test data") }
                }

                assertEquals(1, capturedRequests.size)
                assertEquals(method, capturedRequests.first().method.value)
                assertEquals("integration-test-key", capturedRequests.first().headers["X-API-Key"])

                client.close()
            }
        }
    }

    @Test
    @Order(1)
    @DisplayName("Performance test - interceptors should not significantly impact performance")
    @Timeout(5)
    fun testInterceptorPerformance() = runTest {
        val requestCount = 100
        val mockEngine = MockEngine {
            respond("OK", HttpStatusCode.OK)
        }

        val clientWithInterceptors = HttpClient(mockEngine) {
            install(HttpLogInterceptor) {
                logRequest = false // Disable logging for performance test
                logResponse = false
            }
            install(ApiKeyInterceptor) {
                apiKey = "perf-test-key"
            }
            install(RequestResponseProcessorInterceptor) {
                requestProcessor = { request ->
                    request.headers.append("X-Request-ID", System.nanoTime().toString())
                }
                responseProcessor = { /* minimal processing */ }
            }
        }

        val startTime = System.currentTimeMillis()

        repeat(requestCount) {
            clientWithInterceptors.get("https://perf.test.com/api/$it")
        }

        val endTime = System.currentTimeMillis()
        val totalTime = endTime - startTime

        // Should complete 100 requests in reasonable time (less than 5 seconds)
        assertTrue(totalTime < 5000, "Performance test took too long: ${totalTime}ms")

        clientWithInterceptors.close()
    }

    @ParameterizedTest
    @ValueSource(ints = [200, 201, 204, 400, 401, 403, 404, 500, 502, 503])
    @DisplayName("Test interceptors with various HTTP status codes")
    fun testDifferentStatusCodes(statusCode: Int) = runTest {
        val httpStatus = HttpStatusCode.fromValue(statusCode)
        val mockEngine = MockEngine {
            respond("Response for $statusCode", httpStatus)
        }

        val client = HttpClient(mockEngine) {
            install(HttpLogInterceptor)
            install(RetryInterceptor) {
                maxRetries = 1
                retryOn = setOf(
                    HttpStatusCode.InternalServerError,
                    HttpStatusCode.BadGateway,
                    HttpStatusCode.ServiceUnavailable,
                )
            }
        }

        val response = client.get("https://status.test.com/api")
        assertEquals(httpStatus, response.status)

        client.close()
    }

    @Test
    @DisplayName("Test interceptors with concurrent requests")
    fun testConcurrentRequests() = runTest {
        val requestCount = 10
        val processedRequests = mutableListOf<String>()

        val mockEngine = MockEngine { request ->
            respond("Response for ${request.url}", HttpStatusCode.OK)
        }

        val client = HttpClient(mockEngine) {
            install(RequestResponseProcessorInterceptor) {
                requestProcessor = { request ->
                    synchronized(processedRequests) {
                        processedRequests.add(request.url.toString())
                    }
                }
                responseProcessor = { /* no-op */ }
            }
        }

        // Launch concurrent requests
        val jobs = (1..requestCount).map { index ->
            async {
                client.get("https://concurrent.test.com/api/$index")
            }
        }

        // Wait for all requests to complete
        jobs.awaitAll()

        assertEquals(requestCount, processedRequests.size)
        // Verify all unique URLs were processed
        assertEquals(requestCount, processedRequests.toSet().size)

        client.close()
    }

    @Test
    @DisplayName("Test interceptor error handling and recovery")
    fun testErrorHandlingAndRecovery() = runTest {
        var requestAttempts = 0

        val mockEngine = MockEngine { request ->
            requestAttempts++
            when {
                requestAttempts <= 2 -> throw ConnectTimeoutException(request)
                else -> respond("Success after timeout", HttpStatusCode.OK)
            }
        }

        val client = HttpClient(mockEngine) {
            install(RetryInterceptor) {
                maxRetries = 3
                baseDelayMs = 10
            }
            install(RequestResponseProcessorInterceptor) {
                requestProcessor = { request ->
                    // Should continue to work even with retries
                    request.headers.append("X-Attempt", requestAttempts.toString())
                }
                responseProcessor = { /* no-op */ }
            }
        }

        val response = client.get("https://error.test.com/api")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Success after timeout", response.bodyAsText())
        assertEquals(3, requestAttempts) // 1 original + 2 retries

        client.close()
    }
}
