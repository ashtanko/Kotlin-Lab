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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.Recursive
import java.util.LinkedList

/**
 * 1823. Find the Winner of the Circular Game
 * @see <a href="https://leetcode.com/problems/find-the-winner-of-the-circular-game">Source</a>
 */
fun interface FindTheWinner {
    operator fun invoke(n: Int, k: Int): Int
}

class FindTheWinnerSimulation : FindTheWinner {
    override fun invoke(n: Int, k: Int): Int {
        // Initialize list of N friends, labeled from 1-N
        val circle: MutableList<Int> = LinkedList<Int>()
        for (i in 1..n) {
            circle.add(i)
        }
        var startIndex = 0
        while (circle.size > 1) {
            // Calculate the index of the friend to be removed
            val removalIndex = (startIndex + k - 1) % circle.size

            // Erase the friend at removalIndex
            circle.removeAt(removalIndex)

            // Update startIndex for the next round
            startIndex = removalIndex
        }

        return circle.first()
    }
}

@Recursive
class FindTheWinnerRecursion : FindTheWinner {
    override fun invoke(n: Int, k: Int): Int {
        return winnerHelper(n, k) + 1
    }

    private fun winnerHelper(n: Int, k: Int): Int {
        if (n == 1) {
            return 0
        }
        return (winnerHelper(n - 1, k) + k) % n
    }
}

@Iterative
class FindTheWinnerIterative : FindTheWinner {
    override fun invoke(n: Int, k: Int): Int {
        var ans = 0
        for (i in 2..n) {
            ans = (ans + k) % i
        }

        return ans + 1
    }
}
