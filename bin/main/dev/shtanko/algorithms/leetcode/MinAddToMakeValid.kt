/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 921. Minimum Add to Make Parentheses Valid
 * @see <a href="https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/">Source</a>
 */
@Medium("https://leetcode.com/problems/minimum-add-to-make-parentheses-valid")
fun interface MinAddToMakeValid {
    operator fun invoke(str: String): Int
}

class MinAddToMakeValidCounter : MinAddToMakeValid {
    override fun invoke(str: String): Int {
        var openBrackets = 0
        var minAddsRequired = 0
        for (c in str) {
            if (c == '(') {
                openBrackets++
            } else {
                if (openBrackets == 0) {
                    minAddsRequired++
                } else {
                    openBrackets--
                }
            }
        }
        return minAddsRequired + openBrackets
    }
}
