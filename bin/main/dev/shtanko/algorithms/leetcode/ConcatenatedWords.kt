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

/**
 * 472. Concatenated Words
 * @see <a href="https://leetcode.com/problems/concatenated-words/">Source</a>
 */
fun interface ConcatenatedWords {
    fun findAllConcatenatedWordsInADict(words: Array<String>): List<String>
}

class ConcatenatedWordsDP : ConcatenatedWords {
    override fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
        words.sortWith { w1, w2 -> w1.length - w2.length }
        val ans: MutableList<String> = ArrayList()
        val dict: MutableSet<String> = HashSet()
        for (w in words) {
            val dp = BooleanArray(w.length + 1)
            dp[0] = true
            for (i in 1..w.length) {
                for (j in i downTo -1 + 1) {
                    if (dp[j] && dict.contains(w.substring(j, i))) {
                        dp[i] = true
                        break
                    }
                }
            }
            if (w.isNotEmpty() && dp[w.length]) ans.add(w)
            dict.add(w)
        }
        return ans
    }
}
