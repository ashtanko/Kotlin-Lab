/*
 * Copyright 2023 Oleksii Shtanko
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
 * 1387. Sort Integers by The Power Value
 * @see <a href="https://leetcode.com/problems/sort-integers-by-the-power-value/">Source</a>
 */
fun interface SortIntegersByThePowerValue {
    fun getKth(lo: Int, hi: Int, k: Int): Int
}

class SortIntegersByThePowerValueDP : SortIntegersByThePowerValue {

    private val map: MutableMap<Int, Int> = HashMap()

    override fun getKth(lo: Int, hi: Int, k: Int): Int {
        val queue: PriorityQueue<IntArray> = PriorityQueue label@{ a, b ->
            if (a[1] != b[1]) {
                return@label b[1] - a[1]
            }
            b[0] - a[0]
        }
        for (i in lo..hi) {
            queue.add(intArrayOf(i, solve(i)))
            if (queue.size > k) {
                queue.remove()
            }
        }
        return queue.poll()[0]
    }

    private fun solve(x: Int): Int {
        if (x == 1) {
            return 0
        }
        if (map.containsKey(x)) {
            return map[x] ?: 0
        }
        val res = (if (x % 2 == 0) solve(x / 2) else solve(x * 3 + 1)) + 1
        map[x] = res
        return res
    }
}
