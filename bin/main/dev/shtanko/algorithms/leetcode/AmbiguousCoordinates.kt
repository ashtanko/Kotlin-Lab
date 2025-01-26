/*
 * Copyright 2021 Oleksii Shtanko
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
 * 816. Ambiguous Coordinates
 * @see <a href="https://leetcode.com/problems/ambiguous-coordinates/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/ambiguous-coordinates")
class AmbiguousCoordinates {
    operator fun invoke(str: String): List<String> {
        val ans: MutableList<String> = ArrayList()
        for (i in 2 until str.length - 1) {
            for (left in make(str, 1, i)) {
                for (right in make(str, i, str.length - 1)) {
                    ans.add("($left, $right)")
                }
            }
        }
        return ans
    }

    private fun make(str: String, i: Int, j: Int): List<String?> {
        val ans: MutableList<String?> = ArrayList()
        for (d in 1..j - i) {
            val left = str.substring(i, i + d)
            val right = str.substring(i + d, j)
            if ((!left.startsWith("0") || left == "0") && !right.endsWith("0")) {
                ans.add(left + (if (d < j - i) "." else "") + right)
            }
        }
        return ans
    }
}
