/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.concurrency.coroutines

import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Test

class WithTimeoutTest {

//    @Test
//    fun failedTest() = runTest {
//        withTimeout(1000) { // immediately times out
//            apiCall()
//        }
//    }

    @Test
    fun successTest() = runTest(timeout = 2.seconds) {
        withContext(Dispatchers.Default) {
            withTimeout(1000) { // immediately times out
                apiCall()
            }
        }
    }

    @Test
    fun testExpensiveFunction() = runTest(timeout = 2.seconds) {
        val result = veryExpensiveFunction() // will take a whole real-time second to execute
        assertEquals(1, result)
    }

    suspend fun apiCall() = withContext(Dispatchers.IO) {
        Thread.sleep(100) // not even 1s
    }

    suspend fun veryExpensiveFunction() = withContext(Dispatchers.Default) {
        delay(1_000)
        1
    }
}
