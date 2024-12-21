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

import dev.shtanko.algorithms.annotations.DFS
import kotlin.math.max

/**
 * 2359. Find The Closest Node to Given Two Nodes
 * @see <a href="https://leetcode.com/problems/find-closest-node-to-given-two-nodes/">Source</a>
 */
fun interface ClosestMeetingNode {
    operator fun invoke(edges: IntArray, node1: Int, node2: Int): Int
}

@DFS
class ClosestMeetingNodeDFS : ClosestMeetingNode {
    override operator fun invoke(edges: IntArray, node1: Int, node2: Int): Int {
        val n = edges.size
        // d1[i]: shortest dist to node i starting from node 1
        val d1 = IntArray(n) { Int.MAX_VALUE }
        val d2 = IntArray(n) { Int.MAX_VALUE }
        // vis1[i]: true if node i is visited else false. used for building d1
        val vis1 = BooleanArray(n)
        val vis2 = BooleanArray(n)
        // dist to node1 from node1 is 0, same as node2
        d2[node2] = 0
        d1[node1] = d2[node2]
        // build the dist for d1
        dfs(node1, d1, vis1, edges)
        // build the dist for d2
        dfs(node2, d2, vis2, edges)
        // iterate each node to find the min max dist
        var ans = -1
        var mi = Int.MAX_VALUE
        for (i in 0 until n) {
            if (max(d1[i], d2[i]) < mi) {
                mi = max(d1[i], d2[i])
                ans = i
            }
        }
        return ans
    }

    private fun dfs(u: Int, d: IntArray, vis: BooleanArray, edges: IntArray) {
        // mark it visited
        vis[u] = true
        // check the outgoing edge
        val v = edges[u]
        // -1 means there is no outgoing edge, so we skip it
        if (v != -1 && !vis[v]) {
            // the dist going to node v form node u is simply d[u] + 1
            d[v] = d[u] + 1
            // dfs on neighbour node `v`
            dfs(v, d, vis, edges)
        }
    }
}
