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

import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue

/**
 * 1096. Brace Expansion II
 * @see <a href="https://leetcode.com/problems/brace-expansion-ii/">Source</a>
 */
fun interface BraceExpansion2 {
    fun braceExpansion2(expression: String): List<String>
}

@DFS
class BraceExpansion2DFS : BraceExpansion2 {
    override fun braceExpansion2(expression: String): List<String> {
        val queue: Queue<String> = LinkedList()
        queue.offer(expression)
        val set: MutableSet<String> = HashSet()

        while (queue.isNotEmpty()) {
            val str: String = queue.poll()
            if (str.indexOf('{') == -1) {
                set.add(str)
                continue
            }
            var i = 0
            var l = 0
            while (str[i] != '}') {
                if (str[i] == '{') l = i
                i++
            }
            val r: Int = i
            val before = str.substring(0, l)
            val after = str.substring(r + 1)
            val strs = str.substring(l + 1, r).split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val sb = StringBuilder()
            for (ss in strs) {
                sb.setLength(0)
                queue.offer(sb.append(before).append(ss).append(after).toString())
            }
        }

        val ans: List<String> = ArrayList(set)
        return ans.sorted()
    }
}
