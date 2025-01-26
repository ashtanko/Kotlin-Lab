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

fun IntArray.singleNumber(): Int {
    var res = 0
    var i = 0
    while (i < this.size) {
        res = res xor this[i++]
    }
    return res
}

fun IntArray.singleNumberUsingSet(): Int {
    val set = HashSet<Int>()
    for (i in this) {
        if (!set.remove(i)) set.add(i)
    }
    return if (isEmpty()) 0 else set.iterator().next()
}
