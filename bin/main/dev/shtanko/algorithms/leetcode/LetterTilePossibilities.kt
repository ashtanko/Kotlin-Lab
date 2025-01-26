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
 * 1079. Letter Tile Possibilities
 * @see <a href="https://leetcode.com/problems/letter-tile-possibilities/">Source</a>
 */
fun interface LetterTilePossibilities {
    operator fun invoke(tiles: String): Int
}

class LetterTilePossibilitiesDFS : LetterTilePossibilities {

    private var count = 0

    override operator fun invoke(tiles: String): Int {
        val chars = tiles.toCharArray()
        chars.sort()
        val visited = BooleanArray(chars.size)
        dfs(chars, 0, visited)
        return count
    }

    private fun dfs(chars: CharArray, length: Int, visited: BooleanArray) {
        if (length == chars.size) return
        for (i in chars.indices) {
            if (visited[i]) continue
            if (i - 1 >= 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue
            count++
            visited[i] = true
            dfs(chars, length + 1, visited)
            visited[i] = false
        }
    }
}
