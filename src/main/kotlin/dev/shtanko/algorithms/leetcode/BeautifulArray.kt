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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 932. Beautiful Array
 * @see <a href="https://leetcode.com/problems/beautiful-array">Source</a>
 */
@Medium("https://leetcode.com/problems/beautiful-array")
class BeautifulArray {
    private val memo: MutableMap<Int, IntArray> by lazy {
        HashMap()
    }

    operator fun invoke(n: Int): IntArray {
        var res = ArrayList<Int>()
        res.add(1)
        while (res.size < n) {
            val tmp = ArrayList<Int>()
            for (i in res) if (i * 2 - 1 <= n) tmp.add(i * 2 - 1)
            for (i in res) if (i * 2 <= n) tmp.add(i * 2)
            res = tmp
        }
        return res.stream().mapToInt { i: Int -> i }.toArray()
    }

    fun divideAndConquer(n: Int): IntArray {
        if (memo.containsKey(n)) return memo[n] ?: intArrayOf()
        val ans = IntArray(n)
        if (n == 1) {
            ans[0] = 1
        } else {
            var t = 0
            // odds
            val odds = n.plus(1).div(2)
            for (x in divideAndConquer(odds)) {
                ans[t++] = 2.times(x).minus(1)
            }

            // evens
            val evens = n.div(2)
            for (x in divideAndConquer(evens)) {
                ans[t++] = 2.times(x)
            }
        }
        memo[n] = ans
        return ans
    }
}
