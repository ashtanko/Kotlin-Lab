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

private const val MAX = 25

fun interface StringPermutationStrategy {
    operator fun invoke(s1: String, s2: String): Boolean

    fun matches(s1map: IntArray, s2map: IntArray): Boolean {
        for (i in 0..MAX) {
            if (s1map[i] != s2map[i]) return false
        }
        return true
    }
}

class PermutationBruteForce : StringPermutationStrategy {

    private var flag = false

    override operator fun invoke(s1: String, s2: String): Boolean {
        permute(s1, s2, 0)
        return flag
    }

    fun swap(s: String, i0: Int, i1: Int): String {
        if (i0 == i1) return s
        val s1 = s.substring(0, i0)
        val s2 = s.substring(i0 + 1, i1)
        val s3 = s.substring(i1 + 1)
        return s1 + s[i1] + s2 + s[i0] + s3
    }

    private fun permute(str1: String, s2: String, l: Int) {
        var s1 = str1
        if (l == s1.length) {
            if (s2.indexOf(s1) >= 0) flag = true
        } else {
            for (i in l until s1.length) {
                s1 = swap(s1, l, i)
                permute(s1, s2, l + 1)
                s1 = swap(s1, l, i)
            }
        }
    }
}

class PermutationSorting : StringPermutationStrategy {
    override operator fun invoke(s1: String, s2: String): Boolean {
        for (i in 0..s2.length - s1.length) {
            if (s1 == sort(s2.substring(i, i + s1.length))) return true
        }
        return false
    }

    private fun sort(s: String): String {
        val t = s.toCharArray()
        t.sort()
        return String(t)
    }
}

class PermutationHashmap : StringPermutationStrategy {
    override operator fun invoke(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false

        val s1map: HashMap<Char?, Int?> = HashMap()

        for (i in s1.indices) s1map[s1[i]] =
            s1map.getOrDefault(s1[i], 0)!! + 1
        for (i in 0..s2.length - s1.length) {
            val s2map: HashMap<Char?, Int> = HashMap()
            for (j in s1.indices) {
                s2map[s2[i + j]] =
                    s2map.getOrDefault(s2[i + j], 0) + 1
            }
            if (matches(s1map, s2map)) return true
        }
        return false
    }

    private fun matches(s1map: HashMap<Char?, Int?>, s2map: HashMap<Char?, Int>): Boolean {
        for (key in s1map.keys) {
            s1map[key]?.let {
                if (it - s2map.getOrDefault(key, -1) != 0) return false
            }
        }
        return true
    }
}

class PermutationArray : StringPermutationStrategy {

    override operator fun invoke(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val s1map = IntArray(MAX + 1)
        for (i in s1.indices) s1map[s1[i] - 'a']++
        for (i in 0..s2.length - s1.length) {
            val s2map = IntArray(MAX + 1)
            for (j in s1.indices) {
                s2map[s2[i + j] - 'a']++
            }
            if (matches(s1map, s2map)) return true
        }
        return false
    }
}

class PermutationSlidingWindow : StringPermutationStrategy {

    override operator fun invoke(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val s1map = IntArray(MAX + 1)
        val s2map = IntArray(MAX + 1)
        for (i in s1.indices) {
            s1map[s1[i] - 'a']++
            s2map[s2[i] - 'a']++
        }
        for (i in 0 until s2.length - s1.length) {
            if (matches(s1map, s2map)) return true
            s2map[s2[i + s1.length] - 'a']++
            s2map[s2[i] - 'a']--
        }
        return matches(s1map, s2map)
    }
}

class PermutationOptimizedSlidingWindow : StringPermutationStrategy {
    override operator fun invoke(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val s1map = IntArray(MAX + 1)
        val s2map = IntArray(MAX + 1)
        for (i in s1.indices) {
            s1map[s1[i] - 'a']++
            s2map[s2[i] - 'a']++
        }
        var count = 0
        for (i in 0..MAX) if (s1map[i] == s2map[i]) count++
        for (i in 0 until s2.length - s1.length) {
            val r: Int = s2[i + s1.length] - 'a'
            val l: Int = s2[i] - 'a'
            if (count == MAX + 1) return true
            s2map[r]++
            if (s2map[r] == s1map[r]) count++ else if (s2map[r] == s1map[r] + 1) count--
            s2map[l]--
            if (s2map[l] == s1map[l]) count++ else if (s2map[l] == s1map[l] - 1) count--
        }
        return count == MAX + 1
    }
}
