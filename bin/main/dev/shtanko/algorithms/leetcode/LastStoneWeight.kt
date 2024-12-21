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

import java.util.PriorityQueue

fun interface LastStoneWeight {
    operator fun invoke(arr: IntArray): Int
}

class LastStoneWeightSort : LastStoneWeight {
    override operator fun invoke(arr: IntArray): Int {
        return arr.lastStoneWeight()
    }

    private fun IntArray.lastStoneWeight(): Int {
        sort()
        for (i in size - 1 downTo 1) {
            this[i - 1] = this[i] - this[i - 1]
            sort()
        }
        return if (this.isEmpty()) 1 else first()
    }
}

class LastStoneWeightQueue : LastStoneWeight {
    override operator fun invoke(arr: IntArray): Int {
        val pq = PriorityQueue<Int> { c, d -> d - c }
        for (stone in arr) {
            pq.offer(stone)
        }
        while (pq.size > 1) {
            pq.offer(pq.poll() - pq.poll())
        }
        return if (arr.isEmpty()) 1 else pq.poll()
    }
}
