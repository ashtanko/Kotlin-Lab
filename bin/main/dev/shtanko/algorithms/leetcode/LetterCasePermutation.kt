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

import java.util.LinkedList
import java.util.Queue

/**
 * 784. Letter Case Permutation
 * @see <a href="https://leetcode.com/problems/letter-case-permutation/">Source</a>
 */
fun interface LetterCasePermutation {
    operator fun invoke(s: String): List<String>
}

class LetterCasePermutationBFS : LetterCasePermutation {
    override operator fun invoke(s: String): List<String> {
        if (s.isBlank()) {
            return LinkedList()
        }
        val queue: Queue<String> = LinkedList()
        queue.offer(s)

        for (i in s.indices) {
            if (Character.isDigit(s[i])) continue
            val size: Int = queue.size
            for (j in 0 until size) {
                val cur: String = queue.poll()
                val chs = cur.toCharArray()
                chs[i] = chs[i].uppercaseChar()
                queue.offer(String(chs))
                chs[i] = chs[i].lowercaseChar()
                queue.offer(String(chs))
            }
        }

        return LinkedList(queue)
    }
}

class LetterCasePermutationDFS : LetterCasePermutation {
    override operator fun invoke(s: String): List<String> {
        if (s.isBlank()) {
            return LinkedList()
        }
        val res: MutableList<String> = LinkedList()
        helper(s.toCharArray(), res, 0)
        return res
    }

    fun helper(chs: CharArray, res: MutableList<String>, pos: Int) {
        if (pos == chs.size) {
            res.add(String(chs))
            return
        }
        if (chs[pos] in '0'..'9') {
            helper(chs, res, pos + 1)
            return
        }
        chs[pos] = chs[pos].lowercaseChar()
        helper(chs, res, pos + 1)
        chs[pos] = chs[pos].uppercaseChar()
        helper(chs, res, pos + 1)
    }
}
