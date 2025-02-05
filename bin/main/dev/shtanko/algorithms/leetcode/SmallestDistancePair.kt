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

import dev.shtanko.algorithms.annotations.BinarySearch
import kotlin.math.abs

/**
 * 719. Find K-th Smallest Pair Distance
 * @see <a href="https://leetcode.com/problems/find-k-th-smallest-pair-distance/">Source</a>
 */
fun interface SmallestDistancePair {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class SmallestDistancePairBucketSort : SmallestDistancePair {
    override fun invoke(nums: IntArray, k: Int): Int {
        val arrayLength = nums.size

        // Find the maximum element in the array
        var maxElement = Int.MIN_VALUE
        for (num in nums) {
            maxElement = maxOf(maxElement, num)
        }

        // Initialize a bucket array to store counts of each distance
        val distanceBucket = IntArray(maxElement + 1)

        // Populate the bucket array with counts of each distance
        for (i in 0 until arrayLength) {
            for (j in i + 1 until arrayLength) {
                // Compute the distance between nums[i] and nums[j]
                val distance = abs(nums[i] - nums[j])

                // Increment the count for this distance in the bucket
                distanceBucket[distance]++
            }
        }

        // Find the k-th smallest distance
        var remainingK = k
        for (dist in distanceBucket.indices) {
            // Reduce k by the number of pairs with the current distance
            remainingK -= distanceBucket[dist]

            // If k is less than or equal to 0, return the current distance
            if (remainingK <= 0) {
                return dist
            }
        }

        return -1
    }
}

/**
 * Approach 2: Binary Search + Dynamic Programming (DP)
 */
@BinarySearch
class SmallestDistancePairBS : SmallestDistancePair {
    override fun invoke(nums: IntArray, k: Int): Int {
        nums.sort()
        val arraySize = nums.size

        // Initialize binary search range
        var low = 0
        var high = nums[arraySize - 1] - nums[0]

        while (low < high) {
            val mid = (low + high) / 2

            // Count pairs with distance <= mid
            val count = countPairsWithMaxDistance(nums, mid)

            // Adjust binary search bounds based on count
            if (count < k) {
                low = mid + 1
            } else {
                high = mid
            }
        }

        return low
    }

    // Count number of pairs with distance <= maxDistance using a moving window
    private fun countPairsWithMaxDistance(nums: IntArray, maxDistance: Int): Int {
        var count = 0
        var left = 0

        for (right in nums.indices) {
            // Adjust the left pointer to maintain the window with distances <= maxDistance
            while (nums[right] - nums[left] > maxDistance) {
                left++
            }
            // Add the number of valid pairs ending at the current right index
            count += right - left
        }

        return count
    }
}
