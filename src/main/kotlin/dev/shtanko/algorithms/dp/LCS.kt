/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.dp

/**
 * The longest common subsequence.
 */
interface LCS {
    fun perform(x: String, y: String, m: Int, n: Int): Int
}

class LCSRecursive : LCS {
    override fun perform(x: String, y: String, m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0
        val xArr = x.toCharArray()
        val yArr = y.toCharArray()
        return if (xArr[m - 1] == yArr[n - 1]) {
            1 + perform(x, y, m - 1, n - 1)
        } else {
            maxOf(perform(x, y, m, n - 1), perform(x, y, m - 1, n))
        }
    }
}
