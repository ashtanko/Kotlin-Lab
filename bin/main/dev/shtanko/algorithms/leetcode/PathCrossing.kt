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
 * 1496. Path Crossing
 * @see <a href="https://leetcode.com/problems/path-crossing">Source</a>
 */
fun interface PathCrossing {
    operator fun invoke(path: String): Boolean
}

class PathCrossingHashSet : PathCrossing {
    override fun invoke(path: String): Boolean {
        val moves: MutableMap<Char, Pair<Int, Int>> = HashMap()
        moves['N'] = Pair(0, 1)
        moves['S'] = Pair(0, -1)
        moves['W'] = Pair(-1, 0)
        moves['E'] = Pair(1, 0)

        val visited: MutableSet<Pair<Int, Int>?> = HashSet()
        visited.add(Pair(0, 0))

        var x = 0
        var y = 0

        for (c in path.toCharArray()) {
            val curr = moves.getOrDefault(c, Pair(0, 0))
            val dx: Int = curr.first
            val dy: Int = curr.second
            x += dx
            y += dy

            val pair = Pair(x, y)
            if (visited.contains(pair)) {
                return true
            }

            visited.add(pair)
        }

        return false
    }
}
