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

package dev.shtanko.algorithms.leetcode

/**
 * 2149. Rearrange Array Elements by Sign
 * @see <a href="https://leetcode.com/problems/rearrange-array-elements-by-sign">Source</a>
 */
fun interface RearrangeArray {
    operator fun invoke(nums: IntArray): IntArray
}

class RearrangeArrayTwoPointers : RearrangeArray {
    override fun invoke(nums: IntArray): IntArray {
        val size = nums.size

        // Initializing an answer array of size n
        val ans = IntArray(size) { 0 }

        // Initializing two pointers to track even and
        // odd indices for positive and negative integers respectively
        var posIndex = 0
        var negIndex = 1

        for (i in 0 until size) {
            val num = nums[i]
            if (num > 0) {
                // Placing the positive integer at the
                // desired index in ans and incrementing posIndex by 2
                ans[posIndex] = num
                posIndex += 2
            } else {
                // Placing the negative integer at the
                // desired index in ans and incrementing negIndex by 2
                ans[negIndex] = num
                negIndex += 2
            }
        }

        return ans
    }
}
