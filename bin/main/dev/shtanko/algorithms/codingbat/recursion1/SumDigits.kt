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

/**
 * Recursion-1 > sumDigits
 * @see <a href="https://codingbat.com/prob/p163932">Source</a>
 */
internal fun interface SumDigits {
    operator fun invoke(digits: Int): Int
}

class SumDigitsIterative : SumDigits {
    override fun invoke(digits: Int): Int {
        var res = 0
        var num = digits

        while (num != 0) {
            res += num % DECIMAL
            num /= DECIMAL
        }
        return res
    }
}

class SumDigitsRecursive : SumDigits {
    override fun invoke(digits: Int): Int {
        if (digits < DECIMAL) {
            return digits
        }
        return digits % DECIMAL + invoke(digits / DECIMAL)
    }
}
