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

import dev.shtanko.algorithms.MOD

/**
 * Sum of Subsequence Widths.
 * @see <a href="https://leetcode.com/problems/sum-of-subsequence-widths/">Source</a>
 */
object SumSubsequenceWidths {

    operator fun invoke(inputArray: IntArray): Int {
        if (inputArray.isEmpty()) return 0
        val arraySize: Int = inputArray.size
        inputArray.sort()

        val powerOfTwo = LongArray(arraySize)
        powerOfTwo[0] = 1
        for (index in 1 until arraySize) powerOfTwo[index] = powerOfTwo[index - 1] * 2 % MOD

        var result: Long = 0
        for (index in 0 until arraySize) result =
            (result + (powerOfTwo[index] - powerOfTwo[arraySize - 1 - index]) * inputArray[index]) % MOD

        return result.toInt()
    }
}
