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
 * 1945. Sum of Digits of String After Convert
 * @see <a href="https://leetcode.com/problems/sum-of-digits-of-string-after-convert/">Source</a>
 */
fun interface GetLucky {
    operator fun invoke(str: String, k: Int): Int
}

class GetLuckySolution : GetLucky {
    override fun invoke(str: String, k: Int): Int {
        // Convert each character in the string to its corresponding numeric value
        var number = str.map { (it - 'a' + 1).toString() }.joinToString("")

        // Perform the transformation `k` times
        var remainingK = k
        while (remainingK > 0) {
            val temp = number.sumOf { it.toString().toInt() } // Sum the digits of the current number
            number = temp.toString() // Convert the sum back to a string
            remainingK -= 1
        }
        return number.toInt() // Return the final result as an integer
    }
}
