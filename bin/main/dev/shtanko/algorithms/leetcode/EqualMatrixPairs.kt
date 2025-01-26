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
 * 2352. Equal Row and Column Pairs
 * @see <a href="https://leetcode.com/problems/equal-row-and-column-pairs/">Source</a>
 */
fun interface EqualMatrixPairs {
    operator fun invoke(grid: Array<IntArray>): Int
}

class EqualMatrixPairsTrie : EqualMatrixPairs {
    override fun invoke(grid: Array<IntArray>): Int {
        val root = TrieNode()
        val n: Int = grid.size
        var res = 0
        for (row in grid) {
            var curNode = root
            for (j in 0 until n) {
                curNode = curNode.children.computeIfAbsent(row[j]) { TrieNode() }
            }
            curNode.count++
        }
        for (i in 0 until n) {
            var curNode = root
            for (row in grid) {
                curNode = if (curNode.children.containsKey(row[i])) {
                    curNode.children.getOrDefault(row[i], TrieNode())
                } else {
                    break
                }
            }
            res += curNode.count
        }
        return res
    }

    private data class TrieNode(
        var count: Int = 0,
        val children: MutableMap<Int, TrieNode> = HashMap(),
    )
}
