/*
 * Copyright 2021 Oleksii Shtanko
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

import kotlin.math.abs
import kotlin.math.min

class ShortestWordDistance2(words: Array<String>) {

    private val locations: MutableMap<String, MutableList<Int>> = HashMap()

    init {
        // Prepare a mapping from a word to all it's locations (indices).
        for (i in words.indices) {
            val loc = locations.getOrDefault(words[i], ArrayList())
            loc.add(i)
            locations[words[i]] = loc
        }
    }

    fun shortest(word1: String, word2: String): Int {
        val loc1: MutableList<Int> = locations[word1] ?: ArrayList()
        val loc2: MutableList<Int> = locations[word2] ?: ArrayList()
        // Location lists for both the words
        // the indices will be in SORTED order by default
        var l1 = 0
        var l2 = 0
        var minDiff = Int.MAX_VALUE
        while (l1 < loc1.size && l2 < loc2.size) {
            minDiff = min(minDiff, abs(loc1[l1] - loc2[l2]))
            if (loc1[l1] < loc2[l2]) {
                l1++
            } else {
                l2++
            }
        }

        return minDiff
    }
}
