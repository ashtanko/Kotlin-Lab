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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SynchronizedIncrementorTest {

    @Test
    fun `increment counter`() {
        val incrementor = SynchronizedIncrementor()
        incrementor.updateCounter()
        assertEquals(1, incrementor.sharedCounter)
    }

    @Test
    fun `increment counter multiple times`() {
        val incrementor = SynchronizedIncrementor()
        repeat(5) {
            incrementor.updateCounter()
        }
        assertEquals(5, incrementor.sharedCounter)
    }

    @Test
    fun `increment counter concurrently`() = runBlocking {
        val incrementor = SynchronizedIncrementor()
        val scope = CoroutineScope(newFixedThreadPoolContext(4, "testPool"))
        scope.launch {
            val coroutines = 1.rangeTo(1000).map {
                launch {
                    for (i in 1..1000) {
                        incrementor.updateCounter()
                    }
                }
            }
            coroutines.forEach { it.join() }
        }.join()
        assertEquals(1000000, incrementor.sharedCounter)
    }
}
