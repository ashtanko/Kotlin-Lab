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

import java.util.LinkedList
import java.util.Queue

/**
 * 433. Minimum Genetic Mutation
 * @see <a href="https://leetcode.com/problems/minimum-genetic-mutation/">Source</a>
 */
fun interface MinimumGeneticMutation {
    fun minMutation(start: String, end: String, bank: Array<String>): Int
}

/**
 * Approach: BFS (Breadth-First Search)
 */
class MinimumGeneticMutationBFS : MinimumGeneticMutation {
    override fun minMutation(start: String, end: String, bank: Array<String>): Int {
        val queue: Queue<String> = LinkedList()
        val seen: MutableSet<String> = HashSet()
        queue.add(start)
        seen.add(start)

        var steps = 0

        while (queue.isNotEmpty()) {
            val nodesInQueue: Int = queue.size
            for (j in 0 until nodesInQueue) {
                val node: String = queue.remove()
                if (node == end) {
                    return steps
                }
                for (c in charArrayOf('A', 'C', 'G', 'T')) {
                    for (i in node.indices) {
                        val neighbor = node.substring(0, i) + c + node.substring(i + 1)
                        if (!seen.contains(neighbor) && bank.contains(neighbor)) {
                            queue.add(neighbor)
                            seen.add(neighbor)
                        }
                    }
                }
            }
            steps++
        }

        return -1
    }
}
