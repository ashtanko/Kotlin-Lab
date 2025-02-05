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

/**
 * 2147. Number of Ways to Divide a Long Corridor
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/">Source</a>
 */
fun interface NumberOfWays {
    operator fun invoke(corridor: String): Int
}

class NumberOfWaysDP : NumberOfWays {
    override operator fun invoke(corridor: String): Int {
        var res: Long = 1
        var j: Long = 0
        var k: Long = 0
        val mod = MOD + 7
        for (i in corridor.indices) {
            if (corridor[i] == 'S') {
                if (++k > 2 && k % 2 == 1L) res = res * (i - j) % mod
                j = i.toLong()
            }
        }
        return if (k % 2 == 0L && k > 0) res.toInt() else 0
    }

    companion object {
        private const val MOD = 1e9.toLong()
    }
}
