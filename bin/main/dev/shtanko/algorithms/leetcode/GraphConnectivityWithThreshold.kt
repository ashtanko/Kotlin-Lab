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
 * 1627. Graph Connectivity With Threshold
 * @see <a href="https://leetcode.com/problems/graph-connectivity-with-threshold/">Source</a>
 */
fun interface GraphConnectivityWithThreshold {
    fun areConnected(n: Int, threshold: Int, queries: Array<IntArray>): List<Boolean>
}

class GraphConnectivityWithThresholdUnion : GraphConnectivityWithThreshold {
    override fun areConnected(n: Int, threshold: Int, queries: Array<IntArray>): List<Boolean> {
        val uf = UnionFind(n + 1)
        for (z in threshold + 1..n) {
            var x = z * 2
            while (x <= n) {
                uf.union(z, x)
                x += z
            }
        }

        val ans: MutableList<Boolean> = ArrayList()
        for (q in queries) {
            ans.add(uf.find(q[0]) == uf.find(q[1]))
        }
        return ans
    }

    // Feel free to copy this class for later reuse!
    class UnionFind(n: Int) {
        var parent: IntArray
        var size: IntArray

        init {
            parent = IntArray(n)
            size = IntArray(n)
            for (i in 0 until n) {
                parent[i] = i
                size[i] = 1
            }
        }

        fun find(x: Int): Int {
            return if (x == parent[x]) {
                x
            } else {
                find(parent[x]).also {
                    parent[x] = it // Path compression
                }
            }
        }

        fun union(u: Int, v: Int): Boolean {
            val pu = find(u)
            val pv = find(v)
            if (pu == pv) return false
            if (size[pu] > size[pv]) { // Union by larger size
                size[pu] += size[pv]
                parent[pv] = pu
            } else {
                size[pv] += size[pu]
                parent[pu] = pv
            }
            return true
        }
    }
}
