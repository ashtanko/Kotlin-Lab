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

import kotlin.math.max

/**
 * 2049. Count Nodes With the Highest Score
 * @see <a href="https://leetcode.com/problems/count-nodes-with-the-highest-score/">Source</a>
 */
fun interface CountHighestScoreNodes {
    operator fun invoke(parents: IntArray): Int
}

class CountHighestScoreNodesDfs : CountHighestScoreNodes {

    override operator fun invoke(parents: IntArray): Int {
        if (parents.isEmpty()) return 0
        val al: MutableList<MutableList<Int>> = ArrayList()
        for (i in parents.indices) {
            al.add(ArrayList())
        }
        val s = LongArray(parents.size)
        for (i in 1 until parents.size) {
            al[parents[i]].add(i)
        }
        dfs(al, s, 0)
        val maxVal: Long = s.max()
        return s.filter {
            it == maxVal
        }.size
    }

    private fun dfs(al: List<List<Int>>, s: LongArray, i: Int): Long {
        var prod: Long = 1
        var sum: Long = 1
        for (j in al[i]) {
            val cnt = dfs(al, s, j)
            prod *= cnt
            sum += cnt
        }
        s[i] = prod * max(1, al.size - sum)
        return sum
    }
}
