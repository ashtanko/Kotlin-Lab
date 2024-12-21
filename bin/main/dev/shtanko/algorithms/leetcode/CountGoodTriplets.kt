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

import kotlin.math.abs

fun countGoodTriplets(arr: IntArray, a: Int, b: Int, c: Int): Int {
    var count = 0
    val n = arr.size
    for (i in 0 until n - 2) {
        for (j in i + 1 until n - 1) {
            if (abs(arr[i] - arr[j]) <= a) {
                for (k in j + 1 until n) {
                    if (abs(arr[j] - arr[k]) <= b && abs(arr[k] - arr[i]) <= c) {
                        count++
                    }
                }
            }
        }
    }
    return count
}
