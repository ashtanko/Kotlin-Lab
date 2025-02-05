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

/**
 * 402. Remove K Digits
 * @see <a href="https://leetcode.com/problems/remove-k-digits">Source</a>
 */
fun interface RemoveKDigits {
    operator fun invoke(num: String, k: Int): String
}

class RemoveKDigitsStack : RemoveKDigits {
    override fun invoke(num: String, k: Int): String {
        val stack = mutableListOf<Char>()
        var removed = 0

        for (digit in num) {
            while (stack.isNotEmpty() && removed < k && stack.last() > digit) {
                stack.removeAt(stack.size - 1)
                removed++
            }
            stack.add(digit)
        }

        while (removed < k) {
            stack.removeAt(stack.size - 1)
            removed++
        }

        val result = stack.joinToString("").trimStart('0')
        return if (result.isEmpty()) "0" else result
    }
}
