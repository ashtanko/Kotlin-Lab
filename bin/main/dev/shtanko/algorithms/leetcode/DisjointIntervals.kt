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

import java.util.TreeMap
import kotlin.math.max

/**
 * 352. Data Stream as Disjoint Intervals
 * @see <a href="https://leetcode.com/problems/data-stream-as-disjoint-intervals/">Source</a>
 */
class DisjointIntervals {

    var map: TreeMap<Int, IntArray> = TreeMap()

    fun addNum(value: Int) {
        if (map.containsKey(value)) return
        val low = map.lowerKey(value)
        val high = map.higherKey(value)
        when {
            low != null && high != null && value == map[low]!![1] + 1 && value == map[high]!![0] - 1 -> {
                // low =1,val=3 high=4 and high has key =[4,5] and low has key [1,2]
                // now 4 has no use as 1 2 3 4 5 is possible so [1,5]
                map[low]!![1] = map[high]!![1]
                map.remove(high)
            }

            low != null && value <= map[low]!![1] + 1 -> {
                // for example if 2,8 exists and val is 7 nothing happens but if it is 9 then 2,9
                // if i dont put <= then on 7  as  val,new value is added in the last else part which is incorrect
                map[low]!![1] = max(value, map[low]!![1])
            }

            high != null && value == map[high]!![0] - 1 -> {
                // if  5,8 exists and val is 4 so new=[4,8] and 5 is removed
                map[value] = intArrayOf(value, map[high]!![1])
                map.remove(high)
            }

            else -> {
                map[value] = intArrayOf(value, value)
            }
        }
    }

    fun getIntervals(): Array<IntArray> {
        val res = Array(map.size) { IntArray(2) }
        var i = 0
        for (x in map.values) {
            res[i++] = x
        }
        return res
    }
}
