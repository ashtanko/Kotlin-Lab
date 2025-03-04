/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.PriorityQueue

/**
 * 2054. Two Best Non-Overlapping Events
 * @see <a href="https://leetcode.com/problems/two-best-non-overlapping-events/">Source</a>
 */
@Medium("https://leetcode.com/problems/two-best-non-overlapping-events/")
sealed interface MaxTwoEvents {
    operator fun invoke(events: Array<IntArray>): Int

    @dev.shtanko.algorithms.annotations.TopDownDP
    data object TopDownDP : MaxTwoEvents {
        override fun invoke(events: Array<IntArray>): Int {
            events.sortBy { it[0] } // Sort events by start time
            val dp = Array(events.size) { IntArray(3) { -1 } } // Initialize DP array with -1
            return findEvents(events, 0, 0, dp)
        }

        // Recursive function to find the greatest sum for the pairs
        private fun findEvents(events: Array<IntArray>, idx: Int, cnt: Int, dp: Array<IntArray>): Int {
            if (cnt == 2 || idx >= events.size) return 0
            if (dp[idx][cnt] == -1) {
                val end = events[idx][1]
                var lo = idx + 1
                var hi = events.size - 1

                // Binary search to find the first event that starts after the current one ends
                while (lo < hi) {
                    val mid = lo + ((hi - lo) shr 1)
                    if (events[mid][0] > end) {
                        hi = mid
                    } else lo = mid + 1
                }

                val include = events[idx][2] +
                    if (lo < events.size && events[lo][0] > end) {
                        findEvents(events, lo, cnt + 1, dp)
                    } else {
                        0
                    }
                val exclude = findEvents(events, idx + 1, cnt, dp)

                dp[idx][cnt] = maxOf(include, exclude)
            }
            return dp[idx][cnt]
        }
    }

    data object MinHeap : MaxTwoEvents {
        override fun invoke(events: Array<IntArray>): Int {
            // Create a PriorityQueue to store pairs of ending time and value
            val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

            // Sort the array in ascending order by start time
            events.sortBy { it[0] }

            var maxVal = 0
            var maxSum = 0

            for (event in events) {
                // Poll all valid events from the queue and take their maximum
                while (pq.isNotEmpty() && pq.peek().first < event[0]) {
                    maxVal = maxOf(maxVal, pq.poll().second)
                }

                // Update the maximum sum of two non-overlapping events
                maxSum = maxOf(maxSum, maxVal + event[2])

                // Add the current event's end time and value to the queue
                pq.add(event[1] to event[2])
            }

            return maxSum
        }
    }

    @dev.shtanko.algorithms.annotations.Greedy
    data object Greedy : MaxTwoEvents {
        override fun invoke(events: Array<IntArray>): Int {
            val times = mutableListOf<IntArray>()

            // Convert events into start and end times with their values
            for (event in events) {
                // 1 denotes start time
                times.add(intArrayOf(event[0], 1, event[2]))
                // 0 denotes end time
                times.add(intArrayOf(event[1] + 1, 0, event[2]))
            }

            // Sort the times: first by time, then by type (end before start for same time)
            times.sortWith(compareBy({ it[0] }, { it[1] }))

            var ans = 0
            var maxValue = 0

            // Process the sorted times
            for (timeValue in times) {
                if (timeValue[1] == 1) {
                    // Start time: Calculate the maximum sum
                    ans = maxOf(ans, timeValue[2] + maxValue)
                } else {
                    // End time: Update the maximum value seen so far
                    maxValue = maxOf(maxValue, timeValue[2])
                }
            }

            return ans
        }
    }
}
