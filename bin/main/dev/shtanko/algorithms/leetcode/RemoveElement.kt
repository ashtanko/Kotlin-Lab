/*
 * Copyright 2020 Oleksii Shtanko
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
 * 27. Remove Element
 * @see <a href="https://leetcode.com/problems/remove-linked-list-elements/">Source</a>
 */
fun interface RemoveElement {
    operator fun invoke(nums: IntArray, value: Int): Int
}

class RemoveElementTwoPointers : RemoveElement {
    override fun invoke(nums: IntArray, value: Int): Int {
        var i = 0
        for (j in nums.indices) {
            if (nums[j] != value) {
                val temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
                i++
            }
        }
        return i
    }
}
