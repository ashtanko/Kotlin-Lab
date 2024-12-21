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

import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.math.gcd
import kotlin.math.ceil
import kotlin.math.min

fun interface NthMagicalNumber {
    operator fun invoke(n: Int, a: Int, b: Int): Int
}

class NthMagicalNumberMath : NthMagicalNumber {

    override operator fun invoke(n: Int, a: Int, b: Int): Int {
        val lcm = a * b / gcd(a, b)
        val cntPerLcm = lcm / a + lcm / b - 1
        val cntLcm = n / cntPerLcm
        val remain = n % cntPerLcm

        val nearest: Double = remain.div(1.0.div(a).plus(1.0.div(b)))
        val remainIdx = min(ceil(nearest / a) * a, ceil(nearest / b) * b).toInt()
        return cntLcm.times(lcm).plus(remainIdx) % MOD
    }
}

class NthMagicalNumberBS : NthMagicalNumber {
    override operator fun invoke(n: Int, a: Int, b: Int): Int {
        val l: Int = a / gcd(a, b) * b

        var lo: Long = 0
        var hi: Long = n.toLong() * min(a, b)
        while (lo < hi) {
            val mi = lo + hi.minus(lo) / 2
            if (mi / a + mi / b - mi / l < n) lo = mi + 1 else hi = mi
        }

        return (lo % MOD).toInt()
    }
}
