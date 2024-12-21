/*
 * Copyright 2022 Oleksii Shtanko
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

import java.util.Deque
import java.util.LinkedList
import java.util.Stack

/**
 * 2390. Removing Stars From a String
 * @see <a href="https://leetcode.com/problems/removing-stars-from-a-string/">Source</a>
 */
fun interface RemoveStars {
    operator fun invoke(s: String): String
}

/**
 * Approach 1 :Stack
 */
class RemoveStarsStack : RemoveStars {
    override operator fun invoke(s: String): String {
        val st: Stack<Char> = Stack()
        for (ch in s.toCharArray()) {
            if (ch != '*') st.push(ch) else st.pop()
        }
        val sb = StringBuilder()

        while (st.isNotEmpty()) {
            sb.append(st.pop())
        }

        return sb.reverse().toString() // in case of stack reverse the string
    }
}

/**
 * Approach 1 :Deque
 */
class RemoveStarsDeque : RemoveStars {
    override operator fun invoke(s: String): String {
        val dq: Deque<Char> = LinkedList()
        for (ch in s.toCharArray()) {
            if (ch != '*') dq.addLast(ch) else dq.removeLast()
        }
        val sb = StringBuilder()

        while (dq.isNotEmpty()) {
            sb.append(dq.removeFirst())
        }
        return sb.toString()
    }
}

/**
 * Approach 3 :StringBuilder
 */
class RemoveStarsStackSimulation : RemoveStars {
    override operator fun invoke(s: String): String {
        val res = StringBuilder()
        for (c in s.toCharArray()) {
            if (c == '*') {
                res.setLength(res.length - 1)
            } else {
                res.append(c)
            }
        }
        return res.toString()
    }
}
