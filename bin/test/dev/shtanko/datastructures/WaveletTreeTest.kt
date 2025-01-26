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

package dev.shtanko.datastructures

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WaveletTreeTest {

    private val array = intArrayOf(1, 3, 3, 2, 1, 2, 3, 4, 1, 2)
    private val waveletTree = WaveletTree(array, 1, 4)

    @Test
    fun `test count`() {
        // Count occurrences of `1` in the range [0, 9]
        val count1 = waveletTree.count(1, 0, 9)
        assertEquals(3, count1, "Count of 1 in range [0, 9] should be 3")

        // Count occurrences of `2` in the range [0, 9]
        val count2 = waveletTree.count(2, 0, 9)
        assertEquals(3, count2, "Count of 2 in range [0, 9] should be 3")

        // Count occurrences of `3` in the range [2, 5]
        val count3 = waveletTree.count(3, 2, 5)
        assertEquals(1, count3, "Count of 3 in range [2, 5] should be 1")
    }

    @Test
    fun `test kth smallest`() {
        // Find the 1st smallest element in the range [0, 9]
        val kth1 = waveletTree.kthSmallest(0, 9, 1)
        assertEquals(1, kth1, "The 1st smallest element in the range [0, 9] should be 1")

        // Find the 4th smallest element in the range [0, 9]
        val kth4 = waveletTree.kthSmallest(0, 9, 4)
        assertEquals(2, kth4, "The 4th smallest element in the range [0, 9] should be 2")
    }

    @Test
    fun `test rank`() {
        // Find the rank of `1` in the range [0, 9]
        val rank1 = waveletTree.rank(1, 0, 9)
        assertEquals(3, rank1, "Rank of 1 in range [0, 9] should be 3")

        // Find the rank of `2` in the range [0, 9]
        val rank2 = waveletTree.rank(2, 0, 9)
        assertEquals(6, rank2, "Rank of 2 in range [0, 9] should be 6")

        // Find the rank of `3` in the range [0, 9]
        val rank3 = waveletTree.rank(3, 0, 9)
        assertEquals(9, rank3, "Rank of 3 in range [0, 9] should be 9")
    }
}
