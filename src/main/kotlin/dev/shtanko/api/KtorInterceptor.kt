package dev.shtanko.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

@Suppress("MagicNumber")
fun main() = runBlocking {
    val client = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    client.sendPipeline.intercept(HttpSendPipeline.State) { request ->
        println("Intercepted request to: $request")
        // For example, add a custom header
        context.headers.append("X-Custom-Header", "MyValue")
        proceed()
    }

    client.requestPipeline.intercept(HttpRequestPipeline.Before) {
        println("Sending request to: ${context.url} ${context.method}")
        proceed()
    }

    client.plugin(HttpSend).intercept { request ->
        println("Request URL: ${request.url}")
        println("Request method: ${request.method}")
        println("Request headers: ${request.headers}")
        val originalCall = execute(request)
        if (originalCall.response.status.value !in 100..399) {
            execute(request)
        } else {
            originalCall
        }
    }

    val response = client.get("https://httpbin.org/get")
    println("Response status: ${response.status}")

    client.close()
}
