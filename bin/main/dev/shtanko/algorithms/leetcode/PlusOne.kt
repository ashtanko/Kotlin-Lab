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

data object PlusOne {

    operator fun invoke(digits: IntArray): IntArray {
        val carry = 1
        for (i in digits.size - 1 downTo 0) {
            digits[i] += carry
            if (digits[i] <= MAX_DIGIT_RESTRICTION) {
                return digits
            }
            digits[i] = 0
        }
        val ret = IntArray(digits.size + 1)
        ret[0] = 1
        return ret
    }

    private const val MAX_DIGIT_RESTRICTION = 9
}
