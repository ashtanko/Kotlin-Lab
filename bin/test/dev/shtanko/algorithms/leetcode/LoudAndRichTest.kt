/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class LoudAndRichTest<out T : LoudAndRich>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(2, 1),
                    intArrayOf(3, 1),
                    intArrayOf(3, 7),
                    intArrayOf(4, 3),
                    intArrayOf(5, 3),
                    intArrayOf(6, 3),
                ),
                intArrayOf(3, 2, 5, 4, 6, 1, 7, 0),
                intArrayOf(5, 5, 2, 5, 4, 5, 6, 7),
            ),
            Arguments.of(
                arrayOf<IntArray>(),
                intArrayOf(0),
                intArrayOf(0),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `loud and rich test`(richer: Array<IntArray>, quiet: IntArray, expected: IntArray) {
        val actual = strategy.invoke(richer, quiet)
        assertThat(actual).contains(*expected)
    }
}

class LoudAndRichDFSTest : LoudAndRichTest<LoudAndRich>(LoudAndRichDFS())
class LoudAndRichMapTest : LoudAndRichTest<LoudAndRich>(LoudAndRichMap())
