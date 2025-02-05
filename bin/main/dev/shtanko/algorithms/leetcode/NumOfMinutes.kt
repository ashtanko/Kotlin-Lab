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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

/**
 * 1376. Time Needed to Inform All Employees
 * @see <a href="https://leetcode.com/problems/time-needed-to-inform-all-employees/">Source</a>
 */
fun interface NumOfMinutes {
    operator fun invoke(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int
}

/**
 * Approach 1: Depth-First Search (DFS)
 */
@DFS
class NumOfMinutesDFS : NumOfMinutes {

    private var maxTime = Int.MIN_VALUE

    override operator fun invoke(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        val adjList = ArrayList<ArrayList<Int>>(n)

        for (i in 0 until n) {
            adjList.add(ArrayList())
        }

        // Making an adjacent list, each index stores the Ids of subordinate employees.

        // Making an adjacent list, each index stores the Ids of subordinate employees.
        for (i in 0 until n) {
            if (manager[i] != -1) {
                adjList[manager[i]].add(i)
            }
        }

        dfs(adjList, informTime, headID, 0)
        return maxTime
    }

    private fun dfs(adjList: ArrayList<ArrayList<Int>>, informTime: IntArray, curr: Int, time: Int) {
        // Maximum time for an employee to get the news.
        maxTime = max(maxTime, time)
        for (adjacent in adjList[curr]) {
            // Visit the subordinate employee who gets the news after informTime[curr] unit time.
            dfs(adjList, informTime, adjacent, time + informTime[curr])
        }
    }
}

/**
 * Approach 2: Breadth-First Search (BFS)
 */
@BFS
class NumOfMinutesBFS : NumOfMinutes {
    private var maxTime = Int.MIN_VALUE

    override operator fun invoke(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        val adjList = ArrayList<ArrayList<Int>>(n)

        for (i in 0 until n) {
            adjList.add(ArrayList())
        }

        // Making an adjacent list, each index stores the Ids of subordinate employees.

        // Making an adjacent list, each index stores the Ids of subordinate employees.
        for (i in 0 until n) {
            if (manager[i] != -1) {
                adjList[manager[i]].add(i)
            }
        }

        val q: Queue<Pair<Int, Int>> = LinkedList()
        q.add(Pair(headID, 0))

        while (q.isNotEmpty()) {
            val employeePair: Pair<Int, Int> = q.remove()
            val parent: Int = employeePair.first
            val time: Int = employeePair.second
            // Maximum time for an employee to get the news.
            maxTime = max(maxTime, time)
            for (adjacent in adjList[parent]) {
                q.add(Pair(adjacent, time + informTime[parent]))
            }
        }

        return maxTime
    }
}
