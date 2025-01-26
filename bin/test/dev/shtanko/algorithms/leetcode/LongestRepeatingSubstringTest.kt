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

abstract class LongestRepeatingSubstringTest<out T : LongestRepeatingSubstring>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("", 0),
            Arguments.of("q", 0),
            Arguments.of("qq", 1),
            Arguments.of("abcd", 0),
            Arguments.of("abbaba", 2),
            Arguments.of("aabcaabdaab", 3),
            Arguments.of("aaaaa", 4),
            Arguments.of("aabcccefffff", 4),
            Arguments.of("leetcodecookies", 2),
            Arguments.of("leetcoodecookiees", 3),
            Arguments.of("abbcabbc", 4),
            Arguments.of("qwpqwpqw", 5),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `longest repeating substring test`(s: String, expected: Int) {
        val actual = strategy.invoke(s)
        assertThat(actual).isEqualTo(expected)
    }
}

class LRSBinarySearchTest : LongestRepeatingSubstringTest<LRSBinarySearch>(LRSBinarySearch())
class LRSHashesTest : LongestRepeatingSubstringTest<LRSHashes>(LRSHashes())
class LRSRabinKarpTest : LongestRepeatingSubstringTest<LRSRabinKarp>(LRSRabinKarp())
