/*
 * Copyright 2022 Oleksii Shtanko
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
 * 1987. Number of Unique Good Subsequences
 * @see <a href="https://leetcode.com/problems/number-of-unique-good-subsequences/">Source</a>
 */
fun numberOfUniqueGoodSubsequences(binary: String): Int {
    val mod = E + 7
    var ends0 = 0
    var ends1 = 0
    var has0 = 0
    for (i in binary.indices) {
        if (binary[i] == '1') {
            ends1 = (ends0 + ends1 + 1) % mod
        } else {
            ends0 = (ends0 + ends1) % mod
            has0 = 1
        }
    }
    return (ends0 + ends1 + has0) % mod
}

private const val E = 1e9.toInt()
