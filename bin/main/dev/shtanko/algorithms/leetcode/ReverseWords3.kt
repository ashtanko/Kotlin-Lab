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

/**
 * 557. Reverse Words in a String III
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string-iii">Source</a>
 */
fun interface ReverseWords3 {
    operator fun invoke(s: String): String
}

class ReverseWords3BruteForce : ReverseWords3 {
    override operator fun invoke(s: String): String {
        val sb = StringBuilder()
        val sp = s.split(" ")

        for (s0 in sp) {
            sb.append(s0.reversed()).append(" ")
        }

        return sb.toString().trim()
    }
}

class ReverseWords3BruteForce2 : ReverseWords3 {
    override operator fun invoke(s: String): String {
        val words = split(s)
        val res = StringBuilder()
        for (word in words) res.append(reverse(word) + " ")
        return res.toString().trim { it <= ' ' }
    }

    private fun split(s: String): Array<String> {
        val words = ArrayList<String>()
        var word = StringBuilder()
        for (i in s.indices) {
            if (s[i] == ' ') {
                words.add(word.toString())
                word = StringBuilder()
            } else {
                word.append(s[i])
            }
        }
        words.add(word.toString())
        return words.toTypedArray()
    }

    private fun reverse(s: String): String {
        val res = StringBuilder()
        for (element in s) res.insert(0, element)
        return res.toString()
    }
}

class ReverseWords3SB : ReverseWords3 {
    override operator fun invoke(s: String): String {
        val result = StringBuilder()
        val word = StringBuilder()
        for (i in s.indices) {
            if (s[i] != ' ') {
                word.append(s[i])
            } else {
                result.append(word.reverse())
                result.append(" ")
                word.setLength(0)
            }
        }
        result.append(word.reverse())
        return result.toString()
    }
}

class ReverseWords3TwoPointers : ReverseWords3 {
    override operator fun invoke(s: String): String {
        var i = 0
        var j: Int
        val n: Int = s.length
        val chs = s.toCharArray()
        while (i < n) {
            while (i < n && chs[i] == ' ') {
                i++
            }
            j = i
            while (j < n && chs[j] != ' ') {
                j++
            }
            reverse(chs, i, j - 1)
            i = j
        }
        return String(chs)
    }

    private fun reverse(chs: CharArray, s: Int, t: Int) {
        var s0 = s
        var t0 = t
        while (s0 < t0) {
            val tmp = chs[s0]
            chs[s0] = chs[t0]
            chs[t0] = tmp
            s0++
            t0--
        }
    }
}
