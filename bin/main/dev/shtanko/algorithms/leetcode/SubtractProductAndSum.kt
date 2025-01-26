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

import dev.shtanko.algorithms.DECIMAL

/**
 * This extension function calculates the difference between the product and sum of the digits of an integer.
 * The function iterates over each digit of the integer, calculates the product and sum of the digits, and then
 * returns the difference.
 *
 * @receiver Int The integer on which the function is invoked.
 * @return The difference between the product and sum of the digits of the integer.
 */
fun Int.calculateDifferenceBetweenProductAndSum(): Int {
    // Initialize the integer and the sum and product of its digits
    var number = this
    var sumOfDigits = 0
    var productOfDigits = 1

    // Iterate over each digit of the integer
    while (number > 0) {
        // Calculate the product and sum of the digits
        productOfDigits *= number % DECIMAL
        sumOfDigits += number % DECIMAL
        number /= DECIMAL
    }

    // Return the difference between the product and sum of the digits
    return productOfDigits - sumOfDigits
}
