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
 * 523. Continuous Subarray Sum
 * @see <a href="https://leetcode.com/problems/continuous-subarray-sum/">Source</a>
 */
fun interface ContinuousSubarraySum {
    operator fun invoke(nums: IntArray, k: Int): Boolean
}

class ContinuousSubarraySumPrefixSum : ContinuousSubarraySum {
    override fun invoke(nums: IntArray, k: Int): Boolean {
        var prefixMod = 0
        val modSeen: HashMap<Int, Int> = HashMap()
        modSeen[0] = -1

        for (i in nums.indices) {
            prefixMod = (prefixMod + nums[i]) % k

            if (modSeen.containsKey(prefixMod)) {
                if (i - modSeen.getOrDefault(prefixMod, 0) > 1) {
                    return true
                }
            } else {
                modSeen[prefixMod] = i
            }
        }

        return false
    }
}
