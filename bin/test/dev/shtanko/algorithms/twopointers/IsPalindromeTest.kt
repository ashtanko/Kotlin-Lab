/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.twopointers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class IsPalindromeTest {
    class IsPalindromeTest {

        @ParameterizedTest
        @CsvSource(
            "racecar, true",
            "madam, true",
            "step on no pets, true",
            "hello, false",
            "A man a plan a canal Panama, true",
            "12321, true",
            "123456, false",
            "'', true",
            "'a', true",
            "'aa', true",
            "'ab', false",
            "'A man, a plan, a canal, Panama', true",
        )
        fun testIsPalindrome(input: String, expected: Boolean) {
            assertEquals(expected, isPalindromeTwoPointers(input))
            assertEquals(expected, isPalindromeSimplified(input))
        }
    }
}
