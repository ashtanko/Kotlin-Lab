/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.gfg

/**
 * Recamans sequence
 * @see <a href="https://www.geeksforgeeks.org/problems/recamans-sequence4856">Source</a>
 */
fun interface RecamansSequence {
    operator fun invoke(num: Int): List<Int>
}

class RecamansSequenceRecursive : RecamansSequence {

    private val seen = HashSet<Int>()

    override fun invoke(num: Int): List<Int> {
        val ans = mutableListOf(0)
        seen.add(0)
        recamanSequenceHelper(num, ans, 0, 1)
        return ans
    }

    private fun recamanSequenceHelper(n: Int, ans: MutableList<Int>, current: Int, index: Int) {
        if (index == n) {
            return
        }

        val nextTerm = if (current - index > 0 && (current - index) !in seen) {
            current - index
        } else {
            current + index
        }

        ans.add(nextTerm)
        seen.add(nextTerm)
        recamanSequenceHelper(n, ans, nextTerm, index + 1)
    }
}
