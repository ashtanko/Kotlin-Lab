/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.codingbat.recursion1

/**
 * Recursion-1 > triangle
 * @see <a href="https://codingbat.com/prob/p194781">Source</a>
 */
internal fun interface Triangle {
    operator fun invoke(rows: Int): Int
}

class TriangleIterative : Triangle {
    override fun invoke(rows: Int): Int {
        if (rows == 0) {
            return 0
        }
        var res = 0
        repeat(rows) {
            res += it + 1
        }
        return res
    }
}

class TriangleRecursive : Triangle {
    override fun invoke(rows: Int): Int {
        if (rows == 0) {
            return 0
        }
        return rows + invoke(rows - 1)
    }
}

class TriangleBottomUp : Triangle {
    override fun invoke(rows: Int): Int {
        if (rows == 0) {
            return 0
        }
        return mem(rows)
    }

    private fun mem(rows: Int): Int {
        val memo = IntArray(rows + 1)
        for (i in 1..rows) {
            memo[i] = i + memo[i - 1]
        }
        return memo[rows]
    }
}
