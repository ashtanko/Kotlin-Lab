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

fun Pair<Int, Int>.selfDividingNumbers(): List<Int> {
    val list: MutableList<Int> = ArrayList()
    var i = first
    while (i <= second) {
        if (i.isValid()) {
            list.add(i)
        }
        i++
    }
    return list
}

private fun Int.isValid(): Boolean {
    var cur = this
    while (cur != 0) {
        val digit = cur % DECIMAL
        if (digit == 0 || this % digit != 0) {
            return false
        }
        cur /= DECIMAL
    }
    return true
}
