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

import kotlin.math.max

/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 * @see <a href="https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters">Source
 * </a>
 */
fun interface MaxConcatenationOfSubsequence {
    operator fun invoke(arr: List<String>): Int
}

class MaxConcatenationOfSubsequenceSet : MaxConcatenationOfSubsequence {
    override fun invoke(arr: List<String>): Int {
        val dp: MutableList<Int> = ArrayList()
        dp.add(0)
        var res = 0
        for (str in arr) {
            var a = 0
            var dup = 0
            for (c in str.toCharArray()) {
                dup = dup or (a and (1 shl (c.code - 'a'.code)))
                a = a or (1 shl (c.code - 'a'.code))
            }
            if (dup > 0) continue
            for (i in dp.indices.reversed()) {
                if ((dp[i] and a) > 0) continue
                dp.add(dp[i] or a)
                res = max(res.toDouble(), Integer.bitCount(dp[i] or a).toDouble()).toInt()
            }
        }
        return res
    }
}
