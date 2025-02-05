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

fun interface AbstractFindSubstring {
    operator fun invoke(s: String, words: Array<String>): List<Int>
}

class FindSubstring : AbstractFindSubstring {
    override operator fun invoke(s: String, words: Array<String>): List<Int> {
        val list: MutableList<Int> = ArrayList()
        val n = words.size
        if (n == 0) {
            return list
        }
        val map: MutableMap<String, Int> = HashMap()
        for (word in words) {
            map[word] = map.getOrDefault(word, 0) + 1
        }
        val size = words[0].length
        val window = size * n
        for (i in 0 until size) {
            var start = i
            while (start + window <= s.length) {
                val sub = s.substring(start, start + window)
                val temp: MutableMap<String, Int> = HashMap()
                var j = n
                while (j > 0) {
                    val word = sub.substring(size * (j - 1), size * j)
                    val count = temp.getOrDefault(word, 0) + 1
                    if (count > map.getOrDefault(word, 0)) {
                        break
                    }
                    temp[word] = count
                    --j
                }
                if (j == 0) {
                    list.add(start)
                }
                start += size * j.coerceAtLeast(1)
            }
        }
        return list
    }
}
