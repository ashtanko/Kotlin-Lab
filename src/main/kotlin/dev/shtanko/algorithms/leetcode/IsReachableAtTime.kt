/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import kotlin.math.abs
import kotlin.math.max

/**
 * 2849. Determine if a Cell Is Reachable at a Given Time
 * @see <a href="https://leetcode.com/problems/determine-if-a-cell-is-reachable-at-a-given-time">Source</a>
 */
fun interface IsReachableAtTime {
    operator fun invoke(sx: Int, sy: Int, fx: Int, fy: Int, t: Int): Boolean
}

class IsReachableAtTimeMath : IsReachableAtTime {
    override fun invoke(sx: Int, sy: Int, fx: Int, fy: Int, t: Int): Boolean {
        val width = abs(sx - fx)
        val height = abs(sy - fy)
        return if (width == 0 && height == 0 && t == 1) {
            false
        } else t >= max(width.toDouble(), height.toDouble())
    }
}
