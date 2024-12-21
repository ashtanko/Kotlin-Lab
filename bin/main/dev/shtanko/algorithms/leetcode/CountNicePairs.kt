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

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.MOD

/**
 * 1814. Count Nice Pairs in an Array
 * @see <a href="https://leetcode.com/problems/count-nice-pairs-in-an-array">Source</a>
 */
fun interface CountNicePairs {
    operator fun invoke(nums: IntArray): Int

    fun rev(a: Int): Int {
        var a0 = a
        var b = 0
        while (a0 > 0) {
            b = b * DECIMAL + a0 % DECIMAL
            a0 /= DECIMAL
        }
        return b
    }
}

/**
 * Straight Forward solution
 */
class CountNicePairsSF : CountNicePairs {
    override operator fun invoke(nums: IntArray): Int {
        var res = 0
        val count: MutableMap<Int, Int> = HashMap()
        for (a in nums) {
            val b = rev(a)
            val v = count[a - b] ?: 0
            count[a - b] = v + 1
            res = (res + v) % MOD
        }
        return res
    }
}

class CountNicePairsTwoSum : CountNicePairs {
    override operator fun invoke(nums: IntArray): Int {
        val m: MutableMap<Int, Int> = HashMap()
        var res = 0
        for (n in nums) {
            val comp = n - rev(n)
            val prev = m.getOrDefault(comp, 0)
            res = (res + prev) % MOD
            m[comp] = prev + 1
        }
        return res
    }
}
