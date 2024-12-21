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

/**
 * Parallel Courses
 * @see <a href="https://leetcode.com/problems/parallel-courses/">Source</a>
 */
fun interface ParallelCourses {
    fun minimumSemesters(n: Int, relations: Array<IntArray>): Int
}

/**
 * Approach 1: Breadth-First Search (Kahn's Algorithm)
 */
class ParallelCoursesBFS : ParallelCourses {
    override fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {
        val inCount = IntArray(n + 1) // or indegree

        val graph: MutableList<MutableList<Int>> = ArrayList(n + 1)
        for (i in 0 until n + 1) {
            graph.add(ArrayList())
        }
        for (relation in relations) {
            graph[relation[0]].add(relation[1])
            inCount[relation[1]]++
        }
        var step = 0
        var studiedCount = 0
        var bfsQueue: MutableList<Int> = ArrayList()
        for (node in 1 until n + 1) {
            if (inCount[node] == 0) {
                bfsQueue.add(node)
            }
        }
        // start learning with BFS
        while (bfsQueue.isNotEmpty()) {
            // start new semester
            step++
            val nextQueue: MutableList<Int> = ArrayList()
            for (node in bfsQueue) {
                studiedCount++
                for (endNode in graph[node]) {
                    inCount[endNode]--
                    // if all prerequisite courses learned
                    if (inCount[endNode] == 0) {
                        nextQueue.add(endNode)
                    }
                }
            }
            bfsQueue = nextQueue
        }

        // check if learn all courses
        return if (studiedCount == n) step else -1
    }
}
