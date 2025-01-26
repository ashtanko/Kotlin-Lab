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
 * HappyNumber
 */
fun Int.isHappy(): Boolean {
    val inLoop: MutableSet<Int> = HashSet()
    var squareSum: Int
    var remain: Int
    var n = this
    while (inLoop.add(n)) {
        squareSum = 0
        while (n > 0) {
            remain = n % DECIMAL
            squareSum += remain * remain
            n /= DECIMAL
        }
        if (squareSum == 1) return true else n = squareSum
    }
    return false
}
