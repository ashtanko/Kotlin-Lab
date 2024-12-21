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

import kotlin.math.max

/**
 * Maximum Erasure Value
 * @see <a href="https://leetcode.com/problems/maximum-erasure-value/">Source</a>
 */
fun interface MaximumErasureValue {
    fun maximumUniqueSubarray(nums: IntArray): Int
}

/**
 * Approach 1: Brute Force
 */
class MEVBruteForce : MaximumErasureValue {
    override fun maximumUniqueSubarray(nums: IntArray): Int {
        val n: Int = nums.size
        var result = 0
        val set: HashSet<Int> = HashSet()
        for (start in 0 until n) {
            // reset set and current sum for next subarray
            set.clear()
            var currentSum = 0
            var end = start
            while (end < n && !set.contains(nums[end])) {
                currentSum += nums[end]
                set.add(nums[end])
                end++
            }
            // update result with maximum sum found so far
            result = max(result, currentSum)
        }
        return result
    }
}

/**
 * Approach 2: Two Pointer Approach Using Set
 */
class MEVTwoPointerSet : MaximumErasureValue {
    override fun maximumUniqueSubarray(nums: IntArray): Int {
        var result = 0
        var currentSum = 0
        val set = HashSet<Int>()
        var start = 0
        for (end in nums.indices) {
            // increment start until subarray has unique elements
            while (set.contains(nums[end])) {
                set.remove(nums[start])
                currentSum -= nums[start]
                start++
            }
            currentSum += nums[end]
            set.add(nums[end])
            // update result with maximum sum found so far
            result = max(result, currentSum)
        }
        return result
    }
}

/**
 * Approach 3: Two Pointer Approach Using Boolean Array
 */
class MEVTwoPointerBooleanArray : MaximumErasureValue {
    override fun maximumUniqueSubarray(nums: IntArray): Int {
        var result = 0
        var currentSum = 0
        val isPresent = BooleanArray(ARRAY_SIZE)
        var start = 0
        for (end in nums.indices) {
            // increment start until subarray has unique elements
            while (isPresent[nums[end]]) {
                isPresent[nums[start]] = false
                currentSum -= nums[start]
                start++
            }
            isPresent[nums[end]] = true
            currentSum += nums[end]
            // update result with maximum sum found so far
            result = max(result, currentSum)
        }
        return result
    }

    companion object {
        private const val ARRAY_SIZE = 10001
    }
}

/**
 * Approach 4: Two Pointer Approach Using Count Map
 */
class MEVTwoPointerCountMap : MaximumErasureValue {
    override fun maximumUniqueSubarray(nums: IntArray): Int {
        val countMap = IntArray(ARRAY_SIZE)
        var start = 0
        var result = 0
        var currentSum = 0
        for (end in nums.indices) {
            val currentElement = nums[end]
            countMap[currentElement]++
            currentSum += currentElement
            while (start < end && countMap[currentElement] > 1) {
                countMap[nums[start]]--
                currentSum -= nums[start]
                start++
            }
            // update result with maximum sum found so far
            result = max(result, currentSum)
        }
        return result
    }

    companion object {
        private const val ARRAY_SIZE = 10001
    }
}

/**
 * Approach 5: Using Prefix Sum with HashMap
 */
class MEVPrefixSum : MaximumErasureValue {
    override fun maximumUniqueSubarray(nums: IntArray): Int {
        val n: Int = nums.size
        val lastIndexMap = HashMap<Int, Int>()
        val prefixSum = IntArray(n + 1)

        var result = 0
        var start = 0
        for (end in 0 until n) {
            val currentElement = nums[end]
            prefixSum[end + 1] = prefixSum[end] + currentElement
            if (lastIndexMap.containsKey(currentElement)) {
                start = max(start, lastIndexMap[currentElement]!! + 1)
            }
            // update result with maximum sum found so far
            result = max(result, prefixSum[end + 1] - prefixSum[start])
            lastIndexMap[currentElement] = end
        }
        return result
    }
}

/**
 * Approach 6: Using Prefix Sum with Count Array
 */
class MEVPrefixSumCountArray : MaximumErasureValue {
    override fun maximumUniqueSubarray(nums: IntArray): Int {
        val n: Int = nums.size
        val lastIndexes = IntArray(ARRAY_SIZE) { -1 }
        val prefixSum = IntArray(n + 1)
        var result = 0
        var start = 0
        for (end in 0 until n) {
            val currentElement = nums[end]
            prefixSum[end + 1] = prefixSum[end] + currentElement
            if (lastIndexes[currentElement] != -1) {
                start = max(start, lastIndexes[currentElement] + 1)
            }
            // update result with maximum sum found so far
            result = max(result, prefixSum[end + 1] - prefixSum[start])
            // update last index of current element
            lastIndexes[currentElement] = end
        }
        return result
    }

    companion object {
        private const val ARRAY_SIZE = 10001
    }
}
