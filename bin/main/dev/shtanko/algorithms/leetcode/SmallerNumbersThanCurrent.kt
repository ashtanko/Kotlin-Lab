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

import java.util.TreeMap

fun IntArray.smallerNumbersThanCurrentNaive(): IntArray {
    val map: MutableMap<Int, Int> = HashMap()
    val copy = this.clone()
    copy.sort()
    for (i in indices) {
        map.putIfAbsent(copy[i], i)
    }
    for (i in indices) {
        copy[i] = map[this[i]]!!
    }
    return copy
}

fun IntArray.smallerNumbersThanCurrent(): IntArray {
    val map: MutableMap<Int, Int> = TreeMap()
    for (num in this) { // O(nlgn)
        map[num] = map.getOrDefault(num, 0) + 1
    }
    var lessNumbers = 0
    for (num in map.keys) { // O(n)
        val tmp = map[num]!!
        map[num] = lessNumbers
        lessNumbers += tmp
    }
    for (i in this.indices) { // O(n)
        this[i] = map[this[i]]!!
    }
    return this
}
