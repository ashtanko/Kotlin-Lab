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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 670. Maximum Swap
 * @see <a href="https://leetcode.com/problems/maximum-swap/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-swap")
fun interface MaxSwap {
    operator fun invoke(num: Int): Int
}

class MaxSwapGreedy : MaxSwap {
    override fun invoke(num: Int): Int {
        val numStr = num.toString().toCharArray()
        val n = numStr.size
        var maxDigitIndex = -1
        var swapIdx1 = -1
        var swapIdx2 = -1

        // Traverse the string from right to left, tracking the max digit and potential swap
        for (i in n - 1 downTo 0) {
            if (maxDigitIndex == -1 || numStr[i] > numStr[maxDigitIndex]) {
                maxDigitIndex = i // Update the index of the max digit
            } else if (numStr[i] < numStr[maxDigitIndex]) {
                swapIdx1 = i // Mark the smaller digit for swapping
                swapIdx2 = maxDigitIndex // Mark the larger digit for swapping
            }
        }

        // Perform the swap if a valid swap is found
        if (swapIdx1 != -1 && swapIdx2 != -1) {
            val temp = numStr[swapIdx1]
            numStr[swapIdx1] = numStr[swapIdx2]
            numStr[swapIdx2] = temp
        }

        return Integer.parseInt(numStr.joinToString(""))
    }
}
