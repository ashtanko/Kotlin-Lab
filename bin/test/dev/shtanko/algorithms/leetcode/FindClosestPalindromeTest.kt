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

abstract class FindClosestPalindromeTest<out T : FindClosestPalindrome>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("", ""),
            Arguments.of("1", "0"),
            Arguments.of("1", "0"),
            Arguments.of("10", "9"),
            Arguments.of("111", "101"),
            Arguments.of("123", "121"),
            Arguments.of("456", "454"),
            Arguments.of("1000", "999"),
            Arguments.of("1999", "2002"),
            Arguments.of("9999", "10001"),
            Arguments.of("10000", "9999"),
            Arguments.of("12345", "12321"),
            Arguments.of("123456", "123321"),
            Arguments.of("1234567", "1234321"),
            Arguments.of("12345678", "12344321"),
            Arguments.of("123456789", "123454321"),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `nearest palindromic test`(num: String, expected: String) {
        val actual = strategy(num)
        assertEquals(expected, actual)
    }
}

class FindClosestPalindromeRangeTest :
    FindClosestPalindromeTest<FindClosestPalindrome>(FindClosestPalindromeRange())

class FindClosestPalindromeBSFTest :
    FindClosestPalindromeTest<FindClosestPalindrome>(FindClosestPalindromeBS())
