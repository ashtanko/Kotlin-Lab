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

/**
 * 837. New 21 Game
 * @see <a href="https://leetcode.com/problems/new-21-game/">Source</a>
 */
fun interface New21Game {
    operator fun invoke(n: Int, k: Int, maxPts: Int): Double
}

class New21GameDP : New21Game {
    override operator fun invoke(n: Int, k: Int, maxPts: Int): Double {
        val p1 = 1.0 / maxPts.toDouble()
        val cache = Array(n + 1) { -1.0 }

        // f(x) = (f(x+1) + f(x+2)+..+f(x + maxPts))*p1
        // f(x + 1) = (f(x+2) + f(x+3) +...+f(x + maxPts)*p1 + f(x + 1 + maxPts))*p1
        // f(x) - f(x + 1) = f(x+1)*p1 - f(x+1+maxPts)*p1
        // f(x) = f(x+1) + (f(x+1) - f(x+1+maxPts))*p1
        fun dfs(currSum: Int): Double {
            if (currSum == k - 1) return minOf(1.0, (n - k + 1) * p1) // corner case
            if (currSum >= k) return if (currSum <= n) 1.0 else 0.0
            if (cache[currSum] != -1.0) return cache[currSum]
            val res = dfs(currSum + 1) + (dfs(currSum + 1) - dfs(currSum + 1 + maxPts)) * p1
            cache[currSum] = res
            return res
        }
        return dfs(0)
    }
}
