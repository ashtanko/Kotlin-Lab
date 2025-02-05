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
 * 1887. Reduction Operations to Make the Array Elements Equal
 * @see <a href="https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal">Source</a>
 */
fun interface ReductionOperations {
    operator fun invoke(nums: IntArray): Int
}

class ReductionOperationsSortAndCount : ReductionOperations {
    override fun invoke(nums: IntArray): Int {
        nums.sort()
        var ans = 0
        var up = 0

        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                up++
            }
            ans += up
        }

        return ans
    }
}
