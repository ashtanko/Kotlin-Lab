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
 * 80. Remove Duplicates from Sorted Array II
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii">Source</a>
 */
fun interface RemoveDuplicates2 {
    operator fun invoke(nums: IntArray): Int
}

class RemoveDuplicates2Solution : RemoveDuplicates2 {
    override fun invoke(nums: IntArray): Int {
        var i = 0
        for (n in nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n
            }
        }
        return i
    }
}
