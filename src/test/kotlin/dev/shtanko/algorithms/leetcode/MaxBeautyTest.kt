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

abstract class MaxBeautyTest<out T : MaxBeauty>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(3, 2),
                    intArrayOf(2, 4),
                    intArrayOf(5, 6),
                    intArrayOf(3, 5),
                ),
                intArrayOf(1, 2, 3, 4, 5, 6),
                intArrayOf(2, 4, 5, 5, 6, 6),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 4),
                ),
                intArrayOf(1),
                intArrayOf(4),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(10, 1000),
                ),
                intArrayOf(5),
                intArrayOf(0),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun maximumBeautyTest(items: Array<IntArray>, queries: IntArray, expected: IntArray) {
        val actual = strategy(items, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxBeautySortingBinarySearchTest : MaxBeautyTest<MaxBeauty>(MaxBeautySortingBinarySearch())
class MaxBeautySortingQueriesTest : MaxBeautyTest<MaxBeauty>(MaxBeautySortingQueries())
