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

import dev.shtanko.algorithms.annotations.Greedy
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.TreeMap

/**
 * 954. Array of Doubled Pairs
 * @see <a href="https://leetcode.com/problems/array-of-doubled-pairs/">Source</a>
 */
@Medium("https://leetcode.com/problems/array-of-doubled-pairs")
fun interface ArrayOfDoubledPairs {
    operator fun invoke(arr: IntArray): Boolean
}

@Greedy
class ArrayOfDoubledPairsGreedy : ArrayOfDoubledPairs {
    override fun invoke(arr: IntArray): Boolean {
        val count: MutableMap<Int, Int> = TreeMap()
        for (a in arr) {
            count[a] = count.getOrDefault(a, 0) + 1
        }
        for (x in count.keys) {
            if (count[x] == 0) continue
            val want = if (x < 0) x / 2 else x * 2
            if (x < 0 && x % 2 != 0 || count.getOrDefault(x, 0) > count.getOrDefault(want, 0)) {
                return false
            }
            count[want] = count.getOrDefault(want, 0) - count.getOrDefault(x, 0)
        }
        return true
    }
}
