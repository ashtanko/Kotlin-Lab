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

package dev.shtanko.algorithms.templates

import java.util.LinkedList
import java.util.Queue

private fun fn(graph: Array<IntArray>): Int {
    val startNode = 0
    val queue: Queue<Int> = LinkedList()
    val seen: MutableSet<Int> = HashSet()
    queue.add(startNode)
    seen.add(startNode)
    val ans = 0
    while (queue.isNotEmpty()) {
        val node: Int = queue.remove()
        // do some logic
        for (neighbor in graph[node]) {
            if (!seen.contains(neighbor)) {
                seen.add(neighbor)
                queue.add(neighbor)
            }
        }
    }
    return ans
}
