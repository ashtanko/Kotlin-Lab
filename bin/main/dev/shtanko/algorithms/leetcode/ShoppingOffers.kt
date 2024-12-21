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
 * 638. Shopping Offers
 * @see <a href="https://leetcode.com/problems/shopping-offers/">Source</a>
 */
fun interface ShoppingOffers {
    operator fun invoke(price: List<Int>, special: List<List<Int>>, needs: List<Int>): Int
}

class ShoppingOffersRecursive : ShoppingOffers {
    override operator fun invoke(price: List<Int>, special: List<List<Int>>, needs: List<Int>): Int {
        return backTrace(price, special, needs, 0)
    }

    private fun backTrace(price: List<Int>, special: List<List<Int>>, needs: List<Int>, start: Int): Int {
        var res = directPurchase(price, needs)
        for (i in start until special.size) {
            val offer = special[i]
            if (isValid(offer, needs)) { // offer is a valid one
                val tempNeeds: MutableList<Int> = ArrayList()
                for (j in needs.indices) { // using this valid offer
                    tempNeeds.add(needs[j] - offer[j])
                }
                res = min(res, offer[offer.size - 1] + backTrace(price, special, tempNeeds, start))
            }
        }
        return res
    }

    private fun isValid(offer: List<Int>, needs: List<Int>): Boolean {
        for (i in needs.indices) {
            if (needs[i] < offer[i]) return false
        }
        return true
    }

    private fun directPurchase(price: List<Int>, needs: List<Int>): Int {
        var total = 0
        for (i in needs.indices) {
            total += price[i] * needs[i]
        }
        return total
    }
}
