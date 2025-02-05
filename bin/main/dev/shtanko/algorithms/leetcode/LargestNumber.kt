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
 * 179. Largest Number
 * @see <a href="https://leetcode.com/problems/largest-number/">Source</a>
 */
fun interface LargestNumber {
    operator fun invoke(nums: IntArray): String
}

data object LargestNumberSort : LargestNumber {
    override fun invoke(nums: IntArray): String {
        val strNums = nums.map { it.toString() }.toTypedArray()
        strNums.sortWith { a, b ->
            // Compare based on concatenated strings
            val order1 = a + b
            val order2 = b + a
            order2.compareTo(order1) // Sort in descending order
        }

        // If the largest number is "0", return "0"
        if (strNums[0] == "0") {
            return "0"
        }

        // Concatenate the sorted numbers to form the largest number
        return strNums.joinToString("")
    }
}
