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

import kotlin.math.min

/**
 * 174. Dungeon Game
 * @see <a href="https://leetcode.com/problems/dungeon-game">Source</a>
 */
fun interface DungeonGame {
    fun calculateMinimumHP(dungeon: Array<IntArray>): Int
}

class DungeonGameDP : DungeonGame {
    override fun calculateMinimumHP(dungeon: Array<IntArray>): Int {
        val m: Int = dungeon.size
        val n: Int = dungeon[0].size
        val dp = IntArray(n + 1)
        dp[n] = 1
        for (i in m - 1 downTo 0) {
            for (j in n - 1 downTo 0) {
                val health: Int = if (i == m - 1) {
                    dp[j + 1] - dungeon[i][j]
                } else if (j == n - 1) {
                    dp[j] - dungeon[i][j]
                } else {
                    min(dp[j + 1], dp[j]) - dungeon[i][j]
                }
                dp[j] = if (health <= 0) {
                    1
                } else {
                    health
                }
            }
        }
        return dp[0]
    }
}
