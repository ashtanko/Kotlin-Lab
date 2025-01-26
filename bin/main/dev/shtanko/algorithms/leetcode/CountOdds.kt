/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.extensions.isEven

/**
 * 1523. Count Odd Numbers in an Interval Range
 * @see <a href="https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/">Source</a>
 */
object CountOdds {
    operator fun invoke(low: Int, high: Int): Int {
        var count = high.minus(low).div(2)
        if (low.isEven.not() || high.isEven.not()) {
            count++
        }
        return count
    }
}
