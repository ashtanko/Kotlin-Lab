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
import java.util.PriorityQueue
import java.util.TreeSet

/**
 * 1942. The Number of the Smallest Unoccupied Chair
 * @see <a href="https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/">Source</a>
 */
@Medium("https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair")
fun interface SmallestChair {
    operator fun invoke(times: Array<IntArray>, targetFriend: Int): Int
}

class SmallestChairSet : SmallestChair {
    override fun invoke(times: Array<IntArray>, targetFriend: Int): Int {
        val targetArrival = times[targetFriend][0]
        times.sortBy { it[0] }

        var nextChair = 0
        val leavingQueue = PriorityQueue(compareBy<IntArray> { it[0] })
        val availableChairs = TreeSet<Int>()

        for (time in times) {
            val arrival = time[0]
            val leave = time[1]

            // Free up chairs based on current time
            while (leavingQueue.isNotEmpty() && leavingQueue.peek()[0] <= arrival) {
                availableChairs.add(leavingQueue.poll()[1])
            }

            val currentChair = if (availableChairs.isNotEmpty()) {
                availableChairs.pollFirst()
            } else {
                nextChair++
                nextChair - 1
            }

            // Push current leave time and chair
            leavingQueue.offer(intArrayOf(leave, currentChair))

            // Check if it's the target friend
            if (arrival == targetArrival) return currentChair
        }

        return 0
    }
}
