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

package dev.shtanko.algorithms.dp

import kotlin.math.max

/**
 * RodCuttingProblem
 * solve the problem using dynamic programming
 */
fun cutRod(price: IntArray, n: Int): Int {
    // Declaring a 2D array, T
    val arr = Array(n - 1) { IntArray(n + 1) }

    // Initializing the array to all zeros
    for (i in 0 until n - 1) {
        for (j in 0 until n + 1) {
            arr[i][j] = 0
        }
    }
    for (i in 0 until n - 1) {
        for (j in 0 until n + 1) {
            // First column => 0 length of rod => 0 profit
            when {
                j == 0 -> {
                    continue
                }

                i == 0 -> {
                    arr[i][j] = price[i] + arr[i][j - i - 1]
                }

                j - i - 1 < 0 -> {
                    arr[i][j] = arr[i - 1][j]
                }

                else -> {
                    arr[i][j] = max(arr[i - 1][j], price[i] + arr[i][j - i - 1])
                }
            }
        }
    }
    return arr[n - 2][n]
}
