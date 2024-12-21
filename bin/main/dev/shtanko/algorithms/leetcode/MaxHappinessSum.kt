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

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 3075. Maximize Happiness of Selected Children
 * @see <a href="https://leetcode.com/problems/maximize-happiness-of-selected-children/description/">Source</a>
 */
fun interface MaxHappinessSum {
    operator fun invoke(happiness: IntArray, turns: Int): Long
}

class MaxHappinessSumSort : MaxHappinessSum {
    override fun invoke(happiness: IntArray, turns: Int): Long {
        happiness.sort()
        var res: Long = 0
        val n: Int = happiness.size
        var j = 0

        for (i in n - 1 downTo n - turns) {
            happiness[i] = max(happiness[i] - j++, 0)
            res += happiness[i]
        }

        return res
    }
}

class MaxHappinessSumMaxHeap : MaxHappinessSum {
    override fun invoke(happiness: IntArray, turns: Int): Long {
        val maxHeap: PriorityQueue<Int> = PriorityQueue(Comparator.reverseOrder())

        // Add all elements to the priority queue
        for (h in happiness) {
            maxHeap.add(h)
        }

        var totalHappinessSum: Long = 0

        for ((currentTurn, _) in (0 until turns).withIndex()) {
            totalHappinessSum += max(maxHeap.poll() - currentTurn, 0)
        }

        return totalHappinessSum
    }
}
