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

import kotlin.math.max

/**
 * 1624. Largest Substring Between Two Equal Characters
 * @see <a href="https://leetcode.com/problems/largest-substring-between-two-equal-characters">Source</a>
 */
fun interface SubstringFinder {
    /**
     * This function finds the largest substring between two equal characters in a string.
     * @param str The input string.
     * @return The length of the largest substring between two equal characters.
     */
    operator fun invoke(str: String): Int
}

/**
 * This class implements the SubstringFinder interface using a brute force approach.
 * It iterates over each pair of characters in the string and checks if they are equal.
 * If they are, it updates the maximum length of the substring found so far.
 */
class SubstringFinderBF : SubstringFinder {
    /**
     * This function finds the largest substring between two equal characters in a string using a brute force approach.
     * @param str The input string.
     * @return The length of the largest substring between two equal characters.
     */
    override fun invoke(str: String): Int {
        var ans = -1
        for (left in str.indices) {
            for (right in left + 1 until str.length) {
                if (str[left] == str[right]) {
                    ans = max(ans.toDouble(), (right - left - 1).toDouble()).toInt()
                }
            }
        }

        return ans
    }
}

/**
 * This class implements the SubstringFinder interface using a hashmap to store the first index of each character.
 * It iterates over each character in the string and checks if it has been seen before.
 * If it has, it updates the maximum length of the substring found so far.
 */
class SubstringFinderHashMap : SubstringFinder {
    /**
     * This function finds the largest substring between two equal characters in a string using a hashmap.
     * @param str The input string.
     * @return The length of the largest substring between two equal characters.
     */
    override fun invoke(str: String): Int {
        val firstIndex: MutableMap<Char, Int> = HashMap()
        var ans = -1

        for (i in str.indices) {
            if (firstIndex.containsKey(str[i])) {
                ans = max(ans.toDouble(), (i - firstIndex.getOrDefault(str[i], 0) - 1).toDouble())
                    .toInt()
            } else {
                firstIndex[str[i]] = i
            }
        }

        return ans
    }
}
