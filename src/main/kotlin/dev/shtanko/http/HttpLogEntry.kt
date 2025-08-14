package dev.shtanko.http

import java.time.Instant

data class HttpLogEntry(
    val id: Long? = null,
    val method: String,
    val url: String,
    val requestHeaders: String,
    val responseCode: Int,
    val responseHeaders: String,
    val durationMs: Double,
    val timestamp: Instant,
)
