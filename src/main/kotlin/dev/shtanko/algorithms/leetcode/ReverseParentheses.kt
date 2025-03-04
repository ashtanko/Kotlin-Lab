/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.Sort
import dev.shtanko.datastructures.Stack
import java.util.Deque
import java.util.LinkedList
import java.util.Queue

/**
 * 1190. Reverse Substrings Between Each Pair of Parentheses
 * @see <a href="https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/">Source</a>
 */
fun interface ReverseParentheses {
    operator fun invoke(str: String): String
}

@BruteForce
class ReverseParenthesesBF : ReverseParentheses {
    override operator fun invoke(str: String): String {
        val stack: Stack<Char> = Stack()
        for (char in str.toCharArray()) {
            if (char == ')') {
                val queue: Queue<Char> = LinkedList()
                while (stack.isNotEmpty() && stack.peek() != '(') queue.add(stack.poll())
                if (stack.isNotEmpty()) stack.poll() // Remove the '(' from stack
                while (queue.isNotEmpty()) stack.push(queue.remove())
            } else {
                stack.push(char)
            }
        }
        val result = StringBuilder()
        while (stack.isNotEmpty()) result.append(stack.poll())

        return result.reverse().toString()
    }
}

@Sort
class ReverseParenthesesSort : ReverseParentheses {
    override operator fun invoke(str: String): String {
        val deque: Deque<StringBuilder> = LinkedList()
        deque.push(StringBuilder())

        for (char in str.toCharArray()) {
            when (char) {
                '(' -> {
                    deque.offer(StringBuilder())
                }

                ')' -> {
                    val current = deque.pollLast()
                    deque.peekLast().append(current.reverse())
                }

                else -> {
                    deque.peekLast().append(char)
                }
            }
        }
        return deque.pollLast().toString()
    }
}

@DFS
class ReverseParenthesesStringBuilder : ReverseParentheses {
    override fun invoke(str: String): String {
        val n = str.length
        var i = 0
        fun dfs(): StringBuilder = StringBuilder().apply {
            while (i < n && str[i] != ')') {
                if (str[i] == '(') {
                    i += 1
                    append(dfs().reverse())
                } else {
                    append(str[i])
                }
                i += 1
            }
        }
        return dfs().toString()
    }
}
