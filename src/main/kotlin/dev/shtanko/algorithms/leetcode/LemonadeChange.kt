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

class LemonadeChange {
    operator fun invoke(bills: IntArray): Boolean {
        var fiveDollarCount = 0
        var tenDollarCount = 0
        for (bill in bills) {
            when {
                bill == FIVE_DOLLAR -> fiveDollarCount++
                bill == TEN_DOLLAR -> {
                    fiveDollarCount--
                    tenDollarCount++
                }

                tenDollarCount > 0 -> {
                    tenDollarCount--
                    fiveDollarCount--
                }

                else -> fiveDollarCount -= THREE_DOLLAR
            }
            if (fiveDollarCount < 0) return false
        }
        return true
    }

    companion object {
        private const val FIVE_DOLLAR = 5
        private const val THREE_DOLLAR = 3
        private const val TEN_DOLLAR = 10
    }
}
