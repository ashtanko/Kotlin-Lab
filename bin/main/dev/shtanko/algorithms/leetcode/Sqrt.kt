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

fun interface SqrtStrategy {
    operator fun invoke(x: Int): Int
}

class SqrtBruteForce : SqrtStrategy {
    override fun invoke(x: Int): Int {
        if (x == 0) return 0
        var i = 1
        while (i <= x / i) {
            if (i <= x / i && i + 1 > x / i.plus(1)) {
                return i
            }
            i++
        }
        return -1
    }
}

class SqrtBS : SqrtStrategy {
    override fun invoke(x: Int): Int {
        if (x == 0) return 0
        var start = 1
        var end = x
        while (start < end) {
            val mid = start + end.minus(start) / 2
            if (mid <= x / mid && mid + 1 > x / mid.plus(1)) {
                // Found the result
                return mid
            } else if (mid > x / mid) {
                // Keep checking the left part
                end = mid
            } else {
                // Keep checking the right part
                start = mid + 1
            }
        }
        return start
    }
}

class SqrtNewton : SqrtStrategy {
    override fun invoke(x: Int): Int {
        if (x == 0) return 0
        var i = x.toLong()
        while (i > x / i) {
            i = i.plus(x.div(i)) / 2
        }
        return i.toInt()
    }
}
