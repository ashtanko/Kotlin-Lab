/*
 * Copyright 2022 Oleksii Shtanko
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

import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class SequenceGeneratorTest : TestBase() {

    @Test
    @Disabled
    @Throws(Exception::class)
    fun givenUnsafeSequenceGenerator_whenRaceCondition_thenUnexpectedBehavior() {
        val count = 1000
        val uniqueSequences = getUniqueSequences(SequenceGenerator(), count)
        assertThat(uniqueSequences.size).isNotEqualTo(count)

        val uniqueSequences2 = getUniqueSequences(SequenceGenerator(), 1)
        assertThat(uniqueSequences2.size).isEqualTo(1)
    }

    @Test
    @Throws(Exception::class)
    fun givenUnsafeSequenceGenerator_whenRaceCondition_thenUnexpectedBehaviorCoroutines() = runTest {
        val generator = SequenceGenerator()
        val actual = (1..100).asFlow().map {
            coroutineScope {
                async { generator.getNextSequence() }
            }
        }.toList().awaitAll().last()
        assertThat(actual).isEqualTo(100)
    }

    @Throws(Exception::class)
    private fun getUniqueSequences(generator: SequenceGenerator, count: Int): Set<Int> {
        val executor = Executors.newFixedThreadPool(3)
        val uniqueSequences: MutableSet<Int> = LinkedHashSet()
        val futures: MutableList<Future<Int>> = ArrayList<Future<Int>>()
        for (i in 0 until count) {
            futures.add(executor.submit<Int> { generator.getNextSequence() })
        }
        for (future in futures) {
            uniqueSequences.add(future.get())
        }
        executor.awaitTermination(1, TimeUnit.SECONDS)
        executor.shutdown()
        return uniqueSequences
    }
}
