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

package dev.shtanko.algorithms.exercises

import kotlin.math.pow

/**
 * Greatest power of two
 */
class MaxPowerOfTwo {

    /**
     * @param n the number.
     */
    fun perform(n: Int): Int {
        val powers = getPowers()
        if (n == 0) return 0
        if (powers.contains(n)) {
            return n
        }
        for (p in 1 until powers.size) {
            if (n < powers[p]) {
                return powers[p - 1]
            }
        }
        return 0
    }

    /**
     * Decompose [n] into the sum of powers of 2
     * @param n the number.
     */
    fun decompose(n: Int): List<Int> {
        val data = mutableListOf<Int>()
        var j = n
        while (j != 0) {
            val powerOf2 = MaxPowerOfTwo().perform(j)
            data.add(powerOf2)
            j -= powerOf2
        }
        return data
    }
}

fun getPowers(power: Double = 2.0): List<Int> {
    val powers = mutableListOf<Int>()
    var value: Int
    var i = 0
    while (true) {
        value = power.pow(i).toInt()
        if (value >= Int.MAX_VALUE) {
            break
        }
        powers.add(value)
        i++
    }
    return powers
}
