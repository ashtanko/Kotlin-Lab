/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MeetingSchedulerTest<out T : MeetingScheduler>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(10, 50),
                    intArrayOf(60, 120),
                    intArrayOf(140, 210),
                ),
                arrayOf(
                    intArrayOf(0, 15),
                    intArrayOf(60, 70),
                ),
                8,
                listOf(60, 68),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(10, 50),
                    intArrayOf(60, 120),
                    intArrayOf(140, 210),
                ),
                arrayOf(
                    intArrayOf(0, 15),
                    intArrayOf(60, 70),
                ),
                12,
                listOf<Int>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min available duration test`(
        slots1: Array<IntArray>,
        slots2: Array<IntArray>,
        duration: Int,
        expected: List<Int>,
    ) {
        val actual = strategy.invoke(slots1, slots2, duration)
        assertThat(actual).isEqualTo(expected)
    }
}

class MSTwoPointersTest : MeetingSchedulerTest<MSTwoPointers>(MSTwoPointers())
class MSHeapTest : MeetingSchedulerTest<MSHeap>(MSHeap())
