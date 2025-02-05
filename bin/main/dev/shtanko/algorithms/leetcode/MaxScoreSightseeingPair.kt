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

/**
 * Best Sightseeing Pair
 *
 * This function calculates the maximum score of a sightseeing pair in an array.
 * The score of a pair (i, j) is (arr[i] + arr[j] + i - j).
 *
 * @param arr the input array of integers.
 * @return the maximum score of a sightseeing pair.
 */
fun maxScoreSightseeingPair(arr: IntArray): Int {
    var res = 0
    var cur = 0
    for (a in arr) {
        // Update the result with the maximum of the current result and the current value plus the current array element
        res = res.coerceAtLeast(cur + a)
        // Update the current value with the maximum of the current value and the current array element, then decrement
        // by 1
        cur = cur.coerceAtLeast(a) - 1
    }
    return res
}
