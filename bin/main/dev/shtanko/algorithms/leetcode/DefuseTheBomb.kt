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

import dev.shtanko.algorithms.annotations.SlidingWindow
import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1652. Defuse the Bomb
 * @see <a href="https://leetcode.com/problems/defuse-the-bomb/">Defuse the Bomb</a>
 */
@Easy("https://leetcode.com/problems/defuse-the-bomb/")
fun interface DefuseTheBomb {
    operator fun invoke(code: IntArray, k: Int): IntArray
}

@SlidingWindow
data object DefuseTheBombSlidingWindow : DefuseTheBomb {
    override fun invoke(code: IntArray, k: Int): IntArray {
        val result = IntArray(code.size) { 0 }
        if (k == 0) return result

        var start: Int
        var end: Int
        var windowSum = 0

        if (k > 0) {
            start = 1
            end = k
        } else {
            start = code.size - Math.abs(k)
            end = code.size - 1
        }

        for (i in start..end) {
            windowSum += code[i % code.size]
        }

        for (i in code.indices) {
            result[i] = windowSum
            windowSum -= code[start % code.size]
            windowSum += code[(end + 1) % code.size]
            start++
            end++
        }

        return result
    }
}
