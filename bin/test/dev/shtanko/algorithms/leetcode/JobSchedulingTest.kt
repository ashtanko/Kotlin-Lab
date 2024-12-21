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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class JobSchedulingTest<out T : JobScheduling>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 3),
                intArrayOf(3, 4, 5, 6),
                intArrayOf(50, 10, 40, 70),
                120,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 6),
                intArrayOf(3, 5, 10, 6, 9),
                intArrayOf(20, 20, 100, 70, 60),
                150,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1),
                intArrayOf(2, 3, 4),
                intArrayOf(5, 6, 4),
                6,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `job scheduling test`(startTime: IntArray, endTime: IntArray, profit: IntArray, expected: Int) {
        val actual = strategy(startTime, endTime, profit)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class JobSchedulingDpTest : JobSchedulingTest<JobScheduling>(JobSchedulingDp())
