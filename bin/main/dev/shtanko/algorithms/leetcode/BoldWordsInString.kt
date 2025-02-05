/*
 * Copyright 2020 Oleksii Shtanko
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
import java.util.Arrays

@Iterative
class BoldWordsInString {
    operator fun invoke(words: Array<String>, str: String): String {
        val bold = BooleanArray(str.length + 1)
        for (w in words) {
            var start: Int = str.indexOf(w, 0)
            while (start != -1) {
                Arrays.fill(bold, start, start + w.length, true)
                start = str.indexOf(w, start + 1)
            }
        }
        val r = StringBuilder().append(if (bold[0]) "<b>" else "")
        for (i in 0 until bold.size - 1) {
            r.append(str[i])
            if (!bold[i] && bold[i + 1]) r.append("<b>") else if (bold[i] && !bold[i + 1]) r.append("</b>")
        }
        return r.toString()
    }
}
