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

import java.util.LinkedList

fun Array<String>.invoke(): Array<String> {
    val strs = arrayOf("QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM")
    val map: MutableMap<Char, Int> = HashMap()
    for (i in strs.indices) {
        for (c in strs[i].toCharArray()) {
            map[c] = i // put <char, rowIndex> pair into the map
        }
    }
    val res: MutableList<String> = LinkedList()
    for (w in this) {
        if (w == "") continue
        var index = map[w.uppercase()[0]]
        for (c in w.uppercase().toCharArray()) {
            if (map[c] != index) {
                index = -1 // don't need a boolean flag.
                break
            }
        }
        if (index != -1) res.add(w) // if index != -1, this is a valid string
    }
    return res.toTypedArray()
}
