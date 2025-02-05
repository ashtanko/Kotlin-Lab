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
 * 1561. Maximum Number of Coins You Can Get
 * @see <a href="https://leetcode.com/problems/maximum-number-of-coins-you-can-get">Source</a>
 */
fun interface MaxCoinsYouCanGet {
    operator fun invoke(piles: IntArray): Int
}

class MaxCoinsYouCanGetGreedy : MaxCoinsYouCanGet {
    override fun invoke(piles: IntArray): Int {
        piles.sort()
        val queue: ArrayDeque<Int> = ArrayDeque()
        for (num in piles) {
            queue.addLast(num)
        }

        var ans = 0
        while (queue.isNotEmpty()) {
            queue.removeLast() // alice
            ans += queue.removeLast() // us
            queue.removeFirst() // bob
        }

        return ans
    }
}

class MaxCoinsYouCanGetNoQueue : MaxCoinsYouCanGet {
    override fun invoke(piles: IntArray): Int {
        piles.sort()
        var ans = 0
        var i: Int = piles.size / 3
        while (i < piles.size) {
            ans += piles[i]
            i += 2
        }

        return ans
    }
}
