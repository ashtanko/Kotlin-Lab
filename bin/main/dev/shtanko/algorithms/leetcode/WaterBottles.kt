/*
 * Copyright 2020 Oleksii Shtanko
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
 * 1518. Water Bottles
 * @see <a href="https://leetcode.com/problems/water-bottles/">Source</a>
 */
class WaterBottles {
    operator fun invoke(numBottles: Int, numExchange: Int): Int {
        if (numExchange == 0) return numBottles
        var nBottles = numBottles
        var ans = nBottles
        while (nBottles >= numExchange) {
            val remainder = nBottles % numExchange
            nBottles /= numExchange
            ans += nBottles
            nBottles += remainder
        }
        return ans
    }
}
