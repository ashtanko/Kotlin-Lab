/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2348. Number of Zero-Filled Subarrays
 * @see <a href="https://leetcode.com/problems/number-of-zero-filled-subarrays/">Source</a>
 */
@Medium("https://leetcode.com/problems/number-of-zero-filled-subarrays")
fun interface ZeroFilledSubarray {
    operator fun invoke(nums: IntArray): Long
}

class ZeroFilledSubarrayTwoPointers : ZeroFilledSubarray {
    override operator fun invoke(nums: IntArray): Long {
        var res: Long = 0
        var i = 0
        var j = 0
        while (i < nums.size) {
            if (nums[i] != 0) {
                j = i + 1
            }
            res += (i - j + 1).toLong()
            ++i
        }
        return res
    }
}
