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

import kotlin.math.pow

/**
 * 1062. Longest Repeating Substring
 * @see <a href="https://leetcode.com/problems/longest-repeating-substring/">Source</a>
 */
interface LongestRepeatingSubstring {
    operator fun invoke(s: String): Int {
        val n = s.length
        var left = 1
        var right = n
        var l: Int
        while (left <= right) {
            l = left + right.minus(left) / 2
            if (search(l, n, s) != -1) {
                left = l + 1
            } else {
                right = l - 1
            }
        }
        return left - 1
    }

    fun search(l: Int, n: Int, s: String): Int {
        return -1
    }
}

/**
 * Approach 1: Binary Search + Hashset of Already Seen Strings
 */
class LRSBinarySearch : LongestRepeatingSubstring {

    override fun search(l: Int, n: Int, s: String): Int {
        val seen: MutableSet<String> = HashSet()
        var tmp: String
        for (start in 0 until n - l + 1) {
            tmp = s.substring(start, start + l)
            if (seen.contains(tmp)) return start
            seen.add(tmp)
        }
        return -1
    }
}

/**
 * Approach 2: Binary Search + Hashset of Hashes of Already Seen Strings
 */
class LRSHashes : LongestRepeatingSubstring {

    override fun search(l: Int, n: Int, s: String): Int {
        val seen: MutableSet<Int> = HashSet()
        var tmp: String
        var hc: Int
        for (start in 0 until n - l + 1) {
            tmp = s.substring(start, start + l)
            hc = tmp.hashCode()
            if (seen.contains(hc)) return start
            seen.add(hc)
        }
        return super.search(l, n, s)
    }
}

/**
 * Approach 3: Binary Search + Rabin-Karp
 */
class LRSRabinKarp : LongestRepeatingSubstring {

    override operator fun invoke(s: String): Int {
        val n = s.length
        // convert string to array of integers
        // to implement constant time slice
        val nums = IntArray(n)
        for (i in 0 until n) nums[i] = s[i].code - 'a'.code
        // modulus value for the rolling hash function to avoid overflow
        val modulus = 2.0.pow(HASH_ROLLING_MODULUS).toLong()

        // binary search, L = repeating string length
        var left = 1
        var right = n
        var l: Int
        while (left <= right) {
            l = left + (right - left) / 2
            if (search(l, modulus, n, nums) != -1) left = l + 1 else right = l - 1
        }
        return left - 1
    }

    private fun search(l: Int, modulus: Long, n: Int, nums: IntArray): Int {
        // compute the hash of string S[:L]
        var h: Long = 0
        for (i in 0 until l) h = (h * HASH_ROLLING_NUM + nums[i]) % modulus

        // already seen hashes of strings of length L
        val seen: HashSet<Long> = HashSet()
        seen.add(h)
        // const value to be used often : a**L % modulus
        var aL: Long = 1
        for (i in 1..l) aL = aL * HASH_ROLLING_NUM % modulus
        for (start in 1 until n - l + 1) {
            // compute rolling hash in O(1) time
            h = (h * HASH_ROLLING_NUM - nums[start - 1] * aL % modulus + modulus) % modulus
            h = (h + nums[start + l - 1]) % modulus
            if (seen.contains(h)) return start
            seen.add(h)
        }
        return -1
    }

    companion object {
        private const val HASH_ROLLING_NUM = 26
        private const val HASH_ROLLING_MODULUS = 24.0
    }
}
