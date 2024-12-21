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

abstract class PalindromePermutationTest<out T : PalindromePermutationBehavior>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("", true),
            Arguments.of("a", true),
            Arguments.of("tenet", true),
            Arguments.of("abc", false),
            Arguments.of("code", false),
            Arguments.of("aab", true),
            Arguments.of("carerac", true),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `can permute palindrome test`(s: String, expected: Boolean) {
        val actual = strategy.canPermutePalindrome(s)
        assertEquals(expected, actual)
    }
}

class PalindromePermutationBruteForceTest :
    PalindromePermutationTest<PalindromePermutationBruteForce>(PalindromePermutationBruteForce())

class PalindromePermutationHashMapTest :
    PalindromePermutationTest<PalindromePermutationHashMap>(PalindromePermutationHashMap())

class PalindromePermutationArrayTest :
    PalindromePermutationTest<PalindromePermutationArray>(PalindromePermutationArray())

class PalindromePermutationSinglePassTest :
    PalindromePermutationTest<PalindromePermutationSinglePass>(PalindromePermutationSinglePass())

class PalindromePermutationSetTest :
    PalindromePermutationTest<PalindromePermutationSet>(PalindromePermutationSet())

class PalindromePermutationTreeTest :
    PalindromePermutationTest<PalindromePermutationTree>(PalindromePermutationTree())
