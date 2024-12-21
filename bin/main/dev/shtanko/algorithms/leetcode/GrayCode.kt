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

/**
 * 89. Gray Code
 * @see <a href="https://leetcode.com/problems/gray-code/">Source</a>
 */
object GrayCode {

    var nextNum = 0

    /**
     * Approach 1: Backtracking
     * Time complexity: O(n * 2 ^ n)
     * Space complexity: O(2 ^ n)
     */
    fun backtracking(n: Int): List<Int> {
        val result: MutableList<Int> = ArrayList()
        result.add(0)
        // Keeps track of the numbers present in the current sequence.
        val isPresent: MutableSet<Int> = HashSet()
        // All Gray code sequence starts with 0
        isPresent.add(0)
        grayCodeBacktrackingHelper(result, n, isPresent)
        return result
    }

    /**
     * Approach 2: Recursion
     * Time complexity: O(2 ^ n)
     * Space complexity: O(n)
     */
    fun recursion(n: Int): List<Int> {
        val result: MutableList<Int> = ArrayList()
        grayCodeRecursionHelper(result, n)
        return result
    }

    /**
     * Approach 3: Iteration
     * Time complexity: O(2 ^ n).
     * Space complexity: O(1)
     */
    fun iteration(n: Int): List<Int> {
        val result: MutableList<Int> = ArrayList()
        result.add(0)

        for (i in 1..n) {
            val previousSequenceLength = result.size
            val mask = 1 shl i - 1
            for (j in previousSequenceLength - 1 downTo 0) {
                result.add(mask + result[j])
            }
        }
        return result
    }

    private fun grayCodeRecursion2Helper(result: MutableList<Int>, n: Int) {
        if (n == 0) {
            result.add(nextNum)
            return
        }
        grayCodeRecursion2Helper(result, n - 1)
        // Flip the bit at (n - 1)th position from right
        nextNum = nextNum xor (1 shl n - 1)
        grayCodeRecursion2Helper(result, n - 1)
    }

    private fun grayCodeRecursionHelper(result: MutableList<Int>, n: Int) {
        if (n == 0) {
            result.add(0)
            return
        }
        // we derive the n bits sequence from the (n - 1) bits sequence.
        grayCodeRecursionHelper(result, n - 1)
        val currentSequenceLength = result.size
        // Set the bit at position n - 1 (0 indexed) and assign it to mask.
        val mask = 1 shl n - 1
        for (i in currentSequenceLength - 1 downTo 0) {
            // mask is used to set the (n - 1)th bit from the LSB of all the numbers present in the current sequence.
            result.add(result[i] or mask)
        }
    }

    private fun grayCodeBacktrackingHelper(result: MutableList<Int>, n: Int, isPresent: MutableSet<Int>): Boolean {
        if (result.size == 1 shl n) return true
        val current = result[result.size - 1]
        for (i in 0 until n) {
            val next = current xor (1 shl i)
            if (!isPresent.contains(next)) {
                isPresent.add(next)
                result.add(next)
                // If valid sequence found no need to search any further
                if (grayCodeBacktrackingHelper(result, n, isPresent)) return true
                // If no valid sequence found delete the last added number and continue the search.
                isPresent.remove(next)
                result.removeAt(result.size - 1)
            }
        }
        return false
    }
}
