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
 * 119. Pascal's Triangle II
 * @see <a href="https://leetcode.com/problems/pascals-triangle-ii">Source</a>
 */
fun interface PascalsTriangle2 {
    operator fun invoke(rowIndex: Int): List<Int>
}

class PascalsTriangle2Solution : PascalsTriangle2 {
    override fun invoke(rowIndex: Int): List<Int> {
        val ans: MutableList<Int> = ArrayList()
        ans.add(1)
        var temp: Long = 1
        var i = 1
        var up: Int = rowIndex
        var down = 1
        while (i <= rowIndex) {
            temp = temp * up / down
            ans.add(temp.toInt())
            i++
            up--
            down++
        }
        return ans
    }
}
