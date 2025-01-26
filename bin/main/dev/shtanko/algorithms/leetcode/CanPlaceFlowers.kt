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

import dev.shtanko.algorithms.annotations.Greedy

/**
 * 605. Can Place Flowers
 * @see <a href="https://leetcode.com/problems/can-place-flowers/">Source</a>
 */
fun interface CanPlaceFlowers {
    operator fun invoke(flowerbed: IntArray, n: Int): Boolean
}

@Greedy
class CanPlaceFlowersGreedy : CanPlaceFlowers {
    override operator fun invoke(flowerbed: IntArray, n: Int): Boolean {
        var count = 0
        var i = 0
        while (i < flowerbed.size && count < n) {
            if (flowerbed[i] == 0) {
                // get next and prev flower bed slot values. If i lies at the ends the next and prev are
                // considered as 0.
                val next = if (i == flowerbed.size - 1) 0 else flowerbed[i + 1]
                val prev = if (i == 0) 0 else flowerbed[i - 1]
                if (next == 0 && prev == 0) {
                    flowerbed[i] = 1
                    count++
                }
            }
            i++
        }
        return count == n
    }
}
