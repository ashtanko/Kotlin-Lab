package dev.shtanko.http

import io.mockk.Called
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlin.test.assertEquals
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DbHttpLoggerTest {

    private lateinit var repository: HttpLogRepository
    private lateinit var logger: DbHttpLogger

    @BeforeEach
    fun setUp() {
        repository = mockk(relaxed = true) // relaxed to avoid having to stub every call
        logger = DbHttpLogger(repository)
    }

    @Test
    fun `logResponse should insert log entry into repository`() {
        // Given
        val request = Request.Builder()
            .url("https://example.com/api")
            .get()
            .header("User-Agent", "JUnit-Test")
            .build()

        val response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .header("Content-Type", "application/json")
            .build()

        val tookMs = 123.45

        // When
        logger.logResponse(response, tookMs)

        // Then
        val slot = slot<HttpLogEntry>()
        verify(exactly = 1) { repository.insert(capture(slot)) }

        val loggedEntry = slot.captured
        assertEquals("GET", loggedEntry.method)
        assertEquals("https://example.com/api", loggedEntry.url)
        assertEquals(200, loggedEntry.responseCode)
        assertEquals(true, loggedEntry.requestHeaders.contains("User-Agent"))
        assertEquals(true, loggedEntry.responseHeaders.contains("Content-Type"))
        assertEquals(tookMs, loggedEntry.durationMs)
    }

    @Test
    fun `logRequest should do nothing`() {
        // Given
        val request = Request.Builder()
            .url("https://example.com/only-request")
            .get()
            .build()

        // When
        logger.logRequest(request)

        // Then
        verify { repository wasNot Called }
    }
}
