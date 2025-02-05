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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class NQueensTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                listOf(
                    listOf("Q"),
                ),
            ),
            Arguments.of(
                4,
                listOf(
                    listOf(".Q..", "...Q", "Q...", "..Q."),
                    listOf("..Q.", "Q...", "...Q", ".Q.."),
                ),
            ),
        )
    }

    class InputBoardArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                arrayOf<Pair<Int, Char>>(),
                "2 |_||_|\n1 |_||_|\n  a  b\n",
            ),
            Arguments.of(
                2,
                arrayOf(1 to 'a', 2 to 'b'),
                "2 |_||#|\n1 |#||_|\n  a  b\n",
            ),
            Arguments.of(
                8,
                arrayOf<Pair<Int, Char>>(),
                "8 |_||_||_||_||_||_||_||_|\n7 |_||_||_||_||_||_||_||_|\n6 |_||_||_||_||_||_||_||_|\n5 |_||_||_||_||_||_||_||_|\n4 |_||_||_||_||_||_||_||_|\n3 |_||_||_||_||_||_||_||_|\n2 |_||_||_||_||_||_||_||_|\n1 |_||_||_||_||_||_||_||_|\n  a  b  c  d  e  f  g  h\n",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `n queens test`(target: Int, expected: List<List<String>>) {
        val actual = target.solveNQueens()
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputBoardArgumentsProvider::class)
    fun `generate board test`(num: Int, loc: Array<Pair<Int, Char>>, expected: String) {
        val actual = num.genBoard(*loc)
        assertThat(actual, equalTo(expected))
    }

    @Test
    fun `assert locations wrong input char test`() {
        val abc = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
        assertThrows<IllegalStateException> {
            arrayOf(1 to 'x').assertLocations(2, abc)
        }
    }

    @Test
    fun `assert locations wrong input int test`() {
        val abc = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
        assertThrows<IllegalStateException> {
            arrayOf(3 to 'a').assertLocations(2, abc)
        }
    }
}
