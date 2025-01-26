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

import dev.shtanko.algorithms.MOD
import java.util.Arrays
import kotlin.math.abs

/**
 * 1575. Count All Possible Routes
 * @see <a href="https://leetcode.com/problems/count-all-possible-routes/">Source</a>
 */
fun interface CountAllPossibleRoutes {
    operator fun invoke(locations: IntArray, start: Int, finish: Int, fuel: Int): Int
}

/**
 * Approach 1: Recursive Dynamic Programming
 */
class CountAllPossibleRoutesRec : CountAllPossibleRoutes {
    override fun invoke(locations: IntArray, start: Int, finish: Int, fuel: Int): Int {
        val n: Int = locations.size
        val memo = Array(n) { IntArray(fuel + 1) { -1 } }
        return solve(locations, start, finish, fuel, memo)
    }

    private fun solve(locations: IntArray, currCity: Int, finish: Int, remainingFuel: Int, memo: Array<IntArray>): Int {
        if (remainingFuel < 0) {
            return 0
        }
        if (memo[currCity][remainingFuel] != -1) {
            return memo[currCity][remainingFuel]
        }
        var ans = if (currCity == finish) 1 else 0
        for (nextCity in locations.indices) {
            if (nextCity != currCity) {
                ans = ans.plus(
                    solve(
                        locations,
                        nextCity,
                        finish,
                        remainingFuel - abs(locations[currCity] - locations[nextCity]),
                        memo,
                    ),
                ) % MOD
            }
        }
        return ans.also { memo[currCity][remainingFuel] = it }
    }
}

/**
 * Approach 2: Iterative Dynamic Programming
 */
class CountAllPossibleRoutesIter : CountAllPossibleRoutes {
    override fun invoke(locations: IntArray, start: Int, finish: Int, fuel: Int): Int {
        val n: Int = locations.size
        val dp = Array(n) { IntArray(fuel + 1) }
        Arrays.fill(dp[finish], 1)

        for (j in 0..fuel) {
            for (i in 0 until n) {
                for (k in 0 until n) {
                    if (k == i) {
                        continue
                    }
                    if (abs(locations[i] - locations[k]) <= j) {
                        dp[i][j] = (dp[i][j] + dp[k][j - abs(locations[i] - locations[k])]) % MOD
                    }
                }
            }
        }

        return dp[start][fuel]
    }
}
