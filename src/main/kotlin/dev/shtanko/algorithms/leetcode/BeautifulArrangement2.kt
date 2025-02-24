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

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 667. Beautiful Arrangement II
 * @see <a href="https://leetcode.com/problems/beautiful-arrangement-ii/">Source</a>
 */
@Medium("https://leetcode.com/problems/beautiful-arrangement-ii")
fun interface BeautifulArrangement2 {
    operator fun invoke(num: Int, k: Int): IntArray
}

/**
 * Approach #2: Construction
 */
@Iterative
class BA2Construction : BeautifulArrangement2 {
    override fun invoke(num: Int, k: Int): IntArray {
        if (num == 0) return intArrayOf()
        val ans = IntArray(num)
        var c = 0
        for (v in 1 until num - k) {
            ans[c++] = v
        }
        for (i in 0..k) {
            ans[c++] = if (i % 2 == 0) num - k + i / 2 else num - i / 2
        }
        return ans
    }
}
