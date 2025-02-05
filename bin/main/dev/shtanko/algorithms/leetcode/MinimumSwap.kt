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
 * 1247. Minimum Swaps to Make Strings Equal
 * @see <a href="https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/">Source</a>
 */
fun interface MinimumSwap {
    operator fun invoke(s1: String, s2: String): Int
}

class MinimumSwapSimple : MinimumSwap {
    override operator fun invoke(s1: String, s2: String): Int {
        val n: Int = s1.length
        var x = 0
        var y = 0

        for (i in 0 until n) if (s1[i] != s2[i]) {
            if (s1[i] == 'x') ++x else ++y
        }
        val cnt = x + y
        return if (cnt % 2 == 1) -1 else cnt / 2 + x % 2
    }
}
