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
 * 1578. Minimum Time to Make Rope Colorful
 * @see <a href="https://leetcode.com/problems/minimum-time-to-make-rope-colorful">Source</a>
 */
fun interface MinTimeToMakeRopeColorful {
    operator fun invoke(colors: String, neededTime: IntArray): Int
}

class MinTimeToMakeRopeColorfulSF : MinTimeToMakeRopeColorful {
    override fun invoke(colors: String, neededTime: IntArray): Int {
        var res = neededTime[0]
        var maxCost = neededTime[0]

        for (i in 1 until colors.length) {
            if (colors[i] != colors[i - 1]) {
                res -= maxCost
                maxCost = 0
            }
            res += neededTime[i]
            maxCost = maxOf(maxCost, neededTime[i])
        }

        return res - maxCost
    }
}
