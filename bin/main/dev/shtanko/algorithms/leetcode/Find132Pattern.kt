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
 * 456. 132 Pattern
 * @see <a href="https://leetcode.com/problems/132-pattern">Source</a>
 */
fun interface Find132Pattern {
    operator fun invoke(nums: IntArray): Boolean
}

class Find132PatternOnePass : Find132Pattern {
    override fun invoke(nums: IntArray): Boolean {
        val n: Int = nums.size
        var top = n
        var third = Int.MIN_VALUE

        for (i in n - 1 downTo 0) {
            if (nums[i] < third) return true
            while (top < n && nums[i] > nums[top]) third = nums[top++]
            nums[--top] = nums[i]
        }

        return false
    }
}
