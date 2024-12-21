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

fun interface NumberOfSteps {
    operator fun invoke(inputNumber: Int): Int
}

class NumberOfStepsStraightForward : NumberOfSteps {
    override operator fun invoke(inputNumber: Int): Int {
        return inputNumber.calculateNumberOfSteps()
    }

    private fun Int.calculateNumberOfSteps(): Int {
        if (this == 0) return 0
        var steps = 0
        var currentNumber = this
        var remainder: Int
        while (currentNumber != 0) {
            remainder = currentNumber % 2
            if (remainder == 0) {
                currentNumber /= 2
            } else {
                currentNumber -= 1
            }
            steps++
        }
        return steps
    }
}

class NumberOfStepsBinary : NumberOfSteps {
    override operator fun invoke(inputNumber: Int): Int {
        if (inputNumber == 0) return 0
        var steps = 0
        var currentNumber = inputNumber
        while (currentNumber != 0) {
            steps += if (currentNumber and 1 == 0) 1 else 2
            currentNumber = currentNumber shr 1
        }
        return steps - 1
    }
}
