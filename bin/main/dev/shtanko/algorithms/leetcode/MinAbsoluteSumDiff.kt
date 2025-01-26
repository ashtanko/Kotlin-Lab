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

import dev.shtanko.algorithms.MOD
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * 1818. Minimum Absolute Sum Difference
 * @see <a href="https://leetcode.com/problems/minimum-absolute-sum-difference/">Source</a>
 */
fun interface MinAbsoluteSumDiff {
    operator fun invoke(nums1: IntArray, nums2: IntArray): Int
}

class MinAbsoluteSumDiffSimple : MinAbsoluteSumDiff {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): Int {
        var res: Long = 0
        var maxIdx = -1
        var maxDiff = Int.MIN_VALUE
        for (i in nums1.indices) {
            val diff = abs(nums1[i] - nums2[i])
            res = (res + diff) % MOD
            if (diff > maxDiff) {
                maxDiff = diff
                maxIdx = i
            }
        }

        var ded = maxDiff
        for (num in nums1) {
            ded = min(ded, abs(num - nums2[maxIdx]))
        }

        res -= (maxDiff - ded).toLong()
        return res.toInt()
    }
}

class MinAbsoluteSumDiffBinarySearch : MinAbsoluteSumDiff {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): Int {
        var total = 0
        var best = 0
        val n = nums1.size
        val diff = mutableListOf<Int>()
        for (i in 0 until n) {
            diff.add(abs(nums1[i] - nums2[i]))
        }
        nums1.sort()
        for (i in 0 until n) {
            val j = lowerBound(nums1, nums2[i])
            val delta = diff[i] - min(
                if (0 <= j - 1) abs(nums1[j - 1] - nums2[i]) else MOD,
                if (j < n) abs(nums1[j] - nums2[i]) else MOD,
            )
            best = max(best, delta)
        }
        for (x in diff) {
            total = (total + x) % MOD
        }
        return total - best
    }

    private fun lowerBound(a: IntArray, target: Int): Int {
        val n = a.size
        var i = 0
        var j = n
        while (i < j) {
            val k = (i + j) / 2
            if (a[k] < target) {
                i = k + 1
            } else {
                j = k
            }
        }
        return i
    }
}

class MinAbsoluteSumDiffBinarySearch2 : MinAbsoluteSumDiff {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): Int {
        val len: Int = nums1.size
        var sum: Long = 0
        val diffArray = IntArray(len)
        for (i in 0 until len) {
            val diff = abs(nums1[i] - nums2[i])
            diffArray[i] = diff
            sum += diff.toLong()
        }
        if (sum == 0L) return 0
        val sortedNums1: IntArray = nums1.copyOf(len).sorted().toIntArray()
        var minSum = sum
        for (i in 0 until len) {
            var left = 0
            var right = len - 1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val diff = abs(sortedNums1[mid] - nums2[i])
                val newSum = sum - diffArray[i] + diff
                if (newSum < minSum) minSum = newSum
                if (nums2[i] < sortedNums1[mid]) right = mid - 1 else left = mid + 1
            }
        }
        return (minSum % MOD).toInt()
    }
}
