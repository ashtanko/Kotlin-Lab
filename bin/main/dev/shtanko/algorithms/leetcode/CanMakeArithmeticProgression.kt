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

fun IntArray.canMakeArithmeticProgression(): Boolean {
    sort()
    for (i in 2 until size) {
        if (this[i - 1] - this[i] != this[i - 2] - this[i - 1]) {
            return false
        }
    }
    return true
}

fun IntArray.canMakeArithmeticProgressionSet(): Boolean {
    val seen = HashSet<Int>()
    var mi = Int.MAX_VALUE
    var mx = Int.MIN_VALUE
    var n = size
    for (a in this) {
        mi = mi.coerceAtMost(a)
        mx = mx.coerceAtLeast(a)
        seen.add(a)
    }
    var diff = mx - mi
    val local = n - 1
    if (diff % local != 0) {
        return false
    }
    diff /= n - 1
    while (--n > 0) {
        if (!seen.contains(mi)) {
            return false
        }
        mi += diff
    }

    return true
}
