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
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class KthSmallestTest<out T : KthSmallest>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 5, 9),
                    intArrayOf(10, 11, 13),
                    intArrayOf(12, 13, 15),
                ),
                8,
                13,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(-5),
                ),
                1,
                -5,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `kth smallest`(matrix: Array<IntArray>, k: Int, expected: Int) {
        val actual = strategy.invoke(matrix, k)
        assertThat(actual).isEqualTo(expected)
    }
}

@DisplayName("kth smallest min heap strategy")
class KthSmallestFurthestBuildingMinHeapTest : KthSmallestTest<KthSmallestStrategy.MinHeap>(KthSmallestStrategy.MinHeap)

@DisplayName("kth smallest binary search strategy")
class KthSmallestBinarySearchTest :
    KthSmallestTest<KthSmallestStrategy.BinarySearch>(KthSmallestStrategy.BinarySearch)
