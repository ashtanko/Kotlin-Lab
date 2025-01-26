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

import java.util.LinkedList
import java.util.Queue

/**
 * 1036. Escape a Large Maze
 * @see <a href="https://leetcode.com/problems/escape-a-large-maze">Source</a>
 */
fun interface EscapeLargeMaze {
    operator fun invoke(blocked: Array<IntArray>, source: IntArray, target: IntArray): Boolean
}

class EscapeLargeMazeBFS : EscapeLargeMaze {

    private val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, -1))

    override fun invoke(blocked: Array<IntArray>, source: IntArray, target: IntArray): Boolean {
        val blocks: MutableSet<String> = HashSet()
        for (block in blocked) {
            blocks.add(block[0].toString() + ":" + block[1])
        }
        return bfs(source, target, blocks) && bfs(target, source, blocks)
    }

    private fun bfs(source: IntArray, target: IntArray, blocks: Set<String?>): Boolean {
        val seen: MutableSet<String> = HashSet()
        seen.add(source[0].toString() + ":" + source[1])
        val bfs: Queue<IntArray> = LinkedList()
        bfs.offer(source)
        while (bfs.isNotEmpty()) {
            val cur: IntArray = bfs.poll()
            for (dir in dirs) {
                val nextX = cur[0] + dir[0]
                val nextY = cur[1] + dir[1]
                if (nextX < 0 || nextY < 0 || nextX >= LIMIT || nextY >= LIMIT) continue
                val key = "$nextX:$nextY"
                if (seen.contains(key) || blocks.contains(key)) continue
                if (nextX == target[0] && nextY == target[1]) return true
                bfs.offer(intArrayOf(nextX, nextY))
                seen.add(key)
            }
            if (seen.size == MAX) return true
        }
        return false
    }

    companion object {
        private const val LIMIT = 1e6.toInt()
        private const val MAX = 20000
    }
}
