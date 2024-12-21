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

import dev.shtanko.algorithms.annotations.Greedy

/**
 * 665. Non-decreasing Array
 * https://leetcode.com/problems/non-decreasing-array/
 */
fun interface CheckPossibility {
    operator fun invoke(nums: IntArray): Boolean
}

/**
 * Approach: Greedy
 */
@Greedy
class CheckPossibilityGreedy : CheckPossibility {
    override operator fun invoke(nums: IntArray): Boolean {
        var numViolations = 0
        for (i in 1 until nums.size) {
            if (nums[i - 1] > nums[i]) {
                if (numViolations == 1) {
                    return false
                }
                numViolations++
                if (i < 2 || nums[i - 2] <= nums[i]) {
                    nums[i - 1] = nums[i]
                } else {
                    nums[i] = nums[i - 1]
                }
            }
        }
        return true
    }
}
