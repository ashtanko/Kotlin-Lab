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

@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object CoroutineProducer {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val numbers = produceNumbers()

        // Consuming numbers
        repeat(5) {
            println("Received: ${numbers.receive()}")
        }
        numbers.cancel()  // Cancel the producer coroutine
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun CoroutineScope.produceNumbers() = produce {
        for (i in 1..5) {
            delay(200)
            send(i)
        }
    }
}
