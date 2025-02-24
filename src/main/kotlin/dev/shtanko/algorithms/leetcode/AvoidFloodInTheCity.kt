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

import dev.shtanko.algorithms.annotations.StraightForward
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.TreeSet

/**
 * 1488. Avoid Flood in The City
 * @see <a href="https://leetcode.com/problems/avoid-flood-in-the-city/">Source</a>
 */
@Medium("https://leetcode.com/problems/avoid-flood-in-the-city")
fun interface AvoidFlood {
    operator fun invoke(rains: IntArray): IntArray
}

class AvoidFloodTree : AvoidFlood {
    override operator fun invoke(rains: IntArray): IntArray {
        val full: MutableMap<Int, Int> = HashMap() // last days that is full

        val drain: TreeSet<Int> = TreeSet() // storage days to be used for drain

        val n: Int = rains.size
        val res = IntArray(n) { 1 }

        for (i in rains.indices) {
            val lake = rains[i]
            if (full.containsKey(lake)) {
                val key: Int = drain.ceiling(full[lake])
                    ?: return intArrayOf() // find if there is a day could be drain after last full
                // did not find, flooded
                res[key] = lake // set the day to be drained with lake
                drain.remove(key)
            }
            if (lake == 0) {
                drain.add(i) // we got new days
            } else {
                full[lake] = i
                res[i] = -1 // lake is 0, or could be dry
            }
        }
        return res
    }
}

@StraightForward
class AvoidFloodSimple : AvoidFlood {
    private val empty = IntArray(0)

    override operator fun invoke(rains: IntArray): IntArray {
        val ans = IntArray(rains.size)
        val n: Int = rains.size
        val fullLakes: MutableMap<Int, Int> = HashMap()
        val noRains = TreeSet<Int?>()

        for (i in 0 until n) {
            if (rains[i] == 0) {
                noRains.add(i)
                ans[i] = 1
            } else if (rains[i] > 0) {
                if (fullLakes.containsKey(rains[i])) {
                    val canDry = noRains.ceiling(fullLakes[rains[i]]) ?: return empty
                    ans[canDry] = rains[i]
                    noRains.remove(canDry)
                }
                fullLakes[rains[i]] = i
                ans[i] = -1
            }
        }
        return ans
    }
}
