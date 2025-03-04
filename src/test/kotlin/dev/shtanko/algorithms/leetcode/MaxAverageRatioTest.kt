/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

sealed class MaxAverageRatioTest<out T : MaxAverageRatio>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(3, 5),
                    intArrayOf(2, 2),
                ),
                2,
                0.7833333333333333,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 4),
                    intArrayOf(3, 9),
                    intArrayOf(4, 5),
                    intArrayOf(2, 10),
                ),
                4,
                0.5348484848484849,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `maxAverageRatio test`(classes: Array<IntArray>, extraStudents: Int, expected: Double) {
        val actual = strategy(classes, extraStudents)
        assertThat(actual).isEqualTo(expected)
    }

    data object PriorityQueueTest : MaxAverageRatioTest<MaxAverageRatio>(MaxAverageRatio.PriorityQueueStrategy)
}
