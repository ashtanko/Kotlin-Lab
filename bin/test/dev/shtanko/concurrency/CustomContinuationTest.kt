/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.concurrency

import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertFailsWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

suspend fun suspendingFunction(): String = suspendCoroutine { continuation ->
    Thread {
        Thread.sleep(1000) // Simulate delay
        continuation.resume("Hello from suspension!") // Resuming the coroutine
    }.start()
}

class CustomContinuationTest {
    @Test
    fun testCustomContinuationResumesWithResult() = runTest {
        val resultValue = "Hello from test!"
        val continuation = Continuation<String>(EmptyCoroutineContext) { result ->
            assertEquals(resultValue, result.getOrThrow())
        }

        val customContinuation = CustomContinuation(continuation)
        customContinuation.resumeWith(Result.success(resultValue))
    }

    @Test
    fun testCustomContinuationHandlesException() = runTest {
        val exception = Exception("Test exception")
        val continuation = Continuation<String>(EmptyCoroutineContext) { result ->
            assertFailsWith<Exception>("Test exception") {
                result.getOrThrow()
            }
        }

        val customContinuation = CustomContinuation(continuation)
        customContinuation.resumeWith(Result.failure(exception))
    }

    @Test
    fun testSuspendingFunction() = runTest {
        val result = withContext(Dispatchers.Default) { suspendingFunction() }
        assertEquals("Hello from suspension!", result)
    }
}
