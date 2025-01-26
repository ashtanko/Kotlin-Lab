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

fun interface KClosestPointsStrategy {
    operator fun invoke(points: Array<IntArray>, k: Int): Array<IntArray>

    fun IntArray.getDistance(): Int {
        return this[0] * this[0] + this[1] * this[1]
    }
}

class KClosestPointsQueue : KClosestPointsStrategy {
    override operator fun invoke(points: Array<IntArray>, k: Int): Array<IntArray> {
        var n = k
        val heap = PriorityQueue(Comparator<IntArray> { left, right -> right.getDistance() - left.getDistance() })
        for (point in points) {
            heap.add(point)
            if (heap.size > n) heap.poll()
        }
        val result = Array(n) { IntArray(2) }
        while (n > 0) result[--n] = heap.poll()
        return result
    }
}

class KClosestPointsSort : KClosestPointsStrategy {
    override operator fun invoke(points: Array<IntArray>, k: Int): Array<IntArray> {
        val n = points.size
        val dists = IntArray(n)
        for (i in 0 until n) dists[i] = points[i].getDistance()
        dists.sort()
        val distK = dists[k - 1]
        val ans = Array(k) { IntArray(2) }
        var t = 0
        for (i in 0 until n) if (points[i].getDistance() <= distK) ans[t++] = points[i]
        return ans
    }
}
