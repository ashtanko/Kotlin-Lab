/*
 * Copyright 2020 Oleksii Shtanko
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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SortColorsTest<out T : SortColors>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sort colors test`(arr: IntArray, expected: IntArray) {
        strategy(arr)
        assertArrayEquals(expected, arr)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 0, 2, 1, 1, 0),
                intArrayOf(0, 0, 1, 1, 2, 2),
            ),
            Arguments.of(
                intArrayOf(2, 0, 1),
                intArrayOf(0, 1, 2),
            ),
            Arguments.of(
                intArrayOf(0),
                intArrayOf(0),
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(1),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(2, 0, 2, 1, 1, 0, 0, 1, 2, 2, 1, 0, 1, 2, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2),
            ),
        )
    }
}

class SortColorsOnePassTest : SortColorsTest<SortColorsOnePass>(SortColorsOnePass())
class SortColorsTwoPassTest : SortColorsTest<SortColorsTwoPass>(SortColorsTwoPass())
