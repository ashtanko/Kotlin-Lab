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

import kotlin.math.max

/**
 * 318. Maximum Product of Word Lengths
 * @see <a href="https://leetcode.com/problems/maximum-product-of-word-lengths/">Source</a>
 */
fun interface MaximumProductOfWordLengths {
    fun maxProduct(words: Array<String>): Int
}

fun Char.bitNumber(): Int {
    return this.code - 'a'.code
}

/**
 * Approach 1: Optimize noCommonLetters function : Bitmasks + Precomputation
 */
class MaxProductBitmasks : MaximumProductOfWordLengths {

    override fun maxProduct(words: Array<String>): Int {
        val n: Int = words.size
        val masks = IntArray(n)
        val lens = IntArray(n)

        var bitmask: Int
        for (i in 0 until n) {
            bitmask = 0
            for (ch in words[i].toCharArray()) {
                // add a bit number bit_number in bitmask
                bitmask = bitmask or (1 shl ch.bitNumber())
            }
            masks[i] = bitmask
            lens[i] = words[i].length
        }

        var maxVal = 0
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (masks[i] and masks[j] == 0) {
                    maxVal = max(maxVal, lens[i] * lens[j])
                }
            }
        }

        return maxVal
    }
}

/**
 * Approach 2: Optimise Number of Comparisons : Bitmasks + Pre-computation + Hashmap
 */
class MaxProductHashmap : MaximumProductOfWordLengths {
    override fun maxProduct(words: Array<String>): Int {
        val hashmap: MutableMap<Int, Int> = HashMap()

        var bitmask: Int
        for (word in words) {
            bitmask = 0
            for (ch in word.toCharArray()) {
                // add bit number bitNumber in bitmask
                bitmask = bitmask or (1 shl ch.bitNumber())
            }
            // there could be different words with the same bitmask
            // ex. ab and aabb
            hashmap[bitmask] = max(hashmap.getOrDefault(bitmask, 0), word.length)
        }

        var maxProd = 0
        for (x in hashmap.keys) {
            for (y in hashmap.keys) {
                if (x and y == 0) {
                    maxProd = max(
                        maxProd,
                        hashmap.getOrDefault(x, 0) * hashmap.getOrDefault(y, 0),
                    )
                }
            }
        }

        return maxProd
    }
}
