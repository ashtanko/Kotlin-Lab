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
 * Complexity Analysis
 * Time complexity : O(\log_{10}(n))O(log10(n)).
 * We divided the input by 10 for every iteration, so the time complexity is O(\log_{10}(n))O(log10(n))
 * Space complexity : O(1)O(1)
 */
fun Int.isPalindrome(): Boolean {
    var x = this
    val local = x != 0 && x % DECIMAL == 0
    if (x < 0 || local) return false

    var revertedNumber = 0
    while (x > revertedNumber) {
        revertedNumber = revertedNumber * DECIMAL + x % DECIMAL
        x /= DECIMAL
    }
    return x == revertedNumber || x == revertedNumber / DECIMAL
}
