/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.extensions.commonPrefix

/**
 * Longest Common Prefix.
 * @see <a href="https://leetcode.com/problems/longest-common-prefix/">Source</a>
 */
fun interface LongestCommonPrefix {
    operator fun invoke(strs: Array<String>): String
}

/**
 * Approach 1: Horizontal scanning
 */
class LCPHorizontalScanning : LongestCommonPrefix {
    override operator fun invoke(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        var prefix: String = strs.first()
        for (i in 1 until strs.size) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length - 1)
                if (prefix.isEmpty()) return ""
            }
        }
        return prefix
    }
}

/**
 * Approach 2: Vertical scanning
 */
class LCPVerticalScanning : LongestCommonPrefix {
    override operator fun invoke(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        for (i in strs[0].indices) {
            val c: Char = strs[0][i]
            for (j in 1 until strs.size) {
                if (i == strs[j].length || strs[j][i] != c) return strs[0].substring(0, i)
            }
        }
        return strs[0]
    }
}

/**
 * Approach 3: Divide and conquer
 */
class LCPDivideAndConquer : LongestCommonPrefix {
    override operator fun invoke(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        return longestCommonPrefix(strs, 0, strs.size - 1)
    }

    private fun longestCommonPrefix(strs: Array<String>, l: Int, r: Int): String {
        return if (l == r) {
            strs[l]
        } else {
            val mid = l.plus(r).div(2)
            val lcpLeft = longestCommonPrefix(strs, l, mid)
            val lcpRight = longestCommonPrefix(strs, mid + 1, r)
            (lcpLeft to lcpRight).commonPrefix()
        }
    }
}
