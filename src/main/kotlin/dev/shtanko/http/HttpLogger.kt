package dev.shtanko.http

import java.nio.charset.StandardCharsets
import okhttp3.Request
import okhttp3.Response
import okio.Buffer

interface HttpLogger {
    fun logRequest(request: Request)
    fun logResponse(response: Response, tookMs: Double)
}

class DefaultHttpLogger : HttpLogger {

    override fun logRequest(request: Request) {
        println("----- REQUEST -----")
        println("URL: ${request.url}")
        println("Method: ${request.method}")
        println("Headers:\n${request.headers}")

        request.body?.let { body ->
            val buffer = Buffer()
            body.writeTo(buffer)
            val charset = body.contentType()?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
            println("Body:\n${buffer.readString(charset)}")
        }
    }

    override fun logResponse(response: Response, tookMs: Double) {
        println("----- RESPONSE -----")
        println("Status: ${response.code} ${response.message}")
        println("Received in ${"%.1f".format(tookMs)} ms")
        println("Headers:\n${response.headers}")

        val responseBody = response.body
        val source = responseBody.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer
        val charset = responseBody.contentType()?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
        val bodyString = buffer.clone().readString(charset)
        println("Body:\n$bodyString")
    }
}
