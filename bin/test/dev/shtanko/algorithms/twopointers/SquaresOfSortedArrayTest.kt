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

class SquaresOfSortedArrayTest {

    @Test
    fun `test sorting squares of a sorted array`() {
        val nums = intArrayOf(-4, -2, 0, 3, 5)
        val expectedSquares = intArrayOf(0, 4, 9, 16, 25)

        val squares = sortedSquares(nums)

        assertArrayEquals(expectedSquares, squares)
    }

    @Test
    fun `test sorting squares of an array with negative values`() {
        val nums = intArrayOf(-7, -3, 2, 3, 11)
        val expectedSquares = intArrayOf(4, 9, 9, 49, 121)

        val squares = sortedSquares(nums)

        assertArrayEquals(expectedSquares, squares)
    }

    @Test
    fun `test sorting squares of an array with all negative values`() {
        val nums = intArrayOf(-10, -7, -3, -2, -1)
        val expectedSquares = intArrayOf(1, 4, 9, 49, 100)

        val squares = sortedSquares(nums)

        assertArrayEquals(expectedSquares, squares)
    }
}
