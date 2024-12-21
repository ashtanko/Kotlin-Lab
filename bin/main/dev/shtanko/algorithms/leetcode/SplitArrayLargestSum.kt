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

import kotlin.math.max
import kotlin.math.min

/**
 * 410. Split Array Largest Sum
 * @see <a href="https://leetcode.com/problems/split-array-largest-sum/">Source</a>
 */
fun interface SplitArrayLargestSum {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class SplitArrayLargestSumMaxSum : SplitArrayLargestSum {
    override fun invoke(nums: IntArray, k: Int): Int {
        // validation
        if (nums.isEmpty() || k < 1) {
            return 0
        }

        // get [minMaxSum, maxMaxSum]
        var start: Long = 0
        var end: Long = 0
        for (n in nums) {
            start = min(start, n.toLong())
            end += n.toLong()
        }

        // binary search
        while (start < end) {
            val middle = start + (end - start) / 2
            if (validateMaxSum(nums, middle, k - 1)) {
                // valid maxSum & cut, so we need to shrink the finding (reduce max sum)
                end = middle
            } else {
                // invalid maxSum & cut, so we need to expand the finding (increase max sum)
                start = middle + 1
            }
        }
        return start.toInt()
    }

    /**
     * Given a max sum, we are validating the cuts. Whenever we collect/aggregate enough sum (<= maxSum), we start a new
     * group
     */
    private fun validateMaxSum(nums: IntArray, maxSum: Long, cuts: Int): Boolean {
        var cuts0 = cuts
        var agg = 0
        for (n in nums) {
            // validate value (not needed)
            if (n > maxSum || cuts0 < 0) {
                return false
            }
            agg += n
            if (agg > maxSum) {
                // find a group
                agg = n
                cuts0--
                if (cuts0 < 0) return false
            }
        }
        // cuts may still > 1, which means we can even have less num of cuts for grouping
        return true
    }
}

class SplitArrayLargestSumGreedy : SplitArrayLargestSum {
    override fun invoke(nums: IntArray, k: Int): Int {
        var low = 0
        var high = 0
        var min = Int.MAX_VALUE
        for (i in nums.indices) {
            low = max(low, nums[i])
            high += nums[i]
        }
        while (low <= high) {
            val mid = (low + high) / 2
            if (requiredNoOfChunks(nums, mid, k)) {
                min = min(min, mid)
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return min
    }

    private fun requiredNoOfChunks(nums: IntArray, mid: Int, m: Int): Boolean {
        var chunks = 0
        var i = 0
        while (i < nums.size) {
            var value = 0
            while (i < nums.size && nums[i] + value <= mid) {
                value += nums[i++]
            }
            chunks++
        }
        return chunks <= m
    }
}

class SplitArrayLargestSumBinarySearch : SplitArrayLargestSum {
    override fun invoke(nums: IntArray, k: Int): Int {
        // sanity check
        if (nums.isEmpty()) return 0

        var lo = 0L
        var hi = 0L
        for (num in nums) {
            lo = maxOf(lo, num.toLong())
            hi += num
        }

        while (lo < hi) {
            val mid: Long = lo + (hi - lo) / 2

            if (minGroups(mid, nums) > k) {
                lo = mid + 1
            } else {
                hi = mid
            }
        }

        return lo.toInt()
    }

    private fun minGroups(limit: Long, nums: IntArray): Int {
        var sum = 0
        var groups = 1
        for (num in nums) {
            if (sum + num > limit) {
                sum = num
                ++groups
            } else {
                sum += num
            }
        }

        return groups
    }
}

class SplitArrayLargestSumDP : SplitArrayLargestSum {
    override fun invoke(nums: IntArray, k: Int): Int {
        // sanity check
        if (nums.isEmpty()) return 0

        val size = nums.size

        // 1-indexed, instead of 0-indexed
        val prefixSums = IntArray(size + 1)
        for (i in 0 until size) {
            prefixSums[i + 1] = prefixSums[i] + nums[i]
        }

        val dp = Array(size + 1) { IntArray(k + 1) { Int.MAX_VALUE } }
        dp[0][0] = 0

        // 1-indexed, instead of 0-indexed
        for (i in 1..size) {
            // the actual split(s), starting with 1
            for (j in 1..k) {
                // [0, k], [k, i]: where to split the array
                for (m in 0 until i) {
                    val subarraySum = prefixSums[i] - prefixSums[m]
                    dp[i][j] = minOf(dp[i][j], maxOf(dp[m][j - 1], subarraySum))
                }
            }
        }

        return dp[size][k]
    }
}
