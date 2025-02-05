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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import java.util.Stack

/**
 * 316. Remove Duplicate Letters
 * @see <a href="https://leetcode.com/problems/remove-duplicate-letters">Source</a>
 */
fun interface RemoveDuplicateLetters {
    operator fun invoke(s: String): String
}

class RemoveDuplicateLettersSolution : RemoveDuplicateLetters {
    override fun invoke(s: String): String {
        val lastIndex = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in s.indices) {
            lastIndex[s[i] - 'a'] = i // track the lastIndex of character presence
        }

        val seen = BooleanArray(ALPHABET_LETTERS_COUNT) // keep track seen

        val st: Stack<Int> = Stack()

        for (i in s.indices) {
            val curr: Int = s[i] - 'a'
            if (seen[curr]) continue // if seen continue as we need to pick one char only
            while (st.isNotEmpty() && st.peek() > curr && i < lastIndex[st.peek()]) {
                seen[st.pop()] = false // pop out and mark unseen
            }
            st.push(curr) // add into stack
            seen[curr] = true // mark seen
        }

        val sb = StringBuilder()
        while (st.isNotEmpty()) sb.append((st.pop() + 'a'.code).toChar())
        return sb.reverse().toString()
    }
}
