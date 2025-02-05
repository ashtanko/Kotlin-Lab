/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.facebook

fun interface ColorfulNumbers {
    fun isColorful(n: Int): String
}

class ColorfulNumbersImpl : ColorfulNumbers {

    override fun isColorful(n: Int): String {
        val digits = getDigits(n)
        val powerSet = getPowerSet(digits)

        val multiplications = mutableSetOf<Int>()
        for (currentSet in powerSet) {
            val multiplication = getMultiplication(currentSet)
            if (multiplication in multiplications) {
                return "Not Colorful"
            }

            multiplications.add(multiplication)
        }

        return "Colorful"
    }

    private fun getDigits(n: Int): List<Int> {
        val digits = mutableListOf<Int>()

        var current = n
        while (current > 0) {
            digits.add(current % 10)
            current /= 10
        }

        return digits
    }

    private fun getPowerSet(nums: List<Int>): List<List<Int>> {
        val powerSet = mutableListOf(listOf<Int>())

        for (num in nums) {
            val currentSize = powerSet.size

            for (i in 0 until currentSize) {
                val current = powerSet[i]
                powerSet.add(current.plus(num))
            }
        }

        return powerSet
    }

    private fun getMultiplication(items: List<Int>): Int {
        var multiplication = 1

        for (item in items) {
            multiplication *= item
        }

        return multiplication
    }
}
