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

import dev.shtanko.algorithms.annotations.DFS

/**
 * 1125. Smallest Sufficient Team
 * @see <a href="https://leetcode.com/problems/smallest-sufficient-team/">Source</a>
 */
fun interface SmallestSufficientTeam {
    operator fun invoke(reqSkills: Array<String>, people: List<List<String>>): IntArray
}

@DFS
class SmallestSufficientTeamDFS : SmallestSufficientTeam {
    private var sol: MutableList<Int> = ArrayList()

    override operator fun invoke(reqSkills: Array<String>, people: List<List<String>>): IntArray {
        val idx: MutableMap<String, Int> = HashMap()
        var n = 0
        for (s in reqSkills) idx[s] = n++ // skills are represented by 0, 1, 2....

        val pe = IntArray(people.size)
        for (i in pe.indices) {
            for (p in people[i]) {
                val skill = idx[p]!!
                pe[i] += 1 shl skill
            }
        } // each person is transferred to a number, of which the bits of 1 means the guy has the skill

        search(0, pe, ArrayList(), n)
        val ans = IntArray(sol.size)
        for (i in sol.indices) ans[i] = sol[i]
        return ans
    }

    private fun search(cur: Int, pe: IntArray, onesol: MutableList<Int>, n: Int) {
        // when all bits are 1, all skills are covered
        if (cur == (1 shl n) - 1) {
            if (sol.isEmpty() || onesol.size < sol.size) {
                sol = ArrayList(onesol)
            }
            return
        }
        if (sol.isNotEmpty() && onesol.size >= sol.size) {
            return
        }
        var zeroBit = 0
        while (cur shr zeroBit and 1 == 1) zeroBit++
        for (i in pe.indices) {
            val per = pe[i]
            if (per shr zeroBit and 1 == 1) {
                onesol.add(i) // when a person can cover a zero bit in the current number, we can add him
                search(cur or per, pe, onesol, n)
                onesol.removeAt(onesol.size - 1) // search in a backtracking way
            }
        }
    }
}
