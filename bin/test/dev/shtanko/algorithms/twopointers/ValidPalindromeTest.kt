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

package dev.shtanko.algorithms.twopointers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidPalindromeTest {

    private val testCases = listOf(
        "A man, a plan, a canal: Panama" to true,
        "race a car" to false,
        "Was it a car or a cat I saw?" to true,
        "" to true,
    )

    @Test
    fun `test valid palindrome with alphanumeric characters`() {
        testCases.forEach { (s, expected) ->
            val actual = s.isPalindrome()
            assertThat(actual).isEqualTo(expected)
        }
    }
}
