/*
 * Copyright 2020 Alexey Shtanko
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

package dev.shtanko.algorithms.leetcode

fun Int.divisorGame(): Boolean {
    return this % 2 == 0
}

fun Int.divisorGameNaive(): Boolean {
    return this and 1 == 0
}

fun Int.divisorGameBruteForce(): Boolean {
    for (x in 1 until this) {
        if (this % x == 0 && !(this - x).divisorGame()) {
            // if Bob fails, Alice wins
            return true
        }
    }
    return false
}
