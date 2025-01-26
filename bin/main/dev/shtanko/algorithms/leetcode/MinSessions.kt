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
 * 1986. Minimum Number of Work Sessions to Finish the Tasks
 * @see <a href="https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/">Source</a>
 */
fun interface MinSessions {
    operator fun invoke(tasks: IntArray, sessionTime: Int): Int
}

/**
 * Solution 1: Straightforward Bitmask DP
 */
class MinSessionsSfBitmaskDP : MinSessions {

    var n = 0
    var sessionTime: Int = 0
    var memo = Array(1 shl 14) {
        arrayOfNulls<Int>(
            15,
        )
    }

    override operator fun invoke(tasks: IntArray, sessionTime: Int): Int {
        n = tasks.size
        this.sessionTime = sessionTime
        return dp(tasks, (1 shl n) - 1, 0)
    }

    fun dp(tasks: IntArray, mask: Int, remainTime: Int): Int {
        if (mask == 0) return 0
        if (memo[mask][remainTime] != null) {
            return memo[mask][remainTime] ?: 0
        }
        var ans = n // There is up to N work sessions
        for (i in 0 until n) {
            if (mask shr i and 1 == 1) {
                val newMask = (1 shl i).inv() and mask // clear i th bit
                ans = if (tasks[i] <= remainTime) {
                    min(ans, dp(tasks, newMask, remainTime - tasks[i])) // Consume current session
                } else {
                    min(ans, dp(tasks, newMask, sessionTime - tasks[i]) + 1) // Create new session
                }
            }
        }
        return ans.also { memo[mask][remainTime] = it }
    }
}

/**
 * Solution 2: Bitmask DP - Optimized (Independence with SessionTime)
 */
class MinSessionsBitmask : MinSessions {

    var n = 0
    var sessionTime: Int = 0
    var memo: Array<Pair<Int, Int>?> = arrayOfNulls(1 shl 14)

    override operator fun invoke(tasks: IntArray, sessionTime: Int): Int {
        n = tasks.size
        this.sessionTime = sessionTime
        return dp(tasks, (1 shl n) - 1).first
    }

    fun dp(tasks: IntArray, mask: Int): Pair<Int, Int> {
        if (mask == 0) return Pair(0, 0)
        if (memo[mask] != null) return memo[mask]!!
        var ans = n
        var ansRemainTime = 0
        for (i in 0 until n) {
            if (mask shr i and 1 == 1) {
                val newMask = (1 shl i).inv() and mask // clear i th bit
                val res = dp(tasks, newMask)
                var (cntSession, remainTime) = res
                if (tasks[i] <= remainTime) { // Consume current session
                    remainTime -= tasks[i]
                } else { // Create new session
                    remainTime = sessionTime - tasks[i]
                    cntSession += 1
                }
                if (cntSession < ans || cntSession == ans && remainTime > ansRemainTime) {
                    ans = cntSession
                    ansRemainTime = remainTime
                }
            }
        }
        return (ans to ansRemainTime).also { memo[mask] = it }
    }
}
