/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.LinkedList
import java.util.Queue

/**
 * 207. Course Schedule
 * @see <a href="https://leetcode.com/problems/course-schedule/">Source</a>
 */
@Medium("https://leetcode.com/problems/course-schedule")
fun interface CourseSchedule {
    operator fun invoke(numCourses: Int, prerequisites: Array<IntArray>): Boolean
}

/**
 * Approach 1: Topological Sort Using Kahn's Algorithm
 */
class CourseScheduleKahn : CourseSchedule {
    override fun invoke(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val indegree = IntArray(numCourses)
        val adj: MutableList<MutableList<Int>> = ArrayList(numCourses)

        for (i in 0 until numCourses) {
            adj.add(ArrayList())
        }

        for (prerequisite in prerequisites) {
            adj[prerequisite[1]].add(prerequisite[0])
            indegree[prerequisite[0]]++
        }

        val queue: Queue<Int> = LinkedList()
        // Push all the nodes with indegree zero in the queue.
        for (i in 0 until numCourses) {
            if (indegree[i] == 0) {
                queue.offer(i)
            }
        }

        var nodesVisited = 0
        while (queue.isNotEmpty()) {
            val node: Int = queue.poll()
            nodesVisited++
            for (neighbor in adj[node]) {
                // Delete the edge "node -> neighbor".
                indegree[neighbor]--
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor)
                }
            }
        }

        return nodesVisited == numCourses
    }
}

/**
 * Approach 2: Depth First Search
 */
class CourseScheduleDFS : CourseSchedule {

    override fun invoke(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val adj: MutableList<MutableList<Int>> = ArrayList(numCourses)
        for (i in 0 until numCourses) {
            adj.add(ArrayList())
        }
        for (prerequisite in prerequisites) {
            adj[prerequisite[1]].add(prerequisite[0])
        }
        val visit = BooleanArray(numCourses)
        val inStack = BooleanArray(numCourses)
        for (i in 0 until numCourses) {
            if (safeStatesDFS(i, adj, visit, inStack)) {
                return false
            }
        }
        return true
    }
}
