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

class SparseVectorTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 0, 0, 2, 3),
                intArrayOf(0, 3, 0, 4, 0),
                8,
            ),
            Arguments.of(
                intArrayOf(0, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 2),
                0,
            ),
            Arguments.of(
                intArrayOf(0, 1, 0, 0, 2, 0, 0),
                intArrayOf(1, 0, 0, 0, 3, 0, 4),
                6,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sparse array vector test`(arr1: IntArray, arr2: IntArray, expected: Int) {
        val v1 = SparseVectorArray(arr1)
        val v2 = SparseVectorArray(arr2)
        val actual = v1.dotProduct(v2)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sparse hash set vector test`(arr1: IntArray, arr2: IntArray, expected: Int) {
        val v1 = SparseVectorHashSet(arr1)
        val v2 = SparseVectorHashSet(arr2)
        val actual = v1.dotProduct(v2)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sparse pairs vector test`(arr1: IntArray, arr2: IntArray, expected: Int) {
        val v1 = SparseVectorPairs(arr1)
        val v2 = SparseVectorPairs(arr2)
        val actual = v1.dotProduct(v2)
        assertThat(actual).isEqualTo(expected)
    }
}
