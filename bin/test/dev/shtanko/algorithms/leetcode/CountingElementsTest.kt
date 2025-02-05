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

abstract class CountingElementsTest<out T : CountingElements>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 1, 3, 3, 5, 5, 7, 7),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 3, 2, 3, 5, 0),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 1, 3, 3, 5, 5, 7, 7, 9, 9),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5),
                8,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6),
                10,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7),
                12,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8),
                14,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9),
                16,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10),
                18,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11),
                20,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `counting elements test`(arr: IntArray, expected: Int) {
        val actual = strategy.invoke(arr)
        assertThat(actual).isEqualTo(expected)
    }
}

class CESearchWithArrayTest : CountingElementsTest<CESearchWithArray>(CESearchWithArray())
class CESearchingWithHashSetTest : CountingElementsTest<CESearchingWithHashSet>(CESearchingWithHashSet())
class CESearchingWithSortedArrayTest :
    CountingElementsTest<CESearchingWithSortedArray>(CESearchingWithSortedArray())
