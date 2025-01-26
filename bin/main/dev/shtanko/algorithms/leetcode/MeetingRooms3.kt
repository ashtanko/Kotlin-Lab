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

import java.util.PriorityQueue

/**
 * 2402. Meeting Rooms III
 * @see <a href="https://leetcode.com/problems/meeting-rooms-iii">Source</a>
 */
fun interface MeetingRooms3 {
    operator fun invoke(n: Int, meetings: Array<IntArray>): Int
}

class MeetingRooms3Counting : MeetingRooms3 {
    override fun invoke(n: Int, meetings: Array<IntArray>): Int {
        val roomAvailabilityTime = LongArray(n)
        val meetingCount = IntArray(n)
        meetings.sortBy {
            it[0]
        }
        for (meeting in meetings) {
            val start = meeting[0]
            val end = meeting[1]
            var minRoomAvailabilityTime = Long.MAX_VALUE
            var minAvailableTimeRoom = 0
            var foundUnusedRoom = false

            for (i in 0 until n) {
                if (roomAvailabilityTime[i] <= start) {
                    foundUnusedRoom = true
                    meetingCount[i]++
                    roomAvailabilityTime[i] = end.toLong()
                    break
                }

                if (minRoomAvailabilityTime > roomAvailabilityTime[i]) {
                    minRoomAvailabilityTime = roomAvailabilityTime[i]
                    minAvailableTimeRoom = i
                }
            }

            if (!foundUnusedRoom) {
                roomAvailabilityTime[minAvailableTimeRoom] += (end - start).toLong()
                meetingCount[minAvailableTimeRoom]++
            }
        }

        var maxMeetingCount = 0
        var maxMeetingCountRoom = 0
        for (i in 0 until n) {
            if (meetingCount[i] > maxMeetingCount) {
                maxMeetingCount = meetingCount[i]
                maxMeetingCountRoom = i
            }
        }

        return maxMeetingCountRoom
    }
}

class MeetingRooms3PQ : MeetingRooms3 {
    override fun invoke(n: Int, meetings: Array<IntArray>): Int {
        val meetingCount = IntArray(n)
        val usedRooms: PriorityQueue<LongArray> = PriorityQueue<LongArray> { a, b ->
            if (a[0] != b[0]) {
                a[0].compareTo(b[0])
            } else {
                a[1].compareTo(b[1])
            }
        }
        val unusedRooms: PriorityQueue<Int> = PriorityQueue<Int>()

        for (i in 0 until n) {
            unusedRooms.offer(i)
        }

        meetings.sortBy {
            it[0]
        }

        for (meeting in meetings) {
            val start = meeting[0]
            val end = meeting[1]

            while (usedRooms.isNotEmpty() && usedRooms.peek()[0] <= start) {
                unusedRooms.offer(usedRooms.poll()[1].toInt())
            }

            if (unusedRooms.isNotEmpty()) {
                val room: Int = unusedRooms.poll()
                usedRooms.offer(longArrayOf(end.toLong(), room.toLong()))
                meetingCount[room]++
            } else {
                val roomAvailabilityTime: Long = usedRooms.peek()[0]
                val room = usedRooms.poll()[1]
                usedRooms.offer(longArrayOf(roomAvailabilityTime + end - start, room))
                meetingCount[room.toInt()]++
            }
        }

        var maxMeetingCount = 0
        var maxMeetingCountRoom = 0
        for (i in 0 until n) {
            if (meetingCount[i] > maxMeetingCount) {
                maxMeetingCount = meetingCount[i]
                maxMeetingCountRoom = i
            }
        }

        return maxMeetingCountRoom
    }
}
