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

import java.util.TreeSet
import kotlin.math.abs
import kotlin.math.min

/**
 * 2035. Partition Array Into Two Arrays to Minimize Sum Difference
 * @see <a href="https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference">
 *     Source</a>
 */
fun interface MinimizeSumDifference {
    operator fun invoke(nums: IntArray): Int
}

class MinimizeSumDifferenceSolution : MinimizeSumDifference {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        if (n == 2) return abs(nums[1] - nums[0])
        val lists1 = generate(nums.copyOfRange(0, n / 2))
        val lists2 = generate(nums.copyOfRange(n / 2, n))
        var ans = Int.MAX_VALUE
        for (d in 0..n / 2) {
            val arr1 = lists1[d]
            val arr2 = lists2[d]
            val k = arr1!!.size
            var i1 = 0
            var i2 = 0 // we use two pointers to find two elements in arr1, arr2 with minimum absolute difference
            while (i1 < k && i2 < k) {
                val diff = arr1[i1] - arr2!![i2]
                ans = min(ans, abs(diff))
                if (diff <= 0) i1++
                if (diff >= 0) i2++
            }
        }
        return ans
    }

    private fun generate(nums: IntArray): Array<IntArray?> {
        val n = nums.size
        var total = 0
        for (num in nums) total += num
        val ans = arrayOfNulls<IntArray>(n + 1)
        val pos = IntArray(n + 1)
        var i = 0
        var binomial = 1
        while (i <= n) {
            ans[i] = IntArray(binomial) // number of ways to choose i from n = binomial(i,n)
            binomial = binomial * (n - i) / (i + 1)
            i++
        }
        val maxValue = 1 shl n
        for (key in 0 until maxValue) {
            var sum1 = 0
            for (i0 in 0 until n) {
                if (key shr i0 and 1 == 1) sum1 += nums[i0]
            }
            val sum2 = total - sum1
            val bits = Integer.bitCount(key)
            ans[bits]!![pos[bits]++] = sum1 - sum2
        }
        for (arr in ans) arr?.sort()
        return ans
    }
}

class MinimizeSumDifferenceTree : MinimizeSumDifference {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var sum = 0
        for (i in nums) {
            sum += i
        }

        val sets: Array<TreeSet<Int>?> = arrayOfNulls(n / 2 + 1)
        for (i in 0 until (1 shl n) / 2) {
            var curSum = 0
            var m = 0
            for (j in 0 until n / 2) {
                if (i and (1 shl j) != 0) {
                    curSum += nums[j]
                    m++
                }
            }
            if (sets[m] == null) sets[m] = TreeSet<Int>()
            sets[m]?.add(curSum)
        }

        var res = Int.MAX_VALUE / 3
        for (i in 0 until (1 shl n) / 2) {
            var curSum = 0
            var m = 0
            for (j in 0 until n / 2) {
                if (i and (1 shl j) != 0) {
                    curSum += nums[n / 2 + j]
                    m++
                }
            }
            val target = (sum - 2 * curSum) / 2
            val left: Int? = sets[n / 2 - m]?.floor(target)
            val right: Int? = sets[n / 2 - m]?.ceiling(target)
            if (left != null) {
                res = min(res, abs(sum - 2 * (curSum + left)))
            }
            if (right != null) {
                res = min(res, abs(sum - 2 * (curSum + right)))
            }
            if (res == 0) return 0
        }

        return res
    }
}
