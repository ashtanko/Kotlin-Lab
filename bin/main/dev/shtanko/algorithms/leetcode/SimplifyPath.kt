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

import java.util.Stack

/**
 * 71. Simplify Path
 * @see <a href="https://leetcode.com/problems/simplify-path/">Source</a>
 */
fun interface SimplifyPath {
    /**
     * Simplifies a Unix-style file path.
     * @param path The original file path.
     * @return The simplified file path.
     */
    operator fun invoke(path: String): String
}

/**
 * Implementation of SimplifyPath using a stack.
 * This implementation assumes that the input path is a Unix-style file path.
 */
class SimplifyPathStack : SimplifyPath {
    /**
     * Simplifies a Unix-style file path using a stack.
     * @param path The original file path.
     * @return The simplified file path.
     */
    override operator fun invoke(path: String): String {
        val stack: Stack<String> = Stack()
        for (cur in path.split("/")) {
            if (cur == "..") {
                if (!stack.empty()) stack.pop()
            } else if (cur.isNotEmpty() && cur != ".") {
                stack.push(cur)
            }
        }
        return "/${stack.joinToString("/")}"
    }
}
