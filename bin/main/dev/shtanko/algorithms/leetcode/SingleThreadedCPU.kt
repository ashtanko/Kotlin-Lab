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

/**
 * 1834. Single-Threaded CPU
 * @see <a href="https://leetcode.com/problems/single-threaded-cpu/">Source</a>
 */
fun interface SingleThreadedCPU {
    fun getOrder(tasks: Array<IntArray>): IntArray
}

class SingleThreadedCPUQueue : SingleThreadedCPU {
    override fun getOrder(tasks: Array<IntArray>): IntArray {
        val n: Int = tasks.size
        val ans = IntArray(n)
        val extTasks = Array(n) { IntArray(3) }
        for (i in 0 until n) {
            extTasks[i][0] = i
            extTasks[i][1] = tasks[i][0]
            extTasks[i][2] = tasks[i][1]
        }
        extTasks.sortWith { a, b -> a[1] - b[1] }
        val pq: PriorityQueue<IntArray> = PriorityQueue<IntArray> { a, b ->
            if (a[2] == b[2]) a[0] - b[0] else a[2] - b[2]
        }
        var time = 0
        var ai = 0
        var ti = 0
        while (ai < n) {
            while (ti < n && extTasks[ti][1] <= time) {
                pq.offer(extTasks[ti++])
            }
            if (pq.isEmpty()) {
                time = extTasks[ti][1]
                continue
            }
            val bestFit: IntArray = pq.poll()
            ans[ai++] = bestFit[0]
            time += bestFit[2]
        }
        return ans
    }
}
