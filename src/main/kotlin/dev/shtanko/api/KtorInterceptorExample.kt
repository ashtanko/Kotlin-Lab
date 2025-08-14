package dev.shtanko.api

import dev.shtanko.api.ktor.ApiKeyInterceptor
import dev.shtanko.api.ktor.HttpLogInterceptor
import dev.shtanko.api.ktor.RequestResponseProcessorInterceptor
import dev.shtanko.api.ktor.RetryInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import java.time.Instant
import kotlinx.coroutines.runBlocking

@Suppress("MagicNumber", "TooGenericExceptionCaught")
fun main() = runBlocking {
    val client = HttpClient(CIO) {
        // Install built-in logging
        install(Logging) {
            level = LogLevel.BODY
        }

        // Install custom interceptors
        install(HttpLogInterceptor) {
            logRequest = true
            logResponse = true
            logHeaders = true
        }

        install(ApiKeyInterceptor) {
            apiKey = "your-api-key-here"
            headerName = "Authorization"
        }

        install(RetryInterceptor) {
            maxRetries = 2
            baseDelayMs = 500
            retryOn = setOf(HttpStatusCode.InternalServerError, HttpStatusCode.ServiceUnavailable)
        }

        install(RequestResponseProcessorInterceptor) {
            requestProcessor = { request ->
                // Add timestamp to all requests
                request.headers.append("X-Request-Time", Instant.now().toString())
                request.headers.append("X-Client-Version", "1.0.0")
            }

            responseProcessor = { response ->
                // Process response (logging, metrics, etc.)
                println("📋 Processing response from: ${response.request.url}")
                println("📊 Response size: ${response.headers[HttpHeaders.ContentLength] ?: "unknown"}")
            }
        }
    }

    try {
        // Test the interceptors
        val response1 = client.get("https://httpbin.org/get")
        println("Response 1 status: ${response1.status}")
        println("Response 1 body: ${response1.bodyAsText()}")

        // Test with a endpoint that might fail (for retry testing)
        val response2 = client.get("https://httpbin.org/status/500")
        println("Response 2 status: ${response2.status}")

    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        client.close()
    }
}
