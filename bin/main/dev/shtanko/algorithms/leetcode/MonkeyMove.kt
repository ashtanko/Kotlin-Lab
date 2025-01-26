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
 * 2550. Count Collisions of Monkeys on a Polygon
 * @see <a href="https://leetcode.com/problems/count-collisions-of-monkeys-on-a-polygon">Source</a>
 */
fun interface MonkeyMove {
    operator fun invoke(n: Int): Int
}

class MonkeyMoveBitwise : MonkeyMove {
    override fun invoke(n: Int): Int {
        var res: Long = 1
        var base: Long = 2
        var n0 = n
        while (n0 > 0) {
            if (n0 % 2 == 1) res = res * base % MOD
            base = base * base % MOD
            n0 = n0 shr 1
        }
        return ((res - 2 + MOD) % MOD).toInt()
    }
}
