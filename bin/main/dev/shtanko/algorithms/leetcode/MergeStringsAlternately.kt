/*
 * Copyright 2023 Oleksii Shtanko
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

import kotlin.math.max

/**
 * 1768. Merge Strings Alternately
 * @see <a href="https://leetcode.com/problems/merge-strings-alternately/">Source</a>
 */
fun interface MergeStringsAlternately {
    fun mergeAlternately(word1: String, word2: String): String
}

class MergeStringsAlternatelyTwoPointers : MergeStringsAlternately {
    override fun mergeAlternately(word1: String, word2: String): String {
        val m: Int = word1.length
        val n: Int = word2.length
        val result = java.lang.StringBuilder()
        var i = 0
        var j = 0

        while (i < m || j < n) {
            if (i < m) {
                result.append(word1[i++])
            }
            if (j < n) {
                result.append(word2[j++])
            }
        }

        return result.toString()
    }
}

class MergeStringsAlternatelyOnePointer : MergeStringsAlternately {
    override fun mergeAlternately(word1: String, word2: String): String {
        val m: Int = word1.length
        val n: Int = word2.length
        val result = StringBuilder()

        for (i in 0 until max(m, n)) {
            if (i < m) {
                result.append(word1[i])
            }
            if (i < n) {
                result.append(word2[i])
            }
        }

        return result.toString()
    }
}
