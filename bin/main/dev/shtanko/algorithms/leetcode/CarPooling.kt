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
import java.util.TreeMap

/**
 * 1094. Car Pooling
 * @see <a href="https://leetcode.com/problems/car-pooling/">Source</a>
 */
fun interface CarPooling {
    operator fun invoke(trips: Array<IntArray>, capacity: Int): Boolean
}

class ThousandOneStops : CarPooling {
    companion object {
        private const val STOPS = 1001
    }

    override operator fun invoke(trips: Array<IntArray>, capacity: Int): Boolean {
        val nums = IntArray(STOPS)
        for (trip in trips) {
            if (trip.isEmpty()) return false
            for (i in trip[1] until trip[2]) { // put load in index range [getUpStop, getOffStop)
                nums[i] += trip[0]
            }
        }
        for (num in nums) {
            if (num > capacity) return false
        }
        return true
    }
}

class CarPoolingMeetingRoom : CarPooling {
    override operator fun invoke(trips: Array<IntArray>, capacity: Int): Boolean {
        var c = capacity
        val m: MutableMap<Int, Int> = TreeMap()
        for (t in trips) {
            if (t.isEmpty()) return false
            m[t[1]] = (m[t[1]] ?: 0) + t[0]
            m[t[2]] = (m[t[2]] ?: 0) - t[0]
        }
        for (v in m.values) {
            c -= v
            if (c < 0) {
                return false
            }
        }
        return true
    }
}

class CarPoolingInterval : CarPooling {
    override operator fun invoke(trips: Array<IntArray>, capacity: Int): Boolean {
        if (trips.isEmpty() || capacity == 0) return false
        trips.sortWith(compareBy({ it[1] }, { it[2] }))
        val pq = PriorityQueue<IntArray>(compareBy({ it.first() }, { it.last() })).apply {
            trips.forEach {
                offer(intArrayOf(it[2], -it[0]))
                offer(intArrayOf(it[1], it[0]))
            }
        }
        var maxCount = 0
        while (pq.isNotEmpty()) {
            maxCount += pq.poll().last()
            if (maxCount > capacity) return false
        }

        return true
    }
}

class CarPoolingStream : CarPooling {
    companion object {
        private const val STOPS = 1001
    }

    override operator fun invoke(trips: Array<IntArray>, capacity: Int): Boolean {
        val count = IntArray(STOPS)
        for (t in trips) {
            if (t.isEmpty()) return false
            for (i in t[1] until t[2]) {
                count[i] += t[0]
            }
        }
        return count.max() <= capacity
    }
}
