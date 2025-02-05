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

import dev.shtanko.algorithms.MOD
import dev.shtanko.kotlinlang.functions.sum

/**
 * 1711. Count Good Meals
 * @see <a href="https://leetcode.com/problems/count-good-meals/">Source</a>
 */
fun interface CountPairs {
    operator fun invoke(deliciousness: IntArray): Int
}

class CountPairsTwoSum : CountPairs {
    override operator fun invoke(deliciousness: IntArray): Int {
        val map: MutableMap<Int, Int> = HashMap()
        var res: Long = 0

        for (e in deliciousness) {
            for (i in 0..LIMIT) {
                res += map.getOrDefault((1 shl i) - e, 0).toLong()
            }
            map.merge(e, 1) { a: Int, b: Int ->
                sum(a, b)
            }
        }
        return (res % MOD).toInt()
    }

    companion object {
        private const val LIMIT = 21
    }
}
