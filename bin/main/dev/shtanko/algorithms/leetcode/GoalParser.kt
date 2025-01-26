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

fun interface GoalParser {
    fun interpret(command: String): String
}

class GoalParserBruteForce : GoalParser {
    override fun interpret(command: String): String {
        val sb: StringBuilder = StringBuilder()
        var i = 0
        while (i < command.length) {
            if (command[i] == 'G') {
                sb.append('G')
            } else if (i + 1 < command.length && command[i + 1] == ')') {
                sb.append('o')
                i++
            } else {
                sb.append("al")
                i += 3
            }
            i++
        }
        return sb.toString()
    }
}

class GoalParserRegex : GoalParser {
    override fun interpret(command: String): String {
        return command.replace("()", "o").replace("(al)", "al")
    }
}
