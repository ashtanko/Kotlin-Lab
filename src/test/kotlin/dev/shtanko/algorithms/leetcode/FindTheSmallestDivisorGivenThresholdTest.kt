/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SmallestDivisorStrategyTest<out T : SmallestDivisorStrategy>(private val strategy: T) {
    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 5, 9),
                6,
                5,
            ),
            Arguments.of(
                intArrayOf(2, 3, 5, 7, 11),
                11,
                3,
            ),
            Arguments.of(
                intArrayOf(19),
                5,
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find the smallest divisor given a threshold test`(nums: IntArray, threshold: Int, expected: Int) {
        val actual = strategy.invoke(nums, threshold)
        assertEquals(expected, actual)
    }
}

class SmallestDivisorBinarySearchTest :
    SmallestDivisorStrategyTest<SmallestDivisorBinarySearch>(SmallestDivisorBinarySearch())

class SmallestDivisorMathTest : SmallestDivisorStrategyTest<SmallestDivisorMath>(SmallestDivisorMath())
