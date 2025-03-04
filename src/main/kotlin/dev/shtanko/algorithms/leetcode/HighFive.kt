/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.extensions.second
import java.util.PriorityQueue
import java.util.TreeMap

private const val CAPACITY = 5
private const val SIZE = 2

fun interface HighFiveStrategy {
    operator fun invoke(items: Array<IntArray>): Array<IntArray>
}

class HighFivePriorityQueue : HighFiveStrategy {

    override operator fun invoke(items: Array<IntArray>): Array<IntArray> {
        val map: TreeMap<Int, PriorityQueue<Int>> = TreeMap<Int, PriorityQueue<Int>>()

        for (item in items) {
            val id = item.first()
            val score = item.second()
            if (!map.containsKey(id)) {
                val pq: PriorityQueue<Int> = PriorityQueue<Int>(CAPACITY)
                pq.offer(score)
                map[id] = pq
            } else {
                val pq: PriorityQueue<Int> = map[id] ?: break
                pq.offer(score)
                if (pq.size > CAPACITY) {
                    pq.poll()
                }
                map[id] = pq
            }
        }

        val res = Array(map.size) { IntArray(SIZE) }

        for ((index, id) in map.keys.withIndex()) {
            res[index][0] = id
            val pq: PriorityQueue<Int> = map[id] ?: break
            var sum = 0
            val size: Int = pq.size
            while (pq.isNotEmpty()) {
                sum += pq.poll()
            }
            res[index][1] = sum / size
        }

        return res
    }
}

class HighFiveSort : HighFiveStrategy {

    override operator fun invoke(items: Array<IntArray>): Array<IntArray> {
        items.sortWith { t1, t2 ->
            // put item[id, score] with same id together
            // for each id/student, item[id, score] is ordered by score (increasing)
            if (t1[0] == t2[0]) {
                t2[1] - t1[1]
            } else {
                t1[0] - t2[0]
            }
        }

        val s: Int = items.size
        // The list temp helps to calculate how many students/ids are there
        val temp: MutableList<IntArray> = ArrayList()
        var i = 0
        while (i < s) {
            val id = items[i][0]
            var count = CAPACITY
            var sum = 0
            while (i < s && count-- > 0) {
                sum += items[i][1]
                i++
            }
            temp.add(intArrayOf(id, sum / CAPACITY))

            // skip scores that are not the 'HighFive' for a student
            while (i < s && items[i][0] == id) i++
        }

        val size = temp.size
        val res = Array(size) { IntArray(SIZE) }
        for (j in 0 until size) {
            res[j] = temp[j]
        }

        return res
    }
}
