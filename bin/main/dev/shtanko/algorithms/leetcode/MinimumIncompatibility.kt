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

import kotlin.math.min

/**
 * 1681. Minimum Incompatibility
 * @see <a href="https://leetcode.com/problems/minimum-incompatibility">Source</a>
 */
fun interface MinimumIncompatibility {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class MinimumIncompatibilityDFS : MinimumIncompatibility {

    private var n = 0
    private val memo: MutableMap<String, Int> = HashMap()
    private var k0 = 0

    override operator fun invoke(nums: IntArray, k: Int): Int {
        nums.sort()
        k0 = k
        val count = IntArray(LIMIT)
        for (num in nums) if (++count[num] > k) return -1
        // if same number more than k times, we can not answer the question cause there must be one bucket with same
        // number twice
        n = nums.size / k // n is the number of element in each bucket
        return dfs(HashSet(), count)
    }

    private fun dfs(level: MutableSet<Int>, count: IntArray): Int {
        if (level.size == n) {
            return if (end(count)) findDiff(level) else findDiff(level) + dfs(HashSet(), count)
        }
        // count is remaining element status, level is current hand bucket element
        val key = "${count.contentHashCode()} $level"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, -1)
        }
        var res = INITIAL
        for (i in 1 until LIMIT) {
            if (count[i] <= 0) continue // no more this number left
            if (!level.add(i)) continue // at hand same number already exist
            count[i]--
            res = min(res, dfs(level, count))
            count[i]++
            level.remove(i)
            if (level.isNotEmpty()) break // first element we don't need to expand
        }
        memo[key] = res
        return res
    }

    private fun findDiff(level: Set<Int>): Int {
        return level.max() - level.min()
    }

    private fun end(count: IntArray): Boolean {
        for (c in count) {
            if (c != 0) {
                return false
            }
        }
        return true
    }

    companion object {
        private const val INITIAL = 1000
        private const val LIMIT = 17
    }
}
