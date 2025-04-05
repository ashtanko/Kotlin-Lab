package dev.shtanko.retry

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dev.shtanko.utils.create
import java.io.IOException
import java.util.concurrent.CountDownLatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.runTest
import okhttp3.ExperimentalOkHttpApi
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.http.GET

class RetryTest {

    private val server: MockWebServer = MockWebServer().apply { start() }

    private val testService: FakeService =
        Retrofit.Builder()
            .baseUrl(server.url("/").toString())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create()

    @AfterEach
    fun after() {
        server.shutdown()
    }

    @Test
    fun `should return result without retry when no exception`() = runTest {
        var counter = 0
        val result = retry(policy = DefaultRetryPolicy()) {
            counter++
            "Success"
        }

        assertEquals("Success", result)
        assertEquals(1, counter, "Block should be called only once without retry")
    }

    @Test
    fun `should succeed after transient failure`() = runTest {
        var counter = 0
        val result = retry(policy = DefaultRetryPolicy(maxAttempts = 3)) {
            if (++counter < 2) throw IOException("Transient failure")
            "Recovered"
        }

        assertEquals("Recovered", result)
        assertEquals(2, counter)
    }

    @Test
    fun `should fail after max retry attempts`() = runTest {
        var counter = 0
        val exception = assertThrows<IOException> {
            retry(policy = DefaultRetryPolicy(maxAttempts = 3)) {
                counter++
                throw IOException("Always failing")
            }
        }

        assertEquals("Always failing", exception.message)
        assertEquals(3, counter, "Should retry 3 times before failing")
    }

    @RepeatedTest(10)
    fun `should handle concurrency and correctly update attempt`() = runTest {
        val latch = CountDownLatch(5)
        val retryAttempts = mutableListOf<Int>()
        val expectedAttempts = 5
        val mutex = Mutex()

        // Launch multiple coroutines to simulate concurrent retries
        val job = CoroutineScope(Dispatchers.Default).launch {
            repeat(expectedAttempts) {
                launch {
                    var attempt = 0
                    try {
                        retry(policy = DefaultRetryPolicy(maxAttempts = 3)) { currentAttempt ->
                            attempt = currentAttempt
                            throw IOException("Simulated failure")
                        }
                    } catch (_: IOException) {
                        mutex.withLock {
                            retryAttempts.add(attempt)
                        }
                    } finally {
                        latch.countDown()
                    }
                }
            }
        }

        latch.await()
        assertEquals(expectedAttempts, retryAttempts.size)
        retryAttempts.forEach { attempt ->
            assertTrue(attempt in 1..3, "Retry attempt should be between 1 and 3")
        }

        job.cancelAndJoin()
    }

    @OptIn(ExperimentalOkHttpApi::class)
    @Test
    fun `should retry on server error using MockWebServer`() = runTest {
        var counter = 0

        server.enqueue(MockResponse().setResponseCode(500))
        server.enqueue(MockResponse().setResponseCode(500))
        server.enqueue(MockResponse().setBody("Success").setResponseCode(200))

        val result = retry(
            policy = DefaultRetryPolicy(maxAttempts = 3),
        ) {
            counter++
            testService.fakeGet()
        }

        assertEquals("Success", result.string())
        assertEquals(3, counter, "The block should be retried 3 times")
    }

    @Test
    fun `should fail after max retries on server error using MockWebServer`() = runTest {
        var counter = 0

        server.enqueue(MockResponse().setResponseCode(500))
        server.enqueue(MockResponse().setResponseCode(500))
        server.enqueue(MockResponse().setResponseCode(500))

        val exception = assertThrows<HttpException> {
            retry(policy = DefaultRetryPolicy(maxAttempts = 3)) {
                counter++
                testService.fakeGet()
            }
        }

        assertEquals("HTTP 500 Server Error", exception.message)
        assertEquals(3, counter, "The block should be retried 3 times before failing")
    }
}

private interface FakeService {
    @GET("/fake")
    suspend fun fakeGet(): ResponseBody
}
