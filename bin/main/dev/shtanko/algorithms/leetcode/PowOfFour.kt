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

import kotlin.math.ln

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * @see <a href="https://leetcode.com/problems/power-of-four/">Source</a>
 */
fun interface PowOfFour {
    fun isPow4(n: Int): Boolean
}

/**
 *  Approach 1: Brute Force + Precomputations
 *  Time complexity: O(1).
 *  Space complexity: O(1).
 */
class Pow4BruteForce : PowOfFour {

    private val n = 15
    private val nums: MutableList<Int> = ArrayList()

    init {
        var lastNum = 1
        nums.add(lastNum)
        for (i in 1 until n + 1) {
            lastNum *= 4
            nums.add(lastNum)
        }
    }

    override fun isPow4(n: Int): Boolean {
        return nums.contains(n)
    }
}

/**
 *  Approach 2: Math
 *  Time complexity: O(1).
 *  Space complexity: O(1).
 */
class Pow4Math : PowOfFour {
    override fun isPow4(n: Int): Boolean {
        return n > 0 && ln(n.toDouble()) / ln(2.0) % 2 == 0.0
    }
}

/**
 *  Approach 2: Math
 *  Time complexity: O(1).
 *  Space complexity: O(1).
 */
class Pow4BitManipulation : PowOfFour {
    override fun isPow4(n: Int): Boolean {
        return n > 0 && n and n - 1 == 0 && n and -0x55555556 == 0
    }
}
