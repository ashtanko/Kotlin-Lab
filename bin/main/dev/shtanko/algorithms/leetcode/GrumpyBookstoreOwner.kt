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

/**
 * 1052. Grumpy Bookstore Owner
 * @see <a href="https://leetcode.com/problems/grumpy-bookstore-owner/">Grumpy Bookstore Owner</a>
 */
fun interface GrumpyBookstoreOwner {
    operator fun invoke(customers: IntArray, grumpy: IntArray, minutes: Int): Int
}

class GrumpyBookstoreOwnerSlidingWindow : GrumpyBookstoreOwner {
    override fun invoke(customers: IntArray, grumpy: IntArray, minutes: Int): Int {
        val initialUnrealized = customers.zip(grumpy).take(minutes).sumOf { it.first * it.second }
        var maxUnrealized = initialUnrealized
        var currentUnrealized = initialUnrealized

        for (i in minutes until customers.size) {
            currentUnrealized += customers[i] * grumpy[i] - customers[i - minutes] * grumpy[i - minutes]
            maxUnrealized = maxOf(maxUnrealized, currentUnrealized)
        }

        val satisfiedCustomers = customers.indices.sumOf { customers[it] * (1 - grumpy[it]) }

        return maxUnrealized + satisfiedCustomers
    }
}
