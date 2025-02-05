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

import kotlin.math.max

/**
 * 904. Fruit Into Baskets
 * @see <a href="https://leetcode.com/problems/fruit-into-baskets/">Source</a>
 */
fun interface FruitIntoBaskets {
    fun totalFruit(fruits: IntArray): Int
}

class FruitIntoBasketsSlidingWindow : FruitIntoBaskets {
    override fun totalFruit(fruits: IntArray): Int {
        val count: MutableMap<Int, Int> = HashMap()
        var i = 0
        var j = 0
        while (j < fruits.size) {
            count[fruits[j]] = (count[fruits[j]] ?: 0) + 1
            if (count.size > 2) {
                count[fruits[i]] = count.getOrDefault(fruits[i], 0) - 1
                count.remove(fruits[i++], 0)
            }
            ++j
        }
        return j - i
    }
}

class FruitIntoBasketsSlidingWindow2 : FruitIntoBaskets {
    override fun totalFruit(fruits: IntArray): Int {
        val count: MutableMap<Int, Int> = HashMap()
        var res = 0
        var i = 0
        for (j in fruits.indices) {
            count[fruits[j]] = (count[fruits[j]] ?: 0) + 1
            while (count.size > 2) {
                count[fruits[i]] = count.getOrDefault(fruits[i], 0) - 1
                if (count[fruits[i]] == 0) count.remove(fruits[i])
                i++
            }
            res = max(res, j - i + 1)
        }
        return res
    }
}
