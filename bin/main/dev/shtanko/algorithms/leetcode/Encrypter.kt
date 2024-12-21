/*
 * Copyright 2022 Oleksii Shtanko
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

class Encrypter(val keys: CharArray, val values: Array<String>, dictionary: Array<String>) {

    var enc: MutableMap<Char, String> = HashMap()
    var count: MutableMap<String, Int> = HashMap()

    init {
        for (i in keys.indices) {
            enc[keys[i]] = values[i]
        }

        count = HashMap()
        for (w in dictionary) {
            val e = encrypt(w)
            count[e] = (count[e] ?: 0) + 1
        }
    }

    fun encrypt(word1: String): String {
        val res = StringBuilder()
        for (element in word1) {
            res.append(enc.getOrDefault(element, "#"))
        }
        return res.toString()
    }

    fun decrypt(word2: String): Int {
        return count.getOrDefault(word2, 0)
    }
}
