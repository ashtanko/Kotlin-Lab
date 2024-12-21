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

import dev.shtanko.algorithms.DECIMAL

/**
 * Maximum Gap
 * @see <a href="https://leetcode.com/problems/maximum-gap/">Source</a>
 */
fun interface MaximumGap {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Comparison Sorting
 * Time complexity: O(n log n)
 * Space complexity: No extra space needed, other than the input array (since sorting can usually be done in-place).
 */
class MaximumGapComparisonSorting : MaximumGap {
    override operator fun invoke(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size < 2) {
            return 0
        }

        nums.sort()
        var maxGap = 0
        for (i in 0 until nums.size - 1) {
            maxGap = kotlin.math.max(nums[i + 1] - nums[i], maxGap)
        }
        return maxGap
    }
}

/**
 * Approach 2: Radix Sort
 * Time complexity: O(n)O(d⋅(n+k))≈O(n).
 * Space complexity: O(n)O(n+k)≈O(n) extra space.
 */
class MaximumGapRadixSort : MaximumGap {
    override operator fun invoke(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size < 2) {
            return 0
        }

        val maxVal = nums.maxOrNull() ?: 0
        var exp = 1
        val aux = IntArray(nums.size)

        while (maxVal / exp > 0) {
            val count = IntArray(RADIX)

            for (i in nums.indices) {
                count[nums[i].div(exp) % DECIMAL]++
            }
            for (i in 1 until count.size) {
                count[i] += count[i - 1]
            }
            for (i in nums.size - 1 downTo 0) {
                aux[--count[nums[i].div(exp) % DECIMAL]] = nums[i]
            }
            for (i in nums.indices) {
                nums[i] = aux[i]
            }
            exp *= DECIMAL
        }

        var maxGap = 0
        for (i in 0 until nums.size - 1) {
            maxGap = kotlin.math.max(nums[i + 1] - nums[i], maxGap)
        }
        return maxGap
    }

    companion object {
        private const val RADIX = 10
    }
}

/**
 * Approach 3: Buckets and The Pigeonhole Principle
 */
class MaximumGapBuckets : MaximumGap {
    override operator fun invoke(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size < 2) {
            return 0
        }
        val mini = nums.minOrNull() ?: 0
        val maxi = nums.maxOrNull() ?: 0
        val bucketSize = kotlin.math.max(1, (maxi - mini) / (nums.size - 1))
        val bucketNum = (maxi - mini) / bucketSize + 1
        val buckets = Array(bucketNum) { Bucket() }

        for (num in nums) {
            val bucketIdx = (num - mini) / bucketSize
            buckets[bucketIdx].used = true
            buckets[bucketIdx].minVal = kotlin.math.min(num, buckets[bucketIdx].minVal)
            buckets[bucketIdx].maxVal = kotlin.math.max(num, buckets[bucketIdx].maxVal)
        }

        var prevBucketMax = mini
        var maxGap = 0
        for (bucket in buckets) {
            if (!bucket.used) {
                continue
            }
            maxGap = kotlin.math.max(maxGap, bucket.minVal - prevBucketMax)
            prevBucketMax = bucket.maxVal
        }

        return maxGap
    }

    private data class Bucket(
        var used: Boolean = false,
        var maxVal: Int = Int.MIN_VALUE,
        var minVal: Int = Int.MAX_VALUE,
    )
}
