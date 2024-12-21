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
import kotlin.math.pow

fun interface NarcissisticNumber {
    fun isArmstrong(number: Int): Boolean
}

class NarcissisticNumberImpl : NarcissisticNumber {

    /**
     * Function to check whether the number is armstrong number or not.
     * @param number Number
     * @return true if the number is armstrong.
     * @return false if the number is not armstrong.
     */
    override fun isArmstrong(number: Int): Boolean {
        if (number < 0) {
            return false
        }
        var sum = 0
        var temp = number
        val totalDigits = numberOfDigits(number)
        while (temp > 0) {
            val rem = temp % DECIMAL
            sum += rem.toDouble().pow(totalDigits.toDouble()).toInt()
            temp /= DECIMAL
        }
        return number == sum
    }

    private fun numberOfDigits(num: Int): Int {
        var n = num
        var totalDigits = 0
        while (n > 0) {
            n /= DECIMAL
            totalDigits++
        }
        return totalDigits
    }
}
