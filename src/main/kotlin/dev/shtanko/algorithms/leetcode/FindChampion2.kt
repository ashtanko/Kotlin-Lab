/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

/**
 * 2924. Find Champion II
 * @see <a href="https://leetcode.com/problems/find-champion-ii/">Find Champion II</a>
 */
@Medium("https://leetcode.com/problems/find-champion-ii/")
fun interface FindChampion2 {
    operator fun invoke(n: Int, edges: Array<IntArray>): Int
}

class FindChampion2InDegreeCount : FindChampion2 {
    override fun invoke(n: Int, edges: Array<IntArray>): Int {
        val indegree = IntArray(n)

        // Store the indegree of each team
        for (edge in edges) {
            indegree[edge[1]]++
        }

        var champ = -1
        var champCount = 0

        // Iterate through all teams to find those with an indegree of 0
        for (i in 0 until n) {
            if (indegree[i] == 0) {
                // If the team can be a champion, store the team number and increment the count
                champCount++
                champ = i
            }
        }

        // If more than one team can be a champion, return -1, otherwise return the champion team number
        return if (champCount > 1) -1 else champ
    }
}
