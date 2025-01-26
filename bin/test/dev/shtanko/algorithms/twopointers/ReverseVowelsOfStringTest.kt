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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReverseVowelsOfStringTest {

    @Test
    fun `test reversing vowels in a string with mixed case`() {
        val input = "Hello World"
        val expected = "Hollo Werld"

        val result = reverseVowels(input)

        assertEquals(expected, result)
    }

    @Test
    fun `test reversing vowels in a string with only vowels`() {
        val input = "aeiouAEIOU"
        val expected = "UOIEAuoiea"

        val result = reverseVowels(input)

        assertEquals(expected, result)
    }

    @Test
    fun `test reversing vowels in a string with no vowels`() {
        val input = "bcdfghjklmnpqrstvwxyz"
        val expected = "bcdfghjklmnpqrstvwxyz"

        val result = reverseVowels(input)

        assertEquals(expected, result)
    }

    @Test
    fun `test reversing vowels in an empty string`() {
        val input = ""
        val expected = ""

        val result = reverseVowels(input)

        assertEquals(expected, result)
    }
}
