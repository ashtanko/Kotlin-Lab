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
 * 944. Delete Columns to Make Sorted
 * @see <a href="https://leetcode.com/problems/delete-columns-to-make-sorted/">Source</a>
 */
fun interface MinDeletionSize {
    operator fun invoke(strs: Array<String>): Int
}

class MinDeletionSizeBruteForce : MinDeletionSize {
    override operator fun invoke(strs: Array<String>): Int {
        var count = 0
        for (i in 0 until strs[0].length) {
            for (j in 1 until strs.size) {
                if (strs[j][i] < strs[j - 1][i]) {
                    count++
                    break
                }
            }
        }
        return count
    }
}

class MinDeletionSizeFast : MinDeletionSize {
    override operator fun invoke(strs: Array<String>): Int {
        val len: Int = strs.size
        val wordLen: Int = strs[0].length
        var ans = 0

        for (i in 0 until wordLen) {
            var prev: Char = strs[0][i]
            for (j in 1 until len) {
                val ch: Char = strs[j][i]
                if (ch < prev) {
                    ans++
                    break
                }
                prev = ch
            }
        }
        return ans
    }
}
