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

import java.util.Stack

// Remove All Adjacent Duplicates In String
fun interface RemoveAllAdjacentDuplicatesStrategy2 {
    operator fun invoke(s: String, k: Int): String
}

/**
 * Approach 1: Brute Force
 */
class RemoveDuplicates2BruteForce : RemoveAllAdjacentDuplicatesStrategy2 {
    override operator fun invoke(s: String, k: Int): String {
        val sb = StringBuilder(s)
        var length = -1
        while (length != sb.length) {
            length = sb.length
            var i = 0
            var count = 1
            while (i < sb.length) {
                if (i == 0 || sb[i] != sb[i - 1]) {
                    count = 1
                } else if (++count == k) {
                    sb.delete(i - k + 1, i + 1)
                    break
                }
                ++i
            }
        }
        return sb.toString()
    }
}

/**
 * Approach 2: Memoise Count
 */
class RemoveDuplicates2Memoise : RemoveAllAdjacentDuplicatesStrategy2 {
    override operator fun invoke(s: String, k: Int): String {
        val sb = StringBuilder(s)
        val count = IntArray(sb.length)
        var i = 0
        while (i < sb.length) {
            if (i == 0 || sb[i] != sb[i - 1]) {
                count[i] = 1
            } else {
                count[i] = count[i - 1] + 1
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1)
                    i -= k
                }
            }
            ++i
        }
        return sb.toString()
    }
}

/**
 * Approach 3: Stack
 */
class RemoveDuplicates2Stack : RemoveAllAdjacentDuplicatesStrategy2 {
    override operator fun invoke(s: String, k: Int): String {
        val sb = StringBuilder(s)
        val counts: Stack<Int> = Stack()
        var i = 0
        while (i < sb.length) {
            if (i == 0 || sb[i] != sb[i - 1]) {
                counts.push(1)
            } else {
                val incremented: Int = counts.pop() + 1
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1)
                    i -= k
                } else {
                    counts.push(incremented)
                }
            }
            ++i
        }
        return sb.toString()
    }
}

/**
 * Approach 4: Stack with Reconstruction
 */
class RemoveDuplicates2StackReconstruction : RemoveAllAdjacentDuplicatesStrategy2 {
    override operator fun invoke(s: String, k: Int): String {
        val counts = Stack<Pair>()
        for (i in s.indices) {
            if (counts.empty() || s[i] != counts.peek().ch) {
                counts.push(Pair(1, s[i]))
            } else {
                if (++counts.peek().cnt == k) {
                    counts.pop()
                }
            }
        }
        val b = StringBuilder()
        while (!counts.empty()) {
            val p = counts.pop()
            for (i in 0 until p.cnt) {
                b.append(p.ch)
            }
        }
        return b.reverse().toString()
    }

    class Pair(var cnt: Int, var ch: Char)
}

/**
 * Approach 5: Two Pointers
 */
class RemoveDuplicates2TwoPointers : RemoveAllAdjacentDuplicatesStrategy2 {
    override operator fun invoke(s: String, k: Int): String {
        val counts = Stack<Int>()
        val sa: CharArray = s.toCharArray()
        var j = 0
        var i = 0
        while (i < s.length) {
            sa[j] = sa[i]
            if (j == 0 || sa[j] != sa[j - 1]) {
                counts.push(1)
            } else {
                val incremented = counts.pop() + 1
                if (incremented == k) {
                    j -= k
                } else {
                    counts.push(incremented)
                }
            }
            ++i
            ++j
        }
        return String(sa, 0, j)
    }
}
