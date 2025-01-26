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

import java.util.Stack

/**
 * @see <a href="https://leetcode.com/problems/find-permutation/">Source</a>
 */
fun interface FindPermutation {
    operator fun invoke(s: String): IntArray
}

/**
 * Approach #1 Using Stack
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
class FindPermutationStack : FindPermutation {
    override operator fun invoke(s: String): IntArray {
        val res = IntArray(s.length + 1)
        val stack: Stack<Int> = Stack()
        var j = 0
        for (i in 1..s.length) {
            if (s[i - 1] == 'I') {
                stack.push(i)
                while (stack.isNotEmpty()) {
                    res[j++] = stack.pop()
                }
            } else {
                stack.push(i)
            }
        }
        stack.push(s.length + 1)
        while (stack.isNotEmpty()) {
            res[j++] = stack.pop()
        }
        return res
    }
}

/**
 * Approach #2 Reversing the subarray.
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class FindPermutationReversing : FindPermutation {
    override operator fun invoke(s: String): IntArray {
        val res = IntArray(s.length + 1)
        for (i in res.indices) res[i] = i + 1
        var i = 1
        while (i <= s.length) {
            val j = i
            while (i <= s.length && s[i - 1] == 'D') i++
            res.reverse(j - 1, i)
            i++
        }
        return res
    }
}

/**
 * Approach #3 Two pointers.
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class FindPermutationTwoPointers : FindPermutation {
    override operator fun invoke(s: String): IntArray {
        val res = IntArray(s.length + 1)
        res[0] = 1
        var i = 1
        while (i <= s.length) {
            res[i] = i + 1
            val j = i
            if (s[i - 1] == 'D') {
                while (i <= s.length && s[i - 1] == 'D') i++
                var k = j - 1
                var c = i
                while (k <= i - 1) {
                    res[k] = c
                    k++
                    c--
                }
            } else {
                i++
            }
        }
        return res
    }
}
