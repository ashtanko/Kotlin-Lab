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

import java.util.PriorityQueue

/**
 * 451. Sort Characters By Frequency
 * @see <a href="https://leetcode.com/problems/sort-characters-by-frequency/">Source</a>
 */
fun interface FrequencySort {
    operator fun invoke(str: String): String
}

class FrequencyBucketSort : FrequencySort {
    override operator fun invoke(str: String): String {
        val map: MutableMap<Char, Int> = HashMap()
        for (character in str.toCharArray()) {
            map[character] = map.getOrDefault(character, 0) + 1
        }

        val bucket: Array<MutableList<Char>> = Array(str.length + 1) {
            mutableListOf()
        }
        for (key in map.keys) {
            val frequency = map[key] ?: 0
            bucket[frequency].add(key)
        }

        val sb = StringBuilder()
        for (pos in bucket.indices.reversed()) {
            for (character in bucket[pos]) {
                for (i in 0 until pos) {
                    sb.append(character)
                }
            }
        }
        return sb.toString()
    }
}

class FrequencySortPQ : FrequencySort {
    override operator fun invoke(str: String): String {
        val map: MutableMap<Char, Int> = HashMap()
        for (character in str.toCharArray()) {
            map[character] = map.getOrDefault(character, 0) + 1
        }

        val pq: PriorityQueue<Map.Entry<Char, Int>> = PriorityQueue { a, b -> b.value - a.value }
        pq.addAll(map.entries)

        val sb = StringBuilder()
        while (pq.isNotEmpty()) {
            val (key, value) = pq.poll()
            for (i in 0 until value) sb.append(key)
        }
        return sb.toString()
    }
}
