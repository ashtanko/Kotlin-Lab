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

private val m1 = intArrayOf(1, 4, 7, 2, 5, 8)
private val m2 = intArrayOf(2, 5, 8, 1, 4, 7)
private const val ARRAY_SIZE = 10
private const val ZERO_CHAR = '0'

fun largestMultipleOfThree(digits: IntArray): String {
    var sum = 0
    val ds = IntArray(ARRAY_SIZE)
    for (d in digits) {
        ++ds[d]
        sum += d
    }
    while (sum % 3 != 0) {
        val n = if (sum % 3 == 1) m1 else m2
        for (i in n) {
            if (ds[i] > 0) {
                --ds[i]
                sum -= i
                break
            }
        }
    }
    val sb = StringBuilder()
    for (i in ARRAY_SIZE - 1 downTo 0) {
        sb.append(ZERO_CHAR.plus(i).toString().repeat(ds[i]))
    }
    return if (sb.isNotEmpty() && sb[0] == ZERO_CHAR) "0" else sb.toString()
}
