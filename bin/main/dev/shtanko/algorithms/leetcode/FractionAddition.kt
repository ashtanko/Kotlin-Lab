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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.math.gcd

/**
 * 592. Fraction Addition and Subtraction
 * @see <a href="https://leetcode.com/problems/fraction-addition-and-subtraction/">Fraction Addition and Subtraction</a>
 */
fun interface FractionAddition {
    operator fun invoke(expression: String): String
}

class FractionAdditionRegex : FractionAddition {
    override fun invoke(expression: String): String {
        var num = 0
        var denom = 1

        // Separate expression into signed numbers
        val regex = "/|(?=[-+])".toRegex()
        val nums = expression.split(regex).filter { it.isNotEmpty() }

        for (i in nums.indices step 2) {
            val currNum = nums[i].toInt()
            val currDenom = nums[i + 1].toInt()

            num = num * currDenom + currNum * denom
            denom *= currDenom
        }

        val gcd = (num to denom).gcd().let { kotlin.math.abs(it) }

        num /= gcd
        denom /= gcd

        return "$num/$denom"
    }
}
