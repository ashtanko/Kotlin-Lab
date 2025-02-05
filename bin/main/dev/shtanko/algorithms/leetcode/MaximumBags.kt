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

/**
 * 2279. Maximum Bags With Full Capacity of Rocks
 * @see <a href="https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/">Source</a>
 */
fun interface MaximumBags {
    operator fun invoke(capacity: IntArray, rocks: IntArray, additionalRocks: Int): Int
}

class MaximumBagsGreedy : MaximumBags {
    override operator fun invoke(capacity: IntArray, rocks: IntArray, additionalRocks: Int): Int {
        var add = additionalRocks
        val n: Int = rocks.size
        var cnt = 0
        for (i in 0 until n) {
            rocks[i] = capacity[i] - rocks[i]
        }
        rocks.sort()
        var i = 0
        while (i < n && rocks[i] - add <= 0) {
            cnt++
            add -= rocks[i]
            i++
        }
        return cnt
    }
}
