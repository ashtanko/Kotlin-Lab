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
 * 2966. Divide Array Into Arrays With Max Difference
 * @see <a href="https://leetcode.com/problems/divide-array-into-arrays-with-max-difference">Source</a>
 */
fun interface DivideArrayWithMaxDifference {
    operator fun invoke(nums: IntArray, k: Int): Array<IntArray>
}

class DivideArrayWithMaxDifferenceSort : DivideArrayWithMaxDifference {
    override fun invoke(nums: IntArray, k: Int): Array<IntArray> {
        nums.sort()
        val ans = Array(nums.size / 3) { IntArray(3) }
        var i = 0
        while (i < nums.size) {
            if (nums[i + 2] - nums[i] > k) {
                return Array(0) { IntArray(0) }
            }
            ans[i / 3] = intArrayOf(nums[i], nums[i + 1], nums[i + 2])
            i += 3
        }
        return ans
    }
}
