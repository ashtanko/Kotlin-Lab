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

/**
 * 399. Evaluate Division
 * @see <a href="https://leetcode.com/problems/evaluate-division/">Source</a>
 */
fun interface EvaluateDivision {
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray
}

class EvaluateDivisionDFS : EvaluateDivision {
    override fun calcEquation(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>,
    ): DoubleArray {
        val m: MutableMap<String?, MutableMap<String, Double>> = HashMap()
        for (i in values.indices) {
            m.putIfAbsent(equations[i][0], HashMap())
            m.putIfAbsent(equations[i][1], HashMap())
            m[equations[i][0]]!![equations[i][1]] = values[i]
            m[equations[i][1]]!![equations[i][0]] = 1 / values[i]
        }
        val r = DoubleArray(queries.size)
        for (i in queries.indices) {
            r[i] = dfs(queries[i][0], queries[i][1], 1.0, m, HashSet())
        }
        return r
    }

    private fun dfs(
        s: String,
        t: String,
        r: Double,
        m: Map<String?, Map<String, Double>>,
        seen: MutableSet<String?>,
    ): Double {
        if (!m.containsKey(s) || !seen.add(s)) return (-1).toDouble()
        if (s == t) return r
        val next = m[s]!!
        for (c in next.keys) {
            val result = dfs(c, t, r * next[c]!!, m, seen)
            if (result != -1.0) return result
        }
        return (-1).toDouble()
    }
}
