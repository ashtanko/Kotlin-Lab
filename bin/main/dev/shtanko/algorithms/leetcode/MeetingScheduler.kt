/*
 * Copyright 2021 Oleksii Shtanko
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
import kotlin.math.max
import kotlin.math.min

/**
 * Meeting Scheduler
 * @see <a href="https://leetcode.com/problems/meeting-scheduler/">Source</a>
 */
fun interface MeetingScheduler {
    operator fun invoke(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int>
}

/**
 * Approach 1: Two pointers
 */
class MSTwoPointers : MeetingScheduler {
    override operator fun invoke(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int> {
        slots1.sortWith { a, b -> a[0] - b[0] }
        slots2.sortWith { a, b -> a[0] - b[0] }

        var pointer1 = 0
        var pointer2 = 0

        while (pointer1 < slots1.size && pointer2 < slots2.size) {
            // find the boundaries of the intersection, or the common slot
            val intersectLeft = max(slots1[pointer1][0], slots2[pointer2][0])
            val intersectRight = min(slots1[pointer1][1], slots2[pointer2][1])
            if (intersectRight - intersectLeft >= duration) {
                return listOf(intersectLeft, intersectLeft + duration)
            }
            // always move the one that ends earlier
            if (slots1[pointer1][1] < slots2[pointer2][1]) {
                pointer1++
            } else {
                pointer2++
            }
        }
        return emptyList()
    }
}

/**
 * Approach 2: Heap
 */
class MSHeap : MeetingScheduler {
    override operator fun invoke(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int> {
        val timeslots: PriorityQueue<IntArray> = PriorityQueue { slot1, slot2 -> slot1[0] - slot2[0] }

        for (slot in slots1) {
            if (slot[1] - slot[0] >= duration) timeslots.offer(slot)
        }
        for (slot in slots2) {
            if (slot[1] - slot[0] >= duration) timeslots.offer(slot)
        }

        while (timeslots.size > 1) {
            val slot1: IntArray = timeslots.poll()
            val slot2: IntArray = timeslots.peek()
            if (slot1[1] >= slot2[0] + duration) {
                return listOf(slot2[0], slot2[0] + duration)
            }
        }
        return emptyList()
    }
}
