package dev.shtanko.http

import java.time.Instant
import okhttp3.Request
import okhttp3.Response

class DbHttpLogger(private val repository: HttpLogRepository) : HttpLogger {
    override fun logRequest(request: Request) {
        // We don't insert on request alone — we'll log fully after response
    }

    override fun logResponse(response: Response, tookMs: Double) {
        val request = response.request

        val logEntry = HttpLogEntry(
            method = request.method,
            url = request.url.toString(),
            requestHeaders = request.headers.joinToString("\n") { "${it.first}: ${it.second}" },
            responseCode = response.code,
            responseHeaders = response.headers.joinToString("\n") { "${it.first}: ${it.second}" },
            durationMs = tookMs,
            timestamp = Instant.now(),
        )

        repository.insert(logEntry)
    }
}
