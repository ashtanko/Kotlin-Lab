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

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 2246. Longest Path With Different Adjacent Characters
 * @see <a href="https://leetcode.com/problems/longest-path-with-different-adjacent-characters/">Source</a>
 */
fun interface LongestPath {
    operator fun invoke(parent: IntArray, s: String): Int
}

class LongestPathDFS : LongestPath {
    var res = 0

    override operator fun invoke(parent: IntArray, s: String): Int {
        res = 0
        val children: Array<ArrayList<Int>> = Array(parent.size) { ArrayList() }
        for (i in 1 until parent.size) {
            children[parent[i]].add(i)
        }
        dfs(children, s, 0)
        return res
    }

    private fun dfs(children: Array<ArrayList<Int>>, s: String, i: Int): Int {
        val queue: PriorityQueue<Int> = PriorityQueue()
        for (j in children[i]) {
            val cur = dfs(children, s, j)
            if (s[j] != s[i]) queue.offer(-cur)
        }
        val big1 = if (queue.isEmpty()) 0 else -queue.poll()
        val big2 = if (queue.isEmpty()) 0 else -queue.poll()
        res = max(res, big1 + big2 + 1)
        return big1 + 1
    }
}
