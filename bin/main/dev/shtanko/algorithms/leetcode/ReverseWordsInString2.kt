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

import dev.shtanko.algorithms.utils.reverse

/**
 * Time complexity: O(N).
 * Space complexity: O(1).
 */
class ReverseWordsInString2 {
    operator fun invoke(s: CharArray) {
        // reverse the whole string
        s.reverse(0, s.size - 1)

        // reverse each word
        reverseEachWord(s)
    }

    fun reverseEachWord(s: CharArray) {
        val n = s.size
        var start = 0
        var end = 0
        while (start < n) {
            // go to the end of the word
            while (end < n && s[end] != ' ') {
                end++
            }
            // reverse the word
            s.reverse(start, end - 1)
            // move to the next word
            start = end + 1
            end++
        }
    }
}
