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
 * 875. Koko Eating Bananas
 * @see <a href="https://leetcode.com/problems/koko-eating-bananas/">Source</a>
 */
fun interface KokoEatingBananas {
    fun minEatingSpeed(piles: IntArray, h: Int): Int
}

class KokoEatingBananasBS : KokoEatingBananas {
    override fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var l = 1
        var r = LIMIT
        while (l < r) {
            val m = (l + r) / 2
            var total = 0
            for (p in piles) {
                total += (p + m - 1) / m
            }
            if (total > h) {
                l = m + 1
            } else {
                r = m
            }
        }
        return l
    }

    companion object {
        private const val LIMIT = 1000000000
    }
}
