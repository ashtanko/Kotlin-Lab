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

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 1962. Remove Stones to Minimize the Total
 * @see <a href="https://leetcode.com/problems/remove-stones-to-minimize-the-total/">Source</a>
 */
fun interface MinStoneSum {
    operator fun invoke(piles: IntArray, k: Int): Int
}

class MinStoneSumHeap : MinStoneSum {
    override operator fun invoke(piles: IntArray, k: Int): Int {
        val pq: PriorityQueue<Int> = PriorityQueue { a, b -> b - a }
        var res = 0
        piles.forEach {
            pq.add(it)
            res += it
        }
        repeat(k) {
            val a: Int = pq.poll()
            pq.add(a - a / 2)
            res -= a / 2
        }
        return res
    }
}

class MinStoneSumFast : MinStoneSum {
    override operator fun invoke(piles: IntArray, k: Int): Int {
        val frequency = IntArray(FREQ)
        var max = -1
        for (p in piles) {
            frequency[p]++
            max = max(max, p)
        }
        var i = max
        var k0 = k
        while (i >= 1 && k0 > 0) {
            while (frequency[i] != 0 && k0 > 0) {
                frequency[i]--
                frequency[(i + 1) / 2]++
                k0--
            }
            i--
        }

        var ans = 0
        for (j in max downTo 1) {
            ans += frequency[j] * j
        }
        return ans
    }

    private companion object {
        private const val FREQ = 10001
    }
}
