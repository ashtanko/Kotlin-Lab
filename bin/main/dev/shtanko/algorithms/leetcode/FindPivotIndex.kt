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
 * 724. Find Pivot Index
 * @see <a href="https://leetcode.com/problems/find-pivot-index/?envType=study-plan&id=level-1">Source</a>
 */
fun interface FindPivotIndex {
    operator fun invoke(nums: IntArray): Int
}

class FindPivotIndexPrefixSum : FindPivotIndex {
    override fun invoke(nums: IntArray): Int {
        var sum = 0
        var leftsum = 0
        for (x in nums) sum += x
        for (i in nums.indices) {
            if (leftsum == sum - leftsum - nums[i]) return i
            leftsum += nums[i]
        }
        return -1
    }
}
