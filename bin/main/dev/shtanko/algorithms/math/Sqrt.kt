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

package dev.shtanko.algorithms.math

import kotlin.math.abs

private const val EPSILON = 1e-15

/**
 * Calculates the square root of an integer number using Newton's method.
 *
 * @param number the integer number whose square root is to be calculated.
 * @param epsilon the acceptable error threshold for the approximation.
 * @return the square root of the given integer number.
 */
fun sqrt(number: Int, epsilon: Double = EPSILON): Double {
    return sqrt(number.toDouble(), epsilon)
}

/**
 * Calculates the square root of a double number using Newton's method.
 *
 * @param number the double number whose square root is to be calculated.
 * @param epsilon the acceptable error threshold for the approximation.
 * @return the square root of the given double number.
 */
fun sqrt(number: Double, epsilon: Double = EPSILON): Double {
    if (number < 0) return Double.NaN
    var approx = number
    while (abs(approx - number / approx) > epsilon * approx)
        approx = (number / approx + approx) / 2.0
    return approx
}
