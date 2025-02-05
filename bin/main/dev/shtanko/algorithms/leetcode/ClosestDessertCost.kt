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

import dev.shtanko.algorithms.annotations.Backtracking
import kotlin.math.abs

/**
 * 1774. Closest Dessert Cost
 * @see <a href="https://leetcode.com/problems/closest-dessert-cost/">Source</a>
 */
fun interface ClosestDessertCost {
    operator fun invoke(baseCosts: IntArray, toppingCosts: IntArray, target: Int): Int
}

@Backtracking
class ClosestDessertCostBacktracking : ClosestDessertCost {
    private var result = 0

    override operator fun invoke(baseCosts: IntArray, toppingCosts: IntArray, target: Int): Int {
        if (baseCosts.isEmpty()) return 0
        result = baseCosts[0]
        for (base in baseCosts) closestCost(base, toppingCosts, 0, target)
        return result
    }

    private fun closestCost(current: Int, toppingCosts: IntArray, index: Int, target: Int) {
        val local = abs(target - current) < abs(target - result)
        if (local || abs(target - current) == abs(target - result) && current < result) {
            result = current
        }
        if (index == toppingCosts.size) {
            return
        }
        closestCost(current, toppingCosts, index + 1, target)
        // when current < target, one toppingCosts is acceptable
        if (current < target) {
            closestCost(current + toppingCosts[index], toppingCosts, index + 1, target)
        }
        // when current  + toppingCosts < target, another toppingCosts is acceptable
        if (current + toppingCosts[index] < target) {
            closestCost(current + toppingCosts[index] * 2, toppingCosts, index + 1, target)
        }
    }
}
