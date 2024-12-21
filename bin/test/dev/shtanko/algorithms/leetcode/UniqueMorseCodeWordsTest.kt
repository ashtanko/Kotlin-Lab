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

class UniqueMorseCodeWordsTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    "gin",
                    "zen",
                    "gig",
                    "msg",
                ),
                2,
            ),
            Arguments.of(
                arrayOf(
                    "a",
                    "b",
                    "c",
                    "d",
                ),
                4,
            ),
            Arguments.of(
                arrayOf(
                    "a",
                    "a",
                    "a",
                    "a",
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    "a",
                    "b",
                    "c",
                    "d",
                    "e",
                    "f",
                    "g",
                    "h",
                    "i",
                    "j",
                    "k",
                    "l",
                    "m",
                    "n",
                    "o",
                    "p",
                    "q",
                    "r",
                    "s",
                    "t",
                    "u",
                    "v",
                    "w",
                    "x",
                    "y",
                    "z",
                ),
                26,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `unique morse code words test`(words: Array<String>, expected: Int) {
        val actual = words.uniqueMorseRepresentations()
        assertEquals(expected, actual)
    }
}
