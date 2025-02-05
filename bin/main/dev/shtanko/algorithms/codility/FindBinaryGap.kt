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

package dev.shtanko.algorithms.codility

fun findBinaryGap(n: Int): Int {
    val binaryString = Integer.toBinaryString(n)
    var count: Int
    var bigGap = 0
    var temptCount = 0
    for (element in binaryString) {
        if (element == '0') {
            temptCount++
        } else {
            count = temptCount
            if (count > bigGap) {
                bigGap = count
            }
            temptCount = 0
        }
    }
    return bigGap
}
