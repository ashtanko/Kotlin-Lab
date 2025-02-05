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

/**
 * 327. Count of Range Sum
 * @see <a href="https://leetcode.com/problems/count-of-range-sum">Source</a>
 */
fun interface CountRangeSum {
    operator fun invoke(nums: IntArray, lower: Int, upper: Int): Int
}

class CountRangeSumSegmentTree : CountRangeSum {
    override operator fun invoke(nums: IntArray, lower: Int, upper: Int): Int {
        if (nums.isEmpty()) return 0
        var ans = 0
        val valSet: MutableSet<Long> = HashSet()
        var sum: Long = 0
        for (i in nums.indices) {
            sum += nums[i].toLong()
            valSet.add(sum)
        }
        val valArr = valSet.toTypedArray()
        valArr.sort()
        val root = buildSegmentTree(valArr, 0, valArr.size - 1)
        for (i in nums.indices.reversed()) {
            updateSegmentTree(root, sum)
            sum -= nums[i].toLong()
            ans += getCount(root, lower.toLong() + sum, upper.toLong() + sum)
        }
        return ans
    }

    private fun buildSegmentTree(valArr: Array<Long>, low: Int, high: Int): SegmentTreeNode? {
        if (low > high) return null
        val stn = SegmentTreeNode(valArr[low], valArr[high])
        if (low == high) return stn
        val mid = (low + high) / 2
        stn.left = buildSegmentTree(valArr, low, mid)
        stn.right = buildSegmentTree(valArr, mid + 1, high)
        return stn
    }

    private fun updateSegmentTree(stn: SegmentTreeNode?, value: Long) {
        if (stn == null) return
        if (value >= stn.min && value <= stn.max) {
            stn.count++
            updateSegmentTree(stn.left, value)
            updateSegmentTree(stn.right, value)
        }
    }

    private fun getCount(stn: SegmentTreeNode?, min: Long, max: Long): Int {
        if (stn == null) return 0
        if (min > stn.max || max < stn.min) return 0
        return if (min <= stn.min && max >= stn.max) {
            stn.count
        } else {
            getCount(stn.left, min, max) + getCount(
                stn.right,
                min,
                max,
            )
        }
    }

    private data class SegmentTreeNode(var min: Long, var max: Long) {
        var left: SegmentTreeNode? = null
        var right: SegmentTreeNode? = null
        var count = 0
    }
}
