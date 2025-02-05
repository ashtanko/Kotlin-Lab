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

import kotlin.math.max
import kotlin.math.min

fun interface MeetingRoomsStrategy {
    fun canAttendMeetings(intervals: Array<IntArray>): Boolean
}

class MeetingRoomsBruteForce : MeetingRoomsStrategy {
    override fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        val n = intervals.size
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (overlap(intervals[i], intervals[j])) {
                    return false
                }
            }
        }
        return true
    }

    private fun overlap(i1: IntArray, i2: IntArray): Boolean {
        return min(i1[1], i2[1]) > max(i1[0], i2[0])
    }
}

class MeetingRoomsSorting : MeetingRoomsStrategy {
    override fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        intervals.sortWith { a, b ->
            a[0].compareTo(b[0])
        }
        for (i in 0 until intervals.size - 1) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false
            }
        }
        return true
    }
}
