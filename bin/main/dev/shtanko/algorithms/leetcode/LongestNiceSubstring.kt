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
 * 1763. Longest Nice Substring
 * @see <a href="https://leetcode.com/problems/longest-nice-substring/">Source</a>
 */
fun interface LongestNiceSubstring {
    operator fun invoke(s: String): String
}

class LNSDivideAndConquer : LongestNiceSubstring {
    override operator fun invoke(s: String): String {
        if (s.length < 2) return ""
        val arr = s.toCharArray()
        val set: MutableSet<Char> = HashSet()
        for (c in arr) set.add(c)
        for (i in arr.indices) {
            val c = arr[i]
            if (set.contains(c.uppercaseChar()) && set.contains(c.lowercaseChar())) continue
            val sub1: String = invoke(s.substring(0, i))
            val sub2: String = invoke(s.substring(i + 1))
            return if (sub1.length >= sub2.length) sub1 else sub2
        }
        return s
    }
}
