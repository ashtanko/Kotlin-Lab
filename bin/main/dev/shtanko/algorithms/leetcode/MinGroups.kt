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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2406. Divide Intervals Into Minimum Number of Groups
 * @see <a href="https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/">Source</a>
 */
@Medium("https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups")
fun interface MinGroups {
    operator fun invoke(intervals: Array<IntArray>): Int
}

/**
 * Approach 1: Sorting or Priority Queue
 */
class MinGroupsSort : MinGroups {
    override fun invoke(intervals: Array<IntArray>): Int {
        val events = mutableListOf<IntArray>()

        for (interval in intervals) {
            events.add(intArrayOf(interval[0], 1)) // Start event
            events.add(intArrayOf(interval[1] + 1, -1)) // End event (interval[1] + 1)
        }

        // Sort the events first by time, and then by type (1 for start, -1 for end)
        events.sortWith(compareBy({ it[0] }, { it[1] }))

        var concurrentIntervals = 0
        var maxConcurrentIntervals = 0

        // Sweep through the events
        for (event in events) {
            concurrentIntervals += event[1] // Track currently active intervals
            maxConcurrentIntervals = maxOf(maxConcurrentIntervals, concurrentIntervals) // Update max
        }

        return maxConcurrentIntervals
    }
}

/**
 * Approach 2: Line Sweep Algorithm With Ordered Container
 */
class MinGroupsLineSweep : MinGroups {
    override fun invoke(intervals: Array<IntArray>): Int {
        val pointToCount = sortedMapOf<Int, Int>()

        // Mark the starting and ending points in the TreeMap
        for (interval in intervals) {
            pointToCount[interval[0]] = pointToCount.getOrDefault(interval[0], 0) + 1
            pointToCount[interval[1] + 1] = pointToCount.getOrDefault(interval[1] + 1, 0) - 1
        }

        var concurrentIntervals = 0
        var maxConcurrentIntervals = 0

        // Iterate over the entries of the TreeMap in ascending order of keys
        for ((_, value) in pointToCount) {
            concurrentIntervals += value // Update currently active intervals
            maxConcurrentIntervals = maxOf(maxConcurrentIntervals, concurrentIntervals) // Update max intervals
        }

        return maxConcurrentIntervals
    }
}

/**
 * Approach 3: Line Sweep Algorithm Without Ordered Container
 */
class MinGroupsLineSweep2 : MinGroups {
    override fun invoke(intervals: Array<IntArray>): Int {
        var rangeStart = Int.MAX_VALUE
        var rangeEnd = Int.MIN_VALUE
        for (interval in intervals) {
            rangeStart = minOf(rangeStart, interval[0])
            rangeEnd = maxOf(rangeEnd, interval[1])
        }

        // Initialize the array with all zeroes
        val pointToCount = IntArray(rangeEnd + 2)
        for (interval in intervals) {
            pointToCount[interval[0]]++ // Increment at the start of the interval
            pointToCount[interval[1] + 1]-- // Decrement right after the end of the interval
        }

        var concurrentIntervals = 0
        var maxConcurrentIntervals = 0
        for (i in rangeStart..rangeEnd) {
            // Update currently active intervals
            concurrentIntervals += pointToCount[i]
            maxConcurrentIntervals = maxOf(maxConcurrentIntervals, concurrentIntervals)
        }

        return maxConcurrentIntervals
    }
}
