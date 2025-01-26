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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.PriorityQueue

/**
 * 1405. Longest Happy String
 * @see <a href="https://leetcode.com/problems/longest-happy-string/">Source</a>
 */
@Medium("https://leetcode.com/problems/longest-happy-string")
fun interface LongestHappyString {
    operator fun invoke(a: Int, b: Int, c: Int): String
}

class LongestHappyStringPriorityQueue : LongestHappyString {
    override fun invoke(a: Int, b: Int, c: Int): String {
        val pq = PriorityQueue<Pair> { x, y -> y.count - x.count }

        // Add the counts of a, b and c to the priority queue.
        if (a > 0) pq.add(Pair(a, 'a'))
        if (b > 0) pq.add(Pair(b, 'b'))
        if (c > 0) pq.add(Pair(c, 'c'))

        val ans = StringBuilder()

        while (pq.isNotEmpty()) {
            val p = pq.poll()
            var count = p.count
            val character = p.character

            // If three consecutive characters exist, pick the second most frequent character.
            if (ans.length >= 2 && ans[ans.length - 1] == character && ans[ans.length - 2] == character) {
                if (pq.isEmpty()) break

                val temp = pq.poll()
                ans.append(temp.character)
                if (temp.count - 1 > 0) {
                    pq.add(Pair(temp.count - 1, temp.character))
                }
            } else {
                count--
                ans.append(character)
            }

            // If count is greater than zero, add it back to the priority queue.
            if (count > 0) {
                pq.add(Pair(count, character))
            }
        }

        return ans.toString()
    }

    data class Pair(val count: Int, val character: Char)
}
