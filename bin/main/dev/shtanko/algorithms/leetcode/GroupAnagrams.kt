/*
 * Copyright 2024 Oleksii Shtanko
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

import java.util.Arrays

/**
 * 49. Group Anagrams
 * @see <a href="https://leetcode.com/problems/group-anagrams">Source</a>
 */
fun interface GroupAnagrams {
    operator fun invoke(strs: Array<String>): List<List<String>>
}

class GroupAnagramsMap : GroupAnagrams {
    override fun invoke(strs: Array<String>): List<List<String>> {
        val map: MutableMap<String, MutableList<String>> = HashMap()

        for (word in strs) {
            val chars = word.toCharArray()
            Arrays.sort(chars)
            val sortedWord = String(chars)

            if (!map.containsKey(sortedWord)) {
                map[sortedWord] = ArrayList()
            }

            map[sortedWord]?.add(word)
        }

        return ArrayList<List<String>>(map.values)
    }
}
