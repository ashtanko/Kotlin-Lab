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

/**
 * 851. Loud and Rich
 * @see <a href="https://leetcode.com/problems/loud-and-rich/">Source</a>
 */
fun interface LoudAndRich {
    operator fun invoke(richer: Array<IntArray>, quiet: IntArray): IntArray
}

class LoudAndRichDFS : LoudAndRich {

    override operator fun invoke(richer: Array<IntArray>, quiet: IntArray): IntArray {
        val n = quiet.size
        val res = IntArray(n) { -1 }
        val adj: Array<MutableList<Int>> = Array(n) { ArrayList() }
        for (i in 0 until n) {
            adj[i] = ArrayList()
        }
        for (r in richer) {
            adj[r[1]].add(r[0]) // build graph
        }
        for (i in 0 until n) {
            dfs(adj, i, quiet, res) // dfs for every node
        }
        return res
    }

    private fun dfs(adj: Array<MutableList<Int>>, src: Int, quiet: IntArray, res: IntArray): IntArray {
        if (res[src] > -1) return intArrayOf(quiet[res[src]], res[src])
        var ret = intArrayOf(quiet[src], src)
        for (n in adj[src]) {
            val temp = dfs(adj, n, quiet, res)
            if (temp[0] < ret[0]) {
                ret = temp // if any richer node is quieter
            }
        }
        res[src] = ret[1] // update the index in res
        return ret
    }
}

class LoudAndRichMap : LoudAndRich {
    private var richer2: HashMap<Int, MutableList<Int>> = HashMap()
    private lateinit var res: IntArray

    override operator fun invoke(richer: Array<IntArray>, quiet: IntArray): IntArray {
        val n = quiet.size
        for (i in 0 until n) {
            richer2[i] = ArrayList()
        }
        for (v in richer) {
            richer2[v[1]]?.add(v[0])
        }
        res = IntArray(n) { -1 }
        for (i in 0 until n) dfs(i, quiet)
        return res
    }

    private fun dfs(i: Int, quiet: IntArray): Int {
        if (res[i] >= 0) return res[i]
        res[i] = i
        for (j in richer2.getOrDefault(i, emptyList())) {
            if (quiet[res[i]] > quiet[dfs(j, quiet)]) res[i] = res[j]
        }
        return res[i]
    }
}
