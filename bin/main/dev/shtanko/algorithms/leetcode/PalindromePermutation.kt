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

import java.util.TreeSet

private const val MAX_VALUE = 128

fun interface PalindromePermutationBehavior {
    fun canPermutePalindrome(s: String): Boolean
}

/**
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PalindromePermutationBruteForce : PalindromePermutationBehavior {
    override fun canPermutePalindrome(s: String): Boolean {
        var count = 0
        var i = 0.toChar()
        while (i.code < MAX_VALUE && count <= 1) {
            var ct = 0
            for (j in s.indices) {
                if (s[j] == i) ct++
            }
            count += ct % 2
            i++
        }
        return count <= 1
    }
}

/**
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PalindromePermutationHashMap : PalindromePermutationBehavior {
    override fun canPermutePalindrome(s: String): Boolean {
        val map: HashMap<Char, Int> = HashMap()
        for (i in s.indices) {
            map[s[i]] = map.getOrDefault(s[i], 0) + 1
        }
        var count = 0
        for (key in map.keys) {
            count += map[key]!! % 2
        }
        return count <= 1
    }
}

/**
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PalindromePermutationArray : PalindromePermutationBehavior {
    override fun canPermutePalindrome(s: String): Boolean {
        val map = IntArray(MAX_VALUE)
        for (element in s) {
            map[element.code]++
        }
        var count = 0
        var key = 0
        while (key < map.size && count <= 1) {
            count += map[key] % 2
            key++
        }
        return count <= 1
    }
}

/**
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PalindromePermutationSinglePass : PalindromePermutationBehavior {
    override fun canPermutePalindrome(s: String): Boolean {
        val map = IntArray(MAX_VALUE)
        var count = 0
        for (i in s.indices) {
            map[s[i].code]++
            if (map[s[i].code] % 2 == 0) count-- else count++
        }
        return count <= 1
    }
}

/**
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PalindromePermutationSet : PalindromePermutationBehavior by PalindromePermutationDelegate(HashSet())

/**
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PalindromePermutationTree : PalindromePermutationBehavior by PalindromePermutationDelegate(TreeSet())

class PalindromePermutationDelegate(private val set: MutableSet<Char>) : PalindromePermutationBehavior {
    override fun canPermutePalindrome(s: String): Boolean {
        for (i in s.indices) {
            if (!set.add(s[i])) set.remove(s[i])
        }
        return set.size <= 1
    }
}
