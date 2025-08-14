package dev.shtanko.http

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets
import kotlin.test.assertTrue
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultHttpLoggerTest {

    private lateinit var logger: DefaultHttpLogger
    private lateinit var outputStream: ByteArrayOutputStream
    private lateinit var originalOut: PrintStream

    @BeforeEach
    fun setUp() {
        logger = DefaultHttpLogger()
        outputStream = ByteArrayOutputStream()
        originalOut = System.out
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
    }

    @Test
    fun `logRequest prints request details`() {
        val request = Request.Builder()
            .url("https://example.com/test")
            .method("POST", "hello".toRequestBody("text/plain".toMediaType()))
            .addHeader("X-Test", "true")
            .build()

        logger.logRequest(request)

        val output = outputStream.toString(StandardCharsets.UTF_8)
        assertTrue(output.contains("REQUEST"))
        assertTrue(output.contains("https://example.com/test"))
        assertTrue(output.contains("POST"))
        assertTrue(output.contains("X-Test: true"))
        assertTrue(output.contains("hello"))
    }

    @Test
    fun `logResponse prints response details`() {
        val request = Request.Builder()
            .url("https://example.com/test")
            .get()
            .build()

        val body = "response body".toResponseBody("text/plain".toMediaType())
        val response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(body)
            .build()

        logger.logResponse(response, 123.4)

        val output = outputStream.toString(StandardCharsets.UTF_8)
        assertTrue(output.contains("RESPONSE"))
        assertTrue(output.contains("200 OK"))
        assertTrue(output.contains("123.4 ms"))
        assertTrue(output.contains("response body"))
    }
}
