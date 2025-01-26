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

import kotlin.math.abs
import kotlin.math.min

/**
 * Global and Local Inversions
 * @see <a href="https://leetcode.com/problems/global-and-local-inversions/">Source</a>
 */
fun interface GlobalAndLocalInversions {
    fun isIdealPermutation(a: IntArray): Boolean
}

/**
 * Approach #2: Remember Minimum
 */
class IdealPermutationRememberMinimum : GlobalAndLocalInversions {
    override fun isIdealPermutation(a: IntArray): Boolean {
        val n: Int = a.size
        var floor = n
        for (i in n - 1 downTo 2) {
            floor = min(floor, a[i])
            if (a[i - 2] > floor) return false
        }
        return true
    }
}

/**
 * Approach #3: Linear Scan
 */
class IdealPermutationLinearScan : GlobalAndLocalInversions {
    override fun isIdealPermutation(a: IntArray): Boolean {
        for (i in a.indices) if (abs(a[i] - i) > 1) return false
        return true
    }
}
