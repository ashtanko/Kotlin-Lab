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

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class RemoveDuplicatesTest {
    @Test
    fun `test removing duplicates`() {
        val nums = intArrayOf(1, 1, 2, 2, 2, 3, 4, 4, 5)
        val expectedUnique = intArrayOf(1, 2, 3, 4, 5)

        val newSize = removeDuplicates(nums)
        val uniqueArray = nums.copyOf(newSize)

        assertArrayEquals(expectedUnique, uniqueArray)
    }

    @Test
    fun `test removing duplicates from empty array`() {
        val nums = intArrayOf()
        val expectedUnique = intArrayOf()

        val newSize = removeDuplicates(nums)
        val uniqueArray = nums.copyOf(newSize)

        assertArrayEquals(expectedUnique, uniqueArray)
    }

    @Test
    fun `test removing duplicates from single-element array`() {
        val nums = intArrayOf(42)
        val expectedUnique = intArrayOf(42)

        val newSize = removeDuplicates(nums)
        val uniqueArray = nums.copyOf(newSize)

        assertArrayEquals(expectedUnique, uniqueArray)
    }
}
