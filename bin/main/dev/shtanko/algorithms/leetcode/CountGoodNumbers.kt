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

import dev.shtanko.algorithms.MOD

/**
 * 1922. Count Good Numbers
 * @see <a href="https://leetcode.com/problems/count-good-numbers">Source</a>
 */
fun interface CountGoodNumbers {
    operator fun invoke(num: Long): Int
}

class CountGoodNumbersImpl : CountGoodNumbers {
    override fun invoke(num: Long): Int {
        return powerMod(5, num.plus(1).div(2), MOD).times(
            powerMod(4, num / 2, MOD),
        ).mod(MOD)
    }

    private fun powerMod(a: Int, b: Long, mod: Int): Long {
        if (b == 0L) {
            return 1
        }

        val x = powerMod(a, b / 2, mod)
        return if (b % 2 == 0L) {
            x.times(x).mod(mod).toLong()
        } else {
            a.times(x).mod(mod).times(x).mod(mod).toLong()
        }
    }
}
