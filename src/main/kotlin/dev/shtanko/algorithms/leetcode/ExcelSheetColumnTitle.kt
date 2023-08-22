/*
 * Copyright 2023 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

/**
 * 168. Excel Sheet Column Title
 * @link https://leetcode.com/problems/excel-sheet-column-title/
 */
fun interface ExcelSheetColumnTitle {
    operator fun invoke(columnNumber: Int): String
}

class ExcelSheetColumnTitleSB : ExcelSheetColumnTitle {

    companion object {
        private const val LIMIT = 26
    }

    override fun invoke(columnNumber: Int): String {
        val ans = StringBuilder()
        var mun = columnNumber
        while (mun > 0) {
            mun--
            // Get the last character and append it at the end of the string.
            ans.append((mun % LIMIT + 'A'.code).toChar())
            mun /= LIMIT
        }

        // Reverse it, as we appended characters in reverse order.
        return ans.reverse().toString()
    }
}
