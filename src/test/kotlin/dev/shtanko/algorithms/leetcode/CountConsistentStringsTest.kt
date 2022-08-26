/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

abstract class CountConsistentStringsTest<out T : CountConsistentStrings>(private val strategy: T) {
    private class InoutArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "ab",
                arrayOf("ad", "bd", "aaab", "baa", "badab"),
                2,
            ),
            Arguments.of(
                "abc",
                arrayOf("a", "b", "c", "ab", "ac", "bc", "abc"),
                7,
            ),
            Arguments.of(
                "cad",
                arrayOf("cc", "acd", "b", "ba", "bac", "bad", "ac", "d"),
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InoutArgumentsProvider::class)
    fun `count consistent strings test`(allowed: String, words: Array<String>, expected: Int) {
        val actual = strategy.perform(allowed, words)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountConsistentStringsMapTest : CountConsistentStringsTest<CountConsistentStrings>(CountConsistentStringsMap())
