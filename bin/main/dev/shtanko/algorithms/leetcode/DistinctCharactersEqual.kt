/*
 * Copyright 2023 Oleksii Shtanko
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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 2531. Make Number of Distinct Characters Equal
 * @see <a href="https://leetcode.com/problems/make-number-of-distinct-characters-equal/">Source</a>
 */
fun interface DistinctCharactersEqual {
    fun isItPossible(word1: String, word2: String): Boolean
}

class DistinctCharactersEqualMap : DistinctCharactersEqual {
    override fun isItPossible(word1: String, word2: String): Boolean {
        val mp1: MutableMap<Char, Int> = HashMap()
        val mp2: MutableMap<Char, Int> = HashMap()
        // store freq of chars in word1 in mp1
        for (w1 in word1.toCharArray()) {
            mp1[w1] = mp1.getOrDefault(w1, 0) + 1
        }
        // store freq of chars in word2 in mp2
        for (w2 in word2.toCharArray()) {
            mp2[w2] = mp2.getOrDefault(w2, 0) + 1
        }
        var c1 = 'a'
        while (c1 <= 'z') {
            var c2 = 'a'
            while (c2 <= 'z') {
                if (!mp1.containsKey(c1) || !mp2.containsKey(c2)) {
                    c2++
                    // if any of the char is not present then skip
                    continue
                }
                insertAndRemove(mp1, c2, c1) // insert c2 to word1 and remove c1 from word1
                insertAndRemove(mp2, c1, c2) // insert c1 to word2 and remove c2 from word2
                if (mp1.size == mp2.size) return true // if size of both maps are equal then possible return true

                // reset back the maps
                insertAndRemove(mp1, c1, c2) // insert c1 back to word1 and remove c2 from word1
                insertAndRemove(mp2, c2, c1) // insert c2 back to word2 and remove c1 from word2
                c2++
            }
            c1++
        }
        return false
    }

    private fun insertAndRemove(
        mp: MutableMap<Char, Int>,
        toInsert: Char,
        toRemove: Char,
    ) {
        // made this helper fxn for easy removal from hashmap
        mp[toInsert] = mp.getOrDefault(toInsert, 0) + 1 // increment freq for char to be inserted
        mp[toRemove] = mp.getOrDefault(toRemove, 0) - 1 // decrement freq for char to be removed
        // if freq of that char reaches zero, then remove the key from hashmap
        if (mp[toRemove] == 0) mp.remove(toRemove)
    }
}

class DistinctCharactersEqualArray : DistinctCharactersEqual {
    override fun isItPossible(word1: String, word2: String): Boolean {
        val map1 = IntArray(ALPHABET_LETTERS_COUNT)
        val map2 = IntArray(ALPHABET_LETTERS_COUNT)

        // store frequency of characters
        for (element in word1) map1[element - 'a']++
        for (element in word2) map2[element - 'a']++

        var count1 = 0
        var count2 = 0
        // count no of distinct characters in each string
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            if (map1[i] > 0) count1++
            if (map2[i] > 0) count2++
        }

        // as explained in step 3
        if (count1 == count2 && word1.length == word2.length) return true

        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            for (j in 0 until ALPHABET_LETTERS_COUNT) {
                if (map1[i] == 0 || map2[j] == 0) continue
                var uniqueCharInMap1 = count1
                var uniqueCharInMap2 = count2

                // changing char count for map1
                if (map1[j] == 0) uniqueCharInMap1++
                map1[j]++
                if (map1[i] == 1) uniqueCharInMap1--
                map1[i]--

                // changing char count for map2
                if (map2[i] == 0) uniqueCharInMap2++
                map2[i]++
                if (map2[j] == 1) uniqueCharInMap2--
                map2[j]--

                // if count of unique chars are same, return true
                if (uniqueCharInMap1 == uniqueCharInMap2) return true
                map1[j]--
                map1[i]++
                map2[i]--
                map2[j]++
            }
        }

        return false
    }
}
