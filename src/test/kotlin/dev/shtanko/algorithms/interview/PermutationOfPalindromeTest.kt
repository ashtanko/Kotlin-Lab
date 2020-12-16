/*
 * Copyright 2020 Alexey Shtanko
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

package dev.shtanko.algorithms.interview

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class PermutationOfPalindromeTest {

    companion object {

        @JvmStatic
        fun dataProvider() = listOf(
            "tactcoa" to true,
            "abcdefg" to false,
            "aaaa" to true,
            "aaaabbbb" to true,
            "aaaacbbb" to false
        )
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `is permutation of palindrome test`(testCase: Pair<String, Boolean>) {
        val actual = testCase.first.isPermutationOfPalindrome()
        if (testCase.second) {
            assertTrue(actual)
        } else {
            assertFalse(actual)
        }
    }
}
