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

import java.util.PriorityQueue
import java.util.TreeSet
import kotlin.math.max
import kotlin.math.min

/**
 * 391. Perfect Rectangle
 * @see <a href="https://leetcode.com/problems/perfect-rectangle/">Source</a>
 */
fun interface PerfectRectangle {
    fun isRectangleCover(rectangles: Array<IntArray>): Boolean
}

class PerfectRectangleSweepLine : PerfectRectangle {
    override fun isRectangleCover(rectangles: Array<IntArray>): Boolean {
        val pq: PriorityQueue<Event> = PriorityQueue<Event>()
        // border of y-intervals
        val border = intArrayOf(Int.MAX_VALUE, Int.MIN_VALUE)
        if (rectangles.size == 1 && rectangles.first().isEmpty()) {
            return false
        }
        for (rect in rectangles) {
            val e1 = Event(rect[0], rect)
            val e2 = Event(rect[2], rect)
            pq.add(e1)
            pq.add(e2)
            if (rect[1] < border[0]) border[0] = rect[1]
            if (rect[3] > border[1]) border[1] = rect[3]
        }
        val set = TreeSet(
            Comparator<IntArray> { rect1, rect2 ->
                // if two y-intervals intersects, return 0
                if (rect1[3] <= rect2[1]) -1 else if (rect2[3] <= rect1[1]) 1 else 0
            },
        )
        var yRange = 0
        while (pq.isNotEmpty()) {
            val time: Int = pq.peek().time
            while (pq.isNotEmpty() && pq.peek().time == time) {
                val (_, rect) = pq.poll()
                if (time == rect[2]) {
                    set.remove(rect)
                    yRange -= rect[3] - rect[1]
                } else {
                    if (!set.add(rect)) return false
                    yRange += rect[3] - rect[1]
                }
            }
            // check intervals' range
            if (pq.isNotEmpty() && yRange != border[1] - border[0]) {
                return false
            }
        }
        return rectangles.isNotEmpty()
    }

    data class Event(var time: Int, var rect: IntArray) : Comparable<Event> {
        override operator fun compareTo(other: Event): Int {
            return if (time != other.time) time - other.time else rect[0] - other.rect[0]
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Event

            if (time != other.time) return false
            if (!rect.contentEquals(other.rect)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = time
            result = HASH_CODE_VALUE * result + rect.contentHashCode()
            return result
        }

        companion object {
            private const val HASH_CODE_VALUE = 31
        }
    }
}

class PerfectRectangleEasy : PerfectRectangle {
    override fun isRectangleCover(rectangles: Array<IntArray>): Boolean {
        if (rectangles.isEmpty() || rectangles[0].isEmpty()) return false

        var x1 = Int.MAX_VALUE
        var x2 = Int.MIN_VALUE
        var y1 = Int.MAX_VALUE
        var y2 = Int.MIN_VALUE

        val set = HashSet<String>()
        var area = 0

        for (rect in rectangles) {
            x1 = min(rect[0], x1)
            y1 = min(rect[1], y1)
            x2 = max(rect[2], x2)
            y2 = max(rect[3], y2)
            area += (rect[2] - rect[0]) * (rect[3] - rect[1])
            val s1 = rect[0].toString() + " " + rect[1]
            val s2 = rect[0].toString() + " " + rect[3]
            val s3 = rect[2].toString() + " " + rect[3]
            val s4 = rect[2].toString() + " " + rect[1]
            if (!set.add(s1)) set.remove(s1)
            if (!set.add(s2)) set.remove(s2)
            if (!set.add(s3)) set.remove(s3)
            if (!set.add(s4)) set.remove(s4)
        }

        val x1y1 = !set.contains("$x1 $y1")
        val x1y2 = !set.contains("$x1 $y2")
        val x2y1 = !set.contains("$x2 $y1")
        val x2y2 = !set.contains("$x2 $y2")
        val condition1 = x1y1 || x1y2 || x2y1 || x2y2 || set.size != 4
        return if (condition1) {
            false
        } else {
            area == (x2 - x1) * (y2 - y1)
        }
    }
}
