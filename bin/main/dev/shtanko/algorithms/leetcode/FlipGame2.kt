/*
 * Copyright 2021 Oleksii Shtanko
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
 * 294. Flip Game II
 * @see <a href="https://leetcode.com/problems/flip-game-ii/">Source</a>
 */
fun interface FlipGame2 {
    operator fun invoke(currentState: String): Boolean
}

class FG2BruteForce : FlipGame2 {
    override fun invoke(currentState: String): Boolean {
        for (i in 0 until currentState.length - 1) {
            if (currentState[i] == '+' && currentState[i + 1] == '+' && !invoke(
                    currentState.substring(0, i) + "--" + currentState.substring(i + 2),
                )
            ) {
                return true
            }
        }
        return false
    }
}

/**
 * Backtracking solution with time optimization through DP
 */
class FG2Backtracking : FlipGame2 {
    override fun invoke(currentState: String): Boolean {
        if (currentState.length < 2) {
            return false
        }
        val winMap = HashMap<String, Boolean>()
        return helper(currentState, winMap)
    }

    private fun helper(str: String, winMap: HashMap<String, Boolean>): Boolean {
        if (winMap.containsKey(str)) {
            return winMap[str]!!
        }
        for (i in 0 until str.length - 1) {
            if (str.startsWith("++", i)) {
                val t = str.substring(0, i) + "--" + str.substring(i + 2)
                if (!helper(t, winMap)) {
                    winMap[str] = true
                    return true
                }
            }
        }
        winMap[str] = false
        return false
    }
}
