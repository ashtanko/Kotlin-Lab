/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

/**
 * 1716. Calculate Money in Leetcode Bank
 * @see <a href="https://leetcode.com/problems/calculate-money-in-leetcode-bank/">Source</a>
 */
object TotalMoney {

    private const val WEEK = 7
    private const val PERIOD = 3

    operator fun invoke(n: Int): Int {
        var m = n / WEEK
        var ans = 0
        for (i in 1..m) {
            ans += WEEK.times(i.plus(PERIOD))
        }
        for (i in WEEK.times(m) until n) {
            ans += ++m
        }
        return ans
    }
}
