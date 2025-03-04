/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.annotations.DFS
import java.lang.Character.isDigit

/**
 * 2060. Check if an Original String Exists Given Two Encoded Strings
 * @see <a href="https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings">
 *     Source</a>
 */
fun interface PossiblyEquals {
    operator fun invoke(s1: String, s2: String): Boolean
}

@DFS
class PossiblyEqualsDFS : PossiblyEquals {
    override operator fun invoke(s1: String, s2: String): Boolean {
        val l1: Int = s1.length
        val l2: Int = s2.length

        val dp = Array(l1 + 1) {
            Array(l2 + 1) {
                arrayOfNulls<Boolean>(LIMIT2)
            }
        }
        return dfs(0, 0, 0, s1.toCharArray(), s2.toCharArray(), dp)
    }

    private fun dfs(
        i: Int,
        j: Int,
        diff: Int,
        s1: CharArray,
        s2: CharArray,
        dp: Array<Array<Array<Boolean?>>>,
    ): Boolean {
        if (i == s1.size && j == s2.size) {
            return diff == 0
        }
        if (dp[i][j][diff + LIMIT1] != null) return dp[i][j][diff + LIMIT1]!!

        if (i < s1.size && j < s2.size && diff == 0 && s1[i] == s2[j] && dfs(i + 1, j + 1, 0, s1, s2, dp)) {
            dp[i][j][LIMIT1] = true
            return true
        }

        if (i < s1.size && !isDigit(s1[i]) && diff > 0 && dfs(i + 1, j, diff - 1, s1, s2, dp)) {
            dp[i][j][diff + LIMIT1] = true
            return true
        }

        if (j < s2.size && !isDigit(s2[j]) && diff < 0 && dfs(i, j + 1, diff + 1, s1, s2, dp)) {
            return true.also { dp[i][j][diff + LIMIT1] = it }
        }

        var k = j
        var value = 0
        while (k < s2.size && isDigit(s2[k])) {
            value = value * DECIMAL + (s2[k].code - '0'.code)
            if (dfs(i, k + 1, diff + value, s1, s2, dp)) {
                dp[i][j][diff + LIMIT1] = true
                return true
            }
            ++k
        }
        dp[i][j][diff + LIMIT1] = true
        return false
    }

    companion object {
        private const val LIMIT1 = 1000
        private const val LIMIT2 = 2000
    }
}
