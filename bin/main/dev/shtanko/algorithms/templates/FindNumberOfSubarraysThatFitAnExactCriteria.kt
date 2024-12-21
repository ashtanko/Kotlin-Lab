/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.templates

/**
 * Find number of subarrays that fit an exact criteria
 */
private fun fn(arr: IntArray, k: Int): Int {
    val counts: MutableMap<Int, Int> = HashMap()
    counts[0] = 1
    var ans = 0
    val curr = 0
    for (num in arr) {
        // do logic to change curr
        ans += counts.getOrDefault(curr - k, 0)
        counts[curr] = counts.getOrDefault(curr, 0) + 1
    }
    return ans
}
