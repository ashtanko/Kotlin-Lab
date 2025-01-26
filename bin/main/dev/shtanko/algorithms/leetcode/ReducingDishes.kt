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
 * 1402. Reducing Dishes
 * @see <a href="https://leetcode.com/problems/reducing-dishes/">Source</a>
 */
fun interface ReducingDishes {
    fun maxSatisfaction(satisfaction: IntArray): Int
}

class ReducingDishesSimple : ReducingDishes {
    override fun maxSatisfaction(satisfaction: IntArray): Int {
        satisfaction.sort()
        var res = 0
        var total = 0
        val n: Int = satisfaction.size
        var i = n - 1
        while (i >= 0 && satisfaction[i] > -total) {
            total += satisfaction[i]
            res += total
            --i
        }
        return res
    }
}
