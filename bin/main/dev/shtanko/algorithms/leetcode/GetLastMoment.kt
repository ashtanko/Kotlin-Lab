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
 * 1503. Last Moment Before All Ants Fall Out of a Plank
 * @see <a href="https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank">Source</a>
 */
fun interface GetLastMoment {
    operator fun invoke(n: Int, left: IntArray, right: IntArray): Int
}

class GetLastMomentSolution : GetLastMoment {
    override fun invoke(n: Int, left: IntArray, right: IntArray): Int {
        var ans = 0
        for (num in left) {
            ans = max(ans.toDouble(), num.toDouble()).toInt()
        }

        for (num in right) {
            ans = max(ans, n - num)
        }

        return ans
    }
}
