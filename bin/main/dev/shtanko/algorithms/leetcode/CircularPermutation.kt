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

import dev.shtanko.algorithms.annotations.Bitwise

/**
 * 1238. Circular Permutation in Binary Representation
 * @see <a href="https://leetcode.com/problems/circular-permutation-in-binary-representation/">Source</a>
 */
fun interface CircularPermutation {
    operator fun invoke(n: Int, start: Int): List<Int>
}

@Bitwise
class CircularPermutationGrayCode : CircularPermutation {
    override operator fun invoke(n: Int, start: Int): List<Int> {
        val res: MutableList<Int> = ArrayList()
        for (i in 0 until (1 shl n)) {
            res.add(start xor i xor (i shr 1))
        }
        return res
    }
}

class OneBitDiffPermutation : CircularPermutation {
    override operator fun invoke(n: Int, start: Int): List<Int> {
        val res: MutableList<Int> = ArrayList()
        val tmp = genOneBitDiffPermutation(n) // generate one bit diff permutations

        // rotate the tmp list to make it starts from "start"
        // before: {0, 1, ..., start, ..., end}
        // after:  {start, ..., end, 0, 1, ...}
        var i = 0
        while (i < tmp.size) {
            if (tmp[i] == start) {
                break
            }
            i++
        }

        for (k in i until tmp.size) {
            res.add(tmp[k])
        }

        for (q in 0 until i) {
            res.add(tmp[q])
        }

        return res
    }

    /**
     *  generate one bit diff permutations
     * 0, 1
     * 0, 1, 11, 10
     * 000, 001, 011, 010, 110, 111, 101, 100
     */
    private fun genOneBitDiffPermutation(n: Int): List<Int> {
        val tmp: MutableList<Int> = ArrayList()
        tmp.add(0)
        if (n == 0) {
            return tmp
        }
        for (i in 0 until n) {
            for (j in tmp.indices.reversed()) {
                tmp.add(tmp[j] + (1 shl i))
            }
        }
        return tmp
    }
}
