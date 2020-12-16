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

fun IntArray.minimumAbsDifference(): List<List<Int>> {
    sort()
    val list = this.toList()
    val min = this[1] - first()
    return list.windowed(2).filter {
        it[1] - it[0] == min
    }
}

fun IntArray.minimumAbsDifference2(): List<List<Int>> {
    val res: MutableList<List<Int>> = ArrayList()
    sort()

    var min = Int.MAX_VALUE
    for (i in 0 until size - 1) {
        val diff = this[i + 1] - this[i]
        if (diff < min) {
            min = diff
            res.clear()
            res.add(listOf(this[i], this[i + 1]))
        } else if (diff == min) {
            res.add(listOf(this[i], this[i + 1]))
        }
    }
    return res
}
