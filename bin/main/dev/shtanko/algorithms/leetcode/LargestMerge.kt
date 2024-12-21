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

/**
 * 1754. Largest Merge Of Two Strings
 * @see <a href="https://leetcode.com/problems/largest-merge-of-two-strings/">Source</a>
 */
fun interface LargestMerge {
    operator fun invoke(word1: String, word2: String): String
}

class LargestMergeGreedy : LargestMerge {
    override operator fun invoke(word1: String, word2: String): String {
        return largestMerge(word1, word2)
    }

    private fun largestMerge(s1: String, s2: String): String {
        if (s1.isEmpty() || s2.isEmpty()) return s1 + s2
        return if (s1 > s2) {
            s1[0].toString() + largestMerge(s1.substring(1), s2)
        } else {
            s2[0].toString() + largestMerge(s1, s2.substring(1))
        }
    }
}
