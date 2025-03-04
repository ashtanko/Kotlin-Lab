/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 2488. Count Subarrays With Median K
 * @see <a href="https://leetcode.com/problems/count-subarrays-with-median-k/">Source</a>
 */
@Hard("https://leetcode.com/problems/count-subarrays-with-median-k")
fun interface CountSubarrays {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class CountSubarraysMap : CountSubarrays {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        val prefixSumOfBalance: MutableMap<Int, Int> = HashMap()
        prefixSumOfBalance[0] = 1 // Dummy value of 0's frequency is 1.
        var ans = 0
        var runningBalance = 0
        var found = false
        for (num in nums) {
            if (num < k) {
                --runningBalance
            } else if (num > k) {
                ++runningBalance
            } else {
                found = true
            }
            if (found) {
                ans += prefixSumOfBalance.getOrDefault(runningBalance, 0) + prefixSumOfBalance.getOrDefault(
                    runningBalance - 1,
                    0,
                )
            } else {
                prefixSumOfBalance[runningBalance] = prefixSumOfBalance.getOrDefault(runningBalance, 0) + 1
            }
        }
        return ans
    }
}
