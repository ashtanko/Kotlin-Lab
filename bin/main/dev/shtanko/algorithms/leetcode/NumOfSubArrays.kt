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

import dev.shtanko.algorithms.E_9

private const val MODULE = 7

/**
 * Calculates the number of subarrays with an even number of odd integers.
 *
 * Given an array of integers, this function calculates the number of subarrays
 * where the count of odd integers is even. It utilizes bitwise XOR and dynamic
 * programming to efficiently compute the result.
 *
 * @param arr The input array of integers.
 * @return The number of subarrays with an even number of odd integers.
 */
fun numOfSubArrays(arr: IntArray): Int {
    var cur = 0
    var res = 0
    val count = intArrayOf(1, 0)
    val mod = E_9.toInt() + MODULE

    // Iterate through the array and update the count of sub-arrays
    for (a in arr) {
        cur = cur xor (a and 1)
        res = (res + count[1 - cur]) % mod
        count[cur]++
    }
    return res
}
