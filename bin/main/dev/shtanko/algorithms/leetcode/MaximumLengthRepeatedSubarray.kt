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

import dev.shtanko.algorithms.MOD
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min

/**
 * 718. Maximum Length of Repeated Subarray
 * @see <a href="https://leetcode.com/problems/maximum-length-of-repeated-subarray/">Source</a>
 */
fun interface MaximumLengthRepeatedSubarray {
    fun findLength(nums1: IntArray, nums2: IntArray): Int
}

sealed class MaximumLengthRepeatedSubarrayStrategy {

    /**
     * Approach #1: Brute Force with Initial Character Map [Time Limit Exceeded]
     */
    class BruteForce : MaximumLengthRepeatedSubarray {
        override fun findLength(nums1: IntArray, nums2: IntArray): Int {
            var ans = 0
            val bstarts: MutableMap<Int, ArrayList<Int>> = HashMap()
            for (j in nums2.indices) {
                bstarts.computeIfAbsent(
                    nums2[j],
                ) { ArrayList() }.add(j)
            }

            for (i in nums1.indices) if (bstarts.containsKey(nums1[i])) {
                for (j in bstarts[nums1[i]]!!) {
                    var k = 0
                    while (i + k < nums1.size && j + k < nums2.size && nums1[i + k] == nums2[j + k]) {
                        k++
                    }
                    ans = max(ans, k)
                }
            }
            return ans
        }
    }

    /**
     * Approach #2: Binary Search with Naive Check [Time Limit Exceeded]
     */
    class BinarySearch : MaximumLengthRepeatedSubarray {
        override fun findLength(nums1: IntArray, nums2: IntArray): Int {
            var lo = 0
            var hi: Int = min(nums1.size, nums2.size) + 1
            while (lo < hi) {
                val mi = (lo + hi) / 2
                if (check(mi, nums1, nums2)) lo = mi + 1 else hi = mi
            }
            return lo - 1
        }

        fun check(length: Int, nums1: IntArray, nums2: IntArray): Boolean {
            val seen: MutableSet<String> = HashSet()
            var i = 0
            while (i + length <= nums1.size) {
                seen.add(nums1.copyOfRange(i, i + length).contentToString())
                ++i
            }
            var j = 0
            while (j + length <= nums2.size) {
                if (seen.contains(nums2.copyOfRange(j, j + length).contentToString())) {
                    return true
                }
                ++j
            }
            return false
        }
    }

    /**
     * Approach #3: Dynamic Programming (Accepted)
     */
    class DynamicProgramming : MaximumLengthRepeatedSubarray {
        override fun findLength(nums1: IntArray, nums2: IntArray): Int {
            var ans = 0
            val memo = Array(nums1.size + 1) { IntArray(nums2.size + 1) }
            for (i in nums1.size - 1 downTo 0) {
                for (j in nums2.size - 1 downTo 0) {
                    if (nums1[i] == nums2[j]) {
                        memo[i][j] = memo[i + 1][j + 1] + 1
                        if (ans < memo[i][j]) ans = memo[i][j]
                    }
                }
            }
            return ans
        }
    }

    /**
     * Approach #4: Binary Search with Rolling Hash [Accepted]
     */
    class DPRollingHash : MaximumLengthRepeatedSubarray {

        companion object {
            const val P = 113L
        }

        var pinv: Int = BigInteger.valueOf(P).modInverse(BigInteger.valueOf(MOD.toLong())).intValueExact()

        override fun findLength(nums1: IntArray, nums2: IntArray): Int {
            var lo = 0
            var hi: Int = min(nums1.size, nums2.size) + 1
            while (lo < hi) {
                val mi = (lo + hi) / 2
                if (check(mi, nums1, nums2)) lo = mi + 1 else hi = mi
            }
            return lo - 1
        }

        private fun rolling(source: IntArray, length: Int): IntArray {
            val ans = IntArray(source.size - length + 1)
            var h: Long = 0
            var power: Long = 1
            if (length == 0) return ans
            for (i in source.indices) {
                h = (h + source[i] * power) % MOD
                if (i < length - 1) {
                    power = power * P % MOD
                } else {
                    ans[i - (length - 1)] = h.toInt()
                    h = (h - source[i - (length - 1)]) * pinv % MOD
                    if (h < 0) h += MOD
                }
            }
            return ans
        }

        private fun check(guess: Int, a: IntArray, b: IntArray): Boolean {
            val hashes: MutableMap<Int?, MutableList<Int>?> = HashMap()
            var k = 0
            for (x in rolling(a, guess)) {
                hashes.computeIfAbsent(x) { ArrayList() }?.add(k++)
            }
            for ((j, x) in rolling(b, guess).withIndex()) {
                for (i in hashes.getOrDefault(x, ArrayList())!!) {
                    if (a.copyOfRange(i, i + guess).contentEquals(b.copyOfRange(j, j + guess))) {
                        return true
                    }
                }
            }
            return false
        }
    }
}
