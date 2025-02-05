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

abstract class ValidAnagramTest<out T : ValidAnagram>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                "",
                true,
            ),
            Arguments.of(
                "q",
                "q",
                true,
            ),
            Arguments.of(
                "qwe",
                "qwe",
                true,
            ),
            Arguments.of(
                "cat",
                "tac",
                true,
            ),
            Arguments.of(
                "a",
                "b",
                false,
            ),
            Arguments.of(
                "a",
                "",
                false,
            ),
            Arguments.of(
                "ab",
                "",
                false,
            ),
            Arguments.of(
                "ab",
                "ac",
                false,
            ),
            Arguments.of(
                "anagram",
                "nagaram",
                true,
            ),
            Arguments.of(
                "rat",
                "car",
                false,
            ),
            Arguments.of(
                "restful",
                "fluster",
                true,
            ),
            Arguments.of(
                "evil",
                "vile",
                true,
            ),
            Arguments.of(
                "elvis",
                "lives",
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is anagram test`(s: String, t: String, expected: Boolean) {
        val actual = strategy.invoke(s, t)
        assertThat(actual).isEqualTo(expected)
    }
}

class ValidAnagramHashMapTest : ValidAnagramTest<ValidAnagram>(ValidAnagramHashMap())
class ValidAnagramImplTest : ValidAnagramTest<ValidAnagram>(ValidAnagramImpl())
