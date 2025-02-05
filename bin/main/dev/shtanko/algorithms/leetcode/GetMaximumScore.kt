/*
 * Copyright 2020 Oleksii Shtanko
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

import java.util.LinkedList
import kotlin.math.max

// Get the Maximum Score
class GetMaximumScore {

    fun maxSum(nums1: IntArray, nums2: IntArray): Int {
        val map: MutableMap<Int, MutableList<Int>> = HashMap()
        for (i in 0 until nums1.size - 1) map.computeIfAbsent(
            nums1[i],
        ) { LinkedList() }.add(nums1[i + 1])
        for (i in 0 until nums2.size - 1) map.computeIfAbsent(
            nums2[i],
        ) { LinkedList() }.add(nums2[i + 1])
        val memo: MutableMap<Int?, Long?> = HashMap()
        return max(greedy(nums1[0], map, memo) % M, greedy(nums2[0], map, memo) % M).toInt()
    }

    private fun greedy(cur: Int, map: MutableMap<Int, MutableList<Int>>, memo: MutableMap<Int?, Long?>): Long {
        if (memo.containsKey(cur)) return memo[cur]!!
        if (!map.containsKey(cur)) return cur.toLong()
        var maxSum: Long = 0
        for (next in map[cur]!!) {
            maxSum = max(maxSum, greedy(next, map, memo))
        }
        maxSum += cur.toLong()
        memo[cur] = maxSum
        return maxSum
    }

    companion object {
        private const val M = 1e9.toInt() + 7
    }
}
