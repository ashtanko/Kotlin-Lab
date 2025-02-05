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

import kotlin.math.min

/**
 * Approach 1: Dynamic Programming
 */
object ShortestSuperstring {
    operator fun invoke(words: Array<String>): String {
        val n = words.size

        // Populate overlaps
        val overlaps = Array(n) { IntArray(n) }
        for (i in 0 until n) for (j in 0 until n) if (i != j) {
            val m = min(words[i].length, words[j].length)
            for (k in m downTo 0) if (words[i].endsWith(words[j].substring(0, k))) {
                overlaps[i][j] = k
                break
            }
        }

        // dp[mask][i] = most overlap with mask, ending with ith element
        val (dp, parent) = overlap(n, overlaps)

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.
        val perm = IntArray(n)
        val seen = BooleanArray(n)
        var t = 0
        var mask = (1 shl n) - 1

        // p: the last element of perm (last word written left to right)
        var p = 0
        for (j in 0 until n) if (dp[(1 shl n) - 1][j] > dp[(1 shl n) - 1][p]) p = j

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p
            seen[p] = true
            val p2 = parent[mask][p]
            mask = mask xor (1 shl p)
            p = p2
        }

        // Reverse perm
        for (i in 0 until t / 2) {
            val v = perm[i]
            perm[i] = perm[t - 1 - i]
            perm[t - 1 - i] = v
        }

        // Fill in remaining words not yet added
        for (i in 0 until n) if (!seen[i]) perm[t++] = i

        // Reconstruct final answer given perm
        val ans = StringBuilder(words[perm[0]])
        for (i in 1 until n) {
            val overlap = overlaps[perm[i - 1]][perm[i]]
            ans.append(words[perm[i]].substring(overlap))
        }
        return ans.toString()
    }

    private fun overlap(n: Int, overlaps: Array<IntArray>): Pair<Array<IntArray>, Array<IntArray>> {
        val dp = Array(1 shl n) { IntArray(n) }
        val parent = Array(1 shl n) { IntArray(n) { -1 } }
        for (mask in 0 until (1 shl n)) {
            for (bit in 0 until n) if (mask shr bit and 1 > 0) {
                // Let's try to find dp[mask][bit].  Previously, we had
                // a collection of items represented by pmask.
                val pmask = mask xor (1 shl bit)
                if (pmask == 0) continue
                for (i in 0 until n) if (pmask shr i and 1 > 0) {
                    // For each bit i in pmask, calculate the value
                    // if we ended with word i, then added word 'bit'.
                    val value = dp[pmask][i] + overlaps[i][bit]
                    if (value > dp[mask][bit]) {
                        dp[mask][bit] = value
                        parent[mask][bit] = i
                    }
                }
            }
        }
        return dp to parent
    }
}
