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

package dev.shtanko.algorithms.codingbat.recursion1

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.OCTAL

/**
 * Recursion-1 > count8
 * @see <a href="https://codingbat.com/prob/p192383">Source</a>
 */
internal fun interface Count8 {
    operator fun invoke(num: Int): Int
}

class Count8Iterative : Count8 {
    override fun invoke(num: Int): Int {
        var currentNum = num
        var res = 0
        while (currentNum != 0) {
            var currentCount = 0
            val lastDigit = currentNum % DECIMAL
            if (lastDigit == OCTAL) {
                currentCount = 1
                if ((currentNum / DECIMAL) % DECIMAL == OCTAL) {
                    currentCount += 1
                }
            }
            currentNum /= DECIMAL
            res += currentCount
        }
        return res
    }
}

class Count8Recursive : Count8 {
    override fun invoke(num: Int): Int {
        if (num == 0) {
            return 0
        }
        var currentCount = 0
        val lastDigit = num % DECIMAL
        if (lastDigit == OCTAL) {
            currentCount = 1
            if ((num / DECIMAL) % DECIMAL == OCTAL) {
                currentCount += 1
            }
        }

        val remainingCount: Int = invoke(num / DECIMAL)
        return currentCount + remainingCount
    }
}
