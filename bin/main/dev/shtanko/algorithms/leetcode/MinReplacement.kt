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

package dev.shtanko.algorithms.leetcode

/**
 * 2366. Minimum Replacements to Sort the Array
 * @see <a href="https://leetcode.com/problems/minimum-replacements-to-sort-the-array">Source</a>
 */
fun interface MinReplacement {
    operator fun invoke(nums: IntArray): Long
}

class MinReplacementGreedy : MinReplacement {
    override operator fun invoke(nums: IntArray): Long {
        var answer: Long = 0
        val n: Int = nums.size

        // Start from the second last element, as the last one is always sorted.
        for (i in n - 2 downTo 0) {
            // No need to break if they are already in order.
            if (nums[i] <= nums[i + 1]) {
                continue
            }

            // Count how many elements are made from breaking nums[i].
            val numElements = (nums[i] + nums[i + 1] - 1).toLong() / nums[i + 1].toLong()

            // It requires numElements - 1 replacement operations.
            answer += numElements - 1

            // Maximize nums[i] after replacement.
            nums[i] = nums[i] / numElements.toInt()
        }

        return answer
    }
}
