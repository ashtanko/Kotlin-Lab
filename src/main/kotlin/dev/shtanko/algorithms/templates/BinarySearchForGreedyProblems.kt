/*
 * Copyright 2023 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.templates

/**
 * Binary search: for greedy problems
 */
@Suppress("UNUSED_PARAMETER", "unused")
private fun fn(arr: IntArray?): Int {
    val minimumPossibleAnswer = Int.MAX_VALUE // TODO
    val maximumPossibleAnswer = Int.MAX_VALUE // TODO
    var left: Int = minimumPossibleAnswer
    var right: Int = maximumPossibleAnswer
    while (left <= right) {
        val mid = left + (right - left) / 2
        if (check(mid)) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return left
}

@Suppress("UNUSED_PARAMETER")
private fun check(x: Int): Boolean {
    // this function is implemented depending on the problem
    val b = false // TODO
    return b // TODO
}
