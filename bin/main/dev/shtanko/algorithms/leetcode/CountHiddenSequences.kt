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
import kotlin.math.min

/**
 * 2145. Count the Hidden Sequences
 * @see <a href="https://leetcode.com/problems/count-the-hidden-sequences/">Source</a>
 */
fun interface CountHiddenSequences {
    fun numberOfArrays(differences: IntArray, lower: Int, upper: Int): Int
}

/**
 * Straight Forward Solution
 */
class CountHiddenSequencesSF : CountHiddenSequences {
    override fun numberOfArrays(differences: IntArray, lower: Int, upper: Int): Int {
        var a: Long = 0
        var ma: Long = 0
        var mi: Long = 0
        for (d in differences) {
            a += d.toLong()
            ma = max(ma, a)
            mi = min(mi, a)
        }
        return max(0, upper - lower - (ma - mi) + 1).toInt()
    }
}
