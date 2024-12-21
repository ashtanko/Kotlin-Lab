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

import kotlin.math.log10

private const val POWER = 3

fun interface PowerOfThreeStrategy {
    fun isPowerOfThree(n: Int): Boolean
}

class POTLoopIteration : PowerOfThreeStrategy {

    override fun isPowerOfThree(n: Int): Boolean {
        var num = n
        if (num < 1) {
            return false
        }

        while (num % POWER == 0) {
            num /= POWER
        }

        return num == 1
    }
}

class POTBaseConversion : PowerOfThreeStrategy {
    override fun isPowerOfThree(n: Int): Boolean {
        return n.toString(POWER).matches("^10*$".toRegex())
    }
}

class POTMathematics : PowerOfThreeStrategy {
    override fun isPowerOfThree(n: Int): Boolean {
        val local = log10(n.toDouble()) / log10(POWER.toDouble())

        return local % 1.0 == 0.0
    }
}

class POTIntegerLimitations : PowerOfThreeStrategy {

    companion object {
        private const val LIMIT = 1162261467
    }

    override fun isPowerOfThree(n: Int): Boolean {
        return n > 0 && LIMIT % n == 0
    }
}
