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

import dev.shtanko.algorithms.DECIMAL
import kotlin.math.max

/**
 * 1742. Maximum Number of Balls in a Box
 * @see <a href="https://leetcode.com/problems/maximum-number-of-balls-in-a-box">Source</a>
 */
fun interface CountBalls {
    operator fun invoke(lowLimit: Int, highLimit: Int): Int

    companion object {
        const val ARR_SIZE = 46
    }
}

class CountBallsBruteforce : CountBalls {
    override operator fun invoke(lowLimit: Int, highLimit: Int): Int {
        val cnt = IntArray(CountBalls.ARR_SIZE)
        var max = 0
        for (i in lowLimit..highLimit) {
            var num = i
            var sum = 0
            while (num > 0) {
                sum += num % DECIMAL
                num /= DECIMAL
            }
            max = max(++cnt[sum], max)
        }
        return max
    }
}

class CountBalls2 : CountBalls {
    override operator fun invoke(lowLimit: Int, highLimit: Int): Int {
        val box = IntArray(CountBalls.ARR_SIZE)
        var lo = lowLimit
        var id = 0
        while (lo > 0) { // compute box id for lowLimit.
            id += lo % DECIMAL
            lo /= DECIMAL
        }
        ++box[id]
        for (i in lowLimit + 1..highLimit) { // compute all other box ids.
            var digits = i
            while (digits % DECIMAL == 0) { // for ball numbers with trailing 0's, decrease 9 for each 0.
                id -= 9
                digits /= DECIMAL
            }
            ++box[++id]
        }
        return box.max()
    }
}
