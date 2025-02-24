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
 * 1483. Kth Ancestor of a Tree Node
 * @see <a href="https://leetcode.com/problems/kth-ancestor-of-a-tree-node/">Source</a>
 */
@DFS
class TreeAncestor(n: Int, parent: IntArray) {
    private var tree: Array<MutableList<Int>> = Array(n) { ArrayList() }
    private var level: MutableList<MutableList<Int>> = ArrayList() // nodes in a level
    private var dfn = IntArray(n) // dfs number
    private var ndfn = 0 // dfs number starts wih 0
    private var depth = IntArray(n) // depth of each node

    init {
        for (i in 0 until n) {
            tree[i] = ArrayList()
        }
        for (i in 1 until n) { // build tree
            tree[parent[i]].add(i)
        }
        dfs(0, 0)
    }

    fun getKthAncestor(node: Int, k: Int): Int {
        val d = depth[node]
        if (d - k < 0) return -1
        val list: List<Int> = level[d - k]
        var left = 0
        var right = list.size
        while (left < right) { // binary search
            val mid = left + (right - left) / 2
            if (dfn[list[mid]] < dfn[node]) { // find the first node larger than or equal to dfn[node]
                left = mid + 1
            } else {
                right = mid
            }
        }
        return list[left - 1] // ancestor is the largest one smaller than dfn[node]
    }

    private fun dfs(n: Int, dep: Int) {
        if (level.size == dep) {
            level.add(ArrayList())
        }
        dfn[n] = ndfn++ // mark dfs number
        depth[n] = dep // save the depth
        level[dep].add(n) // save nodes into level
        for (child in tree[n]) {
            dfs(child, dep + 1)
        }
    }
}
