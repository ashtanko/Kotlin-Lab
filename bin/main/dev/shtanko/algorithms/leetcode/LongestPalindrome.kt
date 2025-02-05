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

fun String?.longestPalindrome(): String {
    if (this.isNullOrEmpty()) {
        return ""
    }
    val len = this.length
    val dp = Array(len) { BooleanArray(len) }
    var start = 0
    var end = 0
    var max = 0
    for (i in this.indices) {
        for (j in 0..i) {
            if (this[i] == this[j] && (i - j <= 2 || dp[j + 1][i - 1])) {
                dp[j][i] = true
            }
            if (dp[j][i] && max < i - j + 1) {
                max = i - j + 1
                start = j
                end = i
            }
        }
    }
    return this.substring(start, end + 1)
}
