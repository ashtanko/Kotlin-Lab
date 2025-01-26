/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.DP

/**
 * 698. Partition to K Equal Sum Subsets
 * @see <a href="https://leetcode.com/problems/partition-to-k-equal-sum-subsets/">Source</a>
 */
fun interface CanPartitionKSubsets {
    operator fun invoke(nums: IntArray, k: Int): Boolean
}

@DP
class CanPartitionKSubsetsDP : CanPartitionKSubsets {
    override operator fun invoke(nums: IntArray, k: Int): Boolean {
        if (nums.isEmpty()) return false

        val n: Int = nums.size
        // result array
        val dp = BooleanArray(1 shl n)
        val total = IntArray(1 shl n)
        dp[0] = true

        var sum = 0
        for (num in nums) {
            sum += num
        }
        nums.sort()

        if (sum % k != 0) return false
        sum /= k
        if (nums[n - 1] > sum) return false
        // Loop over power set
        for (i in 0 until (1 shl n)) {
            if (dp[i]) {
                // Loop over each element to find subset
                for (j in 0 until n) {
                    // set the jth bit
                    val temp = i or (1 shl j)
                    if (temp != i) {
                        // if total sum is less than target store in dp and total array
                        if (nums[j] <= sum - total[i] % sum) {
                            dp[temp] = true
                            total[temp] = nums[j] + total[i]
                        } else {
                            break
                        }
                    }
                }
            }
        }
        return dp[(1 shl n) - 1]
    }
}
