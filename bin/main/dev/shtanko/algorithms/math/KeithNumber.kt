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

package dev.shtanko.algorithms.math

import dev.shtanko.algorithms.DECIMAL

/**
 * Keith Number
 * @see <a href="https://en.wikipedia.org/wiki/Keith_number">leetcode page</a>
 */
fun interface KeithNumber {
    fun isKeith(num: Int): Boolean
}

class KeithNumberImpl : KeithNumber {
    override fun isKeith(num: Int): Boolean {
        val terms = ArrayList<Int>()
        var temp: Int = num
        var n = 0
        while (temp > 0) {
            terms.add(temp % DECIMAL)
            temp /= DECIMAL
            n++
        }
        terms.reverse()
        var nextTerm = 0
        var i = n
        while (nextTerm < num) {
            nextTerm = 0
            for (j in 1..n) {
                nextTerm += terms[i - j]
            }
            terms.add(nextTerm)
            i++
        }
        return nextTerm == num
    }
}
