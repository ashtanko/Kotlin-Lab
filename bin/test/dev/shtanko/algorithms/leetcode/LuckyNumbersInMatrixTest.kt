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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AbstractLuckyNumbersTest<out T : AbstractLuckyNumbers>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `lucky numbers test`(matrix: Array<IntArray>, expected: List<Int>) {
        assertEquals(expected, strategy.invoke(matrix))
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 7, 8),
                    intArrayOf(9, 11, 13),
                    intArrayOf(15, 16, 17),
                ),
                listOf(15),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 10, 4, 2),
                    intArrayOf(9, 3, 8, 7),
                    intArrayOf(15, 16, 17, 12),
                ),
                listOf(12),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(7, 8),
                    intArrayOf(1, 2),
                ),
                listOf(7),
            ),
            Arguments.of(
                arrayOf(intArrayOf(1)),
                listOf(1),
            ),
        )
    }
}

class LuckyNumbersTest : AbstractLuckyNumbersTest<LuckyNumbers>(LuckyNumbers())

class LuckyNumbersSetTest : AbstractLuckyNumbersTest<LuckyNumbersSet>(LuckyNumbersSet())
