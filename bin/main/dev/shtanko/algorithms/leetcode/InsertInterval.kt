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

import kotlin.math.max

/**
 * 57. Insert Interval
 * @see <a href="https://leetcode.com/problems/insert-interval">Source</a>
 */
fun interface InsertInterval {
    operator fun invoke(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray>
}

class InsertIntervalLinearSearch : InsertInterval {
    override fun invoke(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val size = intervals.size
        var i = 0
        val res = mutableListOf<IntArray>()

        // Case 1: No overlapping before merging intervals
        while (i < size && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i])
            i++
        }

        // Case 2: Overlapping and merging intervals
        while (i < size && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = minOf(newInterval[0], intervals[i][0])
            newInterval[1] = maxOf(newInterval[1], intervals[i][1])
            i++
        }
        res.add(newInterval)

        // Case 3: No overlapping after merging newInterval
        while (i < size) {
            res.add(intervals[i])
            i++
        }

        return res.toTypedArray()
    }
}

class InsertIntervalBinarySearch : InsertInterval {
    override fun invoke(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        // If the intervals vector is empty, return a vector containing the newInterval
        if (intervals.isEmpty()) {
            return arrayOf(newInterval)
        }

        val size: Int = intervals.size
        val target = newInterval[0]
        var left = 0
        var right = size - 1

        // Binary search to find the position to insert newInterval
        while (left <= right) {
            val mid = (left + right) / 2
            if (intervals[mid][0] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        // Insert newInterval at the found position
        val result: MutableList<IntArray> = ArrayList()
        for (i in 0 until left) {
            result.add(intervals[i])
        }
        result.add(newInterval)
        for (i in left until size) {
            result.add(intervals[i])
        }

        // Merge overlapping intervals
        val merged: MutableList<IntArray> = ArrayList()
        for (interval in result) {
            // If res is empty or there is no overlap, add the interval to the result
            if (merged.isEmpty() || merged[merged.size - 1][1] < interval[0]) {
                merged.add(interval)
                // If there is an overlap, merge the intervals by updating the end of the last interval in res
            } else {
                merged[merged.size - 1][1] =
                    max(merged[merged.size - 1][1].toDouble(), interval[1].toDouble()).toInt()
            }
        }

        return merged.toTypedArray<IntArray>()
    }
}
