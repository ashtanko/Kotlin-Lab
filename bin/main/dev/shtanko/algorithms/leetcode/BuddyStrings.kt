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

/**
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A
 * so that the result equals B.
 */
fun Pair<String, String>.buddyStrings(): Boolean {
    if (first.length != second.length) return false

    if (first == second) {
        val characters = hashSetOf<Char>()
        for (c in first.toCharArray()) {
            characters.add(c)
        }
        return characters.size < first.length
    }
    val dif = mutableListOf<Int>()
    for (i in first.indices) {
        if (first[i] != second[i]) {
            dif.add(i)
        }
    }
    return dif.size == 2 && first[dif[0]] == second[dif[1]] && first[dif[1]] == second[dif[0]]
}
