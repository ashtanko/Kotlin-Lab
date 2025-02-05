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
 * 2193. Minimum Number of Moves to Make Palindrome
 * @see <a href="https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/">Source</a>
 */
fun interface MinMovesToMakePalindrome {
    operator fun invoke(s: String): Int
}

class MinMovesToMakePalindromeGreedy : MinMovesToMakePalindrome {
    override operator fun invoke(s: String): Int {
        var res = 0
        var s0 = s
        while (s0.isNotEmpty()) {
            val i: Int = s0.indexOf(s0[s0.length - 1])
            if (i == s0.length - 1) {
                res += i / 2
            } else {
                res += i
                s0 = s0.substring(0, i) + s0.substring(i + 1)
            }
            s0 = s0.substring(0, s0.length - 1)
        }
        return res
    }
}
