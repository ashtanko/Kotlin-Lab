/*
 * Copyright 2023 Oleksii Shtanko
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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ExtraCharactersInStringTest<out T : ExtraCharactersInString>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                arrayOf("leet", "code", "leetcode"),
                0,
            ),
            Arguments.of(
                "",
                arrayOf(""),
                0,
            ),
            Arguments.of(
                "",
                arrayOf<String>(),
                0,
            ),
            Arguments.of(
                "leetscode",
                arrayOf("leet", "code", "leetcode"),
                1,
            ),
            Arguments.of(
                "sayhelloworld",
                arrayOf("hello", "world"),
                3,
            ),
            Arguments.of(
                "okwqrototydnjmtauzoae",
                arrayOf(
                    "r",
                    "pzvnt",
                    "yxgtfy",
                    "zaqj",
                    "vdd",
                    "iqq",
                    "vrbppx",
                    "djmo",
                    "u",
                    "toqs",
                    "dxlk",
                    "oxf",
                    "wofej",
                    "zuvoo",
                    "bjazak",
                    "stnmn",
                    "zbj",
                    "okw",
                    "x",
                    "z",
                    "xdhs",
                    "eszgo",
                    "f",
                    "h",
                    "oa",
                    "xfql",
                    "rxuneu",
                    "v",
                    "ihct",
                    "rsps",
                    "chb",
                    "m",
                    "vpvaa",
                    "ngn",
                    "mm",
                    "vtuuae",
                    "o",
                    "a",
                    "iwh",
                    "pmnqm",
                    "vaxl",
                    "tauzo",
                    "iijwds",
                    "idfee",
                    "gyq",
                ),
                8,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min extra char test`(s: String, dictionary: Array<String>, expected: Int) {
        val actual = strategy(s, dictionary)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ExtraCharactersInStringTopDownTest :
    ExtraCharactersInStringTest<ExtraCharactersInString>(ExtraCharactersInStringTopDown())

class ExtraCharactersInStringBottomUpTest :
    ExtraCharactersInStringTest<ExtraCharactersInString>(ExtraCharactersInStringBottomUp())

class ExtraCharactersInStringTopDownTrieTest :
    ExtraCharactersInStringTest<ExtraCharactersInString>(ExtraCharactersInStringTopDownTrie())

class ExtraCharactersInStringBottomUpTrieTest :
    ExtraCharactersInStringTest<ExtraCharactersInString>(ExtraCharactersInStringBottomUpTrie())
