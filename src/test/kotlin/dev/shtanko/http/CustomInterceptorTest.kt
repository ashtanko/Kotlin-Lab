package dev.shtanko.http

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CustomInterceptorTest {

    private lateinit var server: MockWebServer
    private lateinit var client: OkHttpClient
    private lateinit var output: ByteArrayOutputStream

    @BeforeEach
    fun setUp() {
        server = MockWebServer()
        server.start()

        output = ByteArrayOutputStream()
        System.setOut(PrintStream(output))

        client = OkHttpClient.Builder()
            .addInterceptor(CustomInterceptor.create(DefaultHttpLogger()))
            .build()
    }

    @AfterEach
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `logs request without body`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("OK"))

        val request = Request.Builder()
            .url(server.url("/test"))
            .get()
            .build()

        client.newCall(request).execute()

        val logs = output.toString()
        assertTrue(logs.contains("REQUEST"))
        assertTrue(logs.contains("Method: GET"))
        assertTrue(logs.contains("/test"))
        assertTrue(logs.contains("RESPONSE"))
        assertTrue(logs.contains("Status: 200"))
    }

    @Test
    fun `logs request with JSON body`() {
        server.enqueue(MockResponse().setResponseCode(201).setBody("{\"result\":\"created\"}"))

        val jsonBody = "{\"name\":\"Oleksii\"}"
        val request = Request.Builder()
            .url(server.url("/json"))
            .post(RequestBody.create("application/json".toMediaTypeOrNull(), jsonBody))
            .build()

        client.newCall(request).execute()

        val logs = output.toString()
        assertTrue(logs.contains("Body:"))
        assertTrue(logs.contains("Oleksii"))
        assertTrue(logs.contains("\"result\":\"created\""))
    }

    @Test
    fun `logs response with no body`() {
        server.enqueue(MockResponse().setResponseCode(204))

        val request = Request.Builder()
            .url(server.url("/empty"))
            .get()
            .build()

        client.newCall(request).execute()

        val logs = output.toString()
        assertTrue(logs.contains("Status: 204"))
        assertTrue(logs.contains("Body:"))
    }

    @Test
    fun `logs binary response`() {
        val binaryData = byteArrayOf(0x01, 0x02, 0x03)
        val buffer = Buffer().write(binaryData)
        server.enqueue(MockResponse().setBody(buffer))

        val request = Request.Builder()
            .url(server.url("/binary"))
            .get()
            .build()

        client.newCall(request).execute()

        val logs = output.toString()
        assertTrue(logs.contains("RESPONSE"))
        assertTrue(logs.contains("Body:"))
    }

    @Test
    fun `interceptor delegates to logger`() {
        var requestLogged = false
        var responseLogged = false

        val logger = object : HttpLogger {
            override fun logRequest(request: Request) {
                requestLogged = true
            }

            override fun logResponse(response: Response, tookMs: Double) {
                responseLogged = true
            }
        }

        val customClient = OkHttpClient.Builder()
            .addInterceptor(CustomInterceptor.create(logger))
            .build()

        server.enqueue(MockResponse().setBody("Test"))

        val request = Request.Builder()
            .url(server.url("/delegate"))
            .get()
            .build()

        customClient.newCall(request).execute()

        assertTrue(requestLogged)
        assertTrue(responseLogged)
    }
}
