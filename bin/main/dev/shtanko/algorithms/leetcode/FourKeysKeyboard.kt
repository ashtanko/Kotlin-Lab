/*
 * Copyright 2020 Oleksii Shtanko
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
 *  4 Keys Keyboard.
 *  @see <a href="https://leetcode.com/problems/4-keys-keyboard/">Source</a>
 */
fun interface FourKeysKeyboard {
    operator fun invoke(num: Int): Int
}

/**
 * Approach #1: Dynamic Programming.
 */
class FourKeysKeyboardDP : FourKeysKeyboard {
    override fun invoke(num: Int): Int {
        val best = IntArray(num + 1)
        for (k in 1..num) {
            best[k] = best[k - 1] + 1
            for (x in 0 until k - 1) best[k] = max(best[k], best[x] * (k - x - 1))
        }
        return best[num]
    }
}

/**
 * Approach #3: Mathematical.
 */
class FourKeysKeyboardMath : FourKeysKeyboard {
    override fun invoke(inputNumber: Int): Int {
        val quotient = if (inputNumber > MAX_VALUE) (inputNumber - MAX_N_VALUE) / MULTIPLICATION_LIMIT else 0
        return best[inputNumber - MULTIPLICATION_LIMIT * quotient] shl 2 * quotient
    }

    companion object {
        private val best = intArrayOf(
            0, 1, 2, 3, 4, 5, 6, 9, 12,
            16, 20, 27, 36, 48, 64, 81,
        )
        private const val MAX_VALUE = 15
        private const val MAX_N_VALUE = 11
        private const val MULTIPLICATION_LIMIT = 5
    }
}
