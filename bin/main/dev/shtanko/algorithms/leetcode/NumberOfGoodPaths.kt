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

import java.util.TreeMap

/**
 * 2421. Number of Good Paths
 * @see <a href="https://leetcode.com/problems/number-of-good-paths/">Source</a>
 */
fun interface NumberOfGoodPaths {
    operator fun invoke(vals: IntArray, edges: Array<IntArray>): Int
}

class NumberOfGoodPathsUnionFind : NumberOfGoodPaths {
    override operator fun invoke(vals: IntArray, edges: Array<IntArray>): Int {
        val n = vals.size
        val adj: Array<MutableList<Int>> = Array(n) { ArrayList() }
        val sameValues = TreeMap<Int, ArrayList<Int>>()
        var ans = 0
        for (i in 0 until n) {
            adj[i] = ArrayList()
            if (!sameValues.containsKey(vals[i])) {
                sameValues[vals[i]] = ArrayList()
            }
            sameValues[vals[i]]?.add(i)
        }
        for (e in edges) {
            val u = e[0]
            val v = e[1]
            if (vals[u] >= vals[v]) {
                adj[u].add(v)
            } else {
                adj[v].add(u)
            }
        }
        val uf = UF(n)
        for (sameValue in sameValues.keys) {
            val ints = sameValues[sameValue] ?: arrayListOf()
            for (u in ints) {
                for (v in adj[u]) {
                    uf.union(u, v)
                }
            }
            val group: HashMap<Int, Int> = HashMap()
            for (u in ints) {
                group[uf.find(u)] = group.getOrDefault(uf.find(u), 0) + 1
            }
            ans += ints.size
            for (key in group.keys) {
                val size = group[key] ?: 0
                ans += size * (size - 1) / 2
            }
        }
        return ans
    }

    class UF(len: Int) {
        private val parent: IntArray = IntArray(len) { -1 }

        fun find(a: Int): Int {
            return if (parent[a] >= 0) {
                find(parent[a]).also { parent[a] = it }
            } else {
                a
            }
        }

        fun union(a: Int, b: Int): Boolean {
            val pa = find(a)
            val pb = find(b)
            if (pa == pb) return false
            if (parent[pa] <= parent[pb]) {
                parent[pa] += parent[pb]
                parent[pb] = pa
            } else {
                parent[pb] += parent[pa]
                parent[pa] = pb
            }
            return true
        }
    }
}
