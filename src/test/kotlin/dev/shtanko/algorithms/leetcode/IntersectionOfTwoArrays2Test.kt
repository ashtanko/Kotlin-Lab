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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class IntersectionOfTwoArrays2Test<out T : IntersectionOfTwoArrays2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 2, 1),
                intArrayOf(2, 2),
                intArrayOf(2, 2),
            ),
            Arguments.of(
                intArrayOf(4, 9, 5),
                intArrayOf(9, 4, 9, 8, 4),
                intArrayOf(4, 9),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun intersectTest(nums1: IntArray, nums2: IntArray, expected: IntArray) {
        val actual = strategy(nums1, nums2)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}

class IntersectionOfTwoArrays2SortTest :
    IntersectionOfTwoArrays2Test<IntersectionOfTwoArrays2>(IntersectionOfTwoArrays2Sort())

class IntersectionOfTwoArrays2MapTest :
    IntersectionOfTwoArrays2Test<IntersectionOfTwoArrays2>(IntersectionOfTwoArrays2Map())
