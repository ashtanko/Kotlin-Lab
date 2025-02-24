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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.utils.Dp

/**
 * 139. Word Break
 */
fun interface WordBreak {
    operator fun invoke(s: String, wordDict: List<String>): Boolean
}

class WordBreakBruteForce : WordBreak {
    override operator fun invoke(s: String, wordDict: List<String>): Boolean {
        return wb(s, HashSet(wordDict))
    }

    private fun wb(s: String, set: Set<String>): Boolean {
        val len = s.length
        if (len == 0) {
            return true
        }
        for (i in 1..len) {
            if (set.contains(s.substring(0, i)) && wb(s.substring(i), set)) {
                return true
            }
        }
        return false
    }
}

@Dp
class WordBreakDP : WordBreak {
    override operator fun invoke(s: String, wordDict: List<String>): Boolean {
        val dp = BooleanArray(s.length + 1)
        val set: MutableSet<String> = HashSet()
        set.addAll(wordDict)

        dp[0] = true
        for (i in 1..s.length) {
            for (j in i - 1 downTo 0) {
                dp[i] = dp[j] && set.contains(s.substring(j, i))
                if (dp[i]) break
            }
        }
        return dp[s.length]
    }
}

/**
 * https://leetcode.com/problems/word-break/
 *
 * Time Complexity:     O(L ^ 2)
 * Space Complexity:    O(L)
 *
 * References:
 *  https://leetcode.com/problems/word-break/discuss/43819/DFS-with-Path-Memorizing-Java-Solution
 */
@DFS
class WordBreakDFS : WordBreak {
    override operator fun invoke(s: String, wordDict: List<String>): Boolean {
        if (s.isEmpty()) return false

        val wordSet = HashSet(wordDict)
        val seen = HashSet<Int>()

        return dfs(0, s, seen, wordSet)
    }

    private fun dfs(
        idx: Int,
        s: String,
        seen: HashSet<Int>,
        wordSet: HashSet<String>,
    ): Boolean {
        val len = s.length

        if (idx == len) return true

        if (seen.contains(idx)) return false

        for (i in idx + 1..len) {
            val sub = s.substring(idx, i)

            if (!wordSet.contains(sub)) continue

            if (dfs(i, s, seen, wordSet)) return true
        }

        seen.add(idx)
        return false
    }
}

/**
 * https://leetcode.com/problems/word-break/
 *
 * Time Complexity:     O(L ^ 2) + O(N * L) / O(N) ~ O(L ^ 2)
 * Space Complexity:    O(N)
 */
@BFS
class WordBreakBFS : WordBreak {
    override operator fun invoke(s: String, wordDict: List<String>): Boolean {
        if (s.isEmpty()) return false

        val wordSet = HashSet(wordDict)
        val queue = ArrayDeque<String>()
        val seen = HashSet<String>()

        queue.add(s)
        seen.add(s)

        while (queue.isNotEmpty()) {
            val size = queue.size
            for (sz in 0 until size) {
                val cur = queue.removeFirst()
                val len = cur.length

                if (processSubstring(cur, len, wordSet, seen, queue)) {
                    return true
                }
            }
        }

        return false
    }

    private fun processSubstring(
        cur: String,
        len: Int,
        wordSet: Set<String>,
        seen: MutableSet<String>,
        queue: MutableList<String>,
    ): Boolean {
        for (idx in 1..len) {
            if (!wordSet.contains(cur.substring(0, idx))) continue

            if (idx == len) return true

            val sub = cur.substring(idx)
            if (!seen.add(sub)) continue
            queue.add(sub)
        }
        return false
    }
}

/**
 * https://leetcode.com/problems/word-break/
 *
 * Time Complexity:     O(L ^ 2) + O(totalWords)
 * Space Complexity:    O(L)
 *
 * References:
 *  https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways
 */
class WordBreakDP2 : WordBreak {
    override operator fun invoke(s: String, wordDict: List<String>): Boolean {
        if (s.isEmpty()) return false

        val len = s.length
        val wordSet = HashSet(wordDict)

        val dp = BooleanArray(len + 1)
        dp[0] = true

        for (hi in 1..len) {
            for (lo in 0..hi) {
                if (dp[lo] && wordSet.contains(s.substring(lo, hi))) {
                    dp[hi] = true
                    break
                }
            }
        }

        return dp[len]
    }
}
