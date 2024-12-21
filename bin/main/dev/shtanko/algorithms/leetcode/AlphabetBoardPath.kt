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

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Collections
import kotlin.math.max

/**
 * 1138. Alphabet Board Path
 * @see <a href="https://leetcode.com/problems/alphabet-board-path/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/alphabet-board-path")
fun interface AlphabetBoardPath {
    operator fun invoke(target: String): String
}

@Iterative
class AlphabetBoardPathSB : AlphabetBoardPath {
    override operator fun invoke(target: String): String {
        var x = 0
        var y = 0
        val sb = StringBuilder()
        for (ch: Char in target.toCharArray()) {
            val x1 = (ch.code - 'a'.code) % 5
            val y1 = (ch.code - 'a'.code) / 5
            sb.append(
                Collections.nCopies(max(0, y - y1), "U").joinToString("") +
                    Collections.nCopies(max(0, x1 - x), "R").joinToString("") +
                    Collections.nCopies(max(0, x - x1), "L").joinToString("") +
                    Collections.nCopies(max(0, y1 - y), "D").joinToString("") + "!",
            )
            x = x1
            y = y1
        }
        return sb.toString()
    }
}
