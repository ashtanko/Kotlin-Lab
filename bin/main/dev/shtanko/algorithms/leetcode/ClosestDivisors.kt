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

import kotlin.math.sqrt

fun closestDivisors(num: Int): IntArray {
    for (a in sqrt(num.plus(2).toDouble()).toInt() downTo 1) {
        if ((num + 1) % a == 0) return intArrayOf(a, num.plus(1) / a)
        if ((num + 2) % a == 0) return intArrayOf(a, num.plus(2) / a)
    }
    return intArrayOf()
}
