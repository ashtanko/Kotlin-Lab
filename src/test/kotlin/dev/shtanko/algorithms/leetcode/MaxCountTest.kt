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

abstract class MaxCountTest<out T : MaxCount>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 6, 5),
                5,
                6,
                2,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                8,
                1,
                0,
            ),
            Arguments.of(
                intArrayOf(11),
                7,
                50,
                7,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `maxCount `(banned: IntArray, n: Int, maxSum: Int, expected: Int) {
        val actual = strategy(banned, n, maxSum)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxCountBinarySearchTest : MaxCountTest<MaxCount>(MaxCount.BinarySearch)
class MaxCountSweepTest : MaxCountTest<MaxCount>(MaxCount.Sweep)
class MaxCountHashSetTest : MaxCountTest<MaxCount>(MaxCount.HashSet)
