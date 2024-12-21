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

import dev.shtanko.algorithms.annotations.level.Easy
import java.util.TreeMap

/**
 * 1331. Rank Transform of an Array
 * @see <a href="https://leetcode.com/problems/rank-transform-of-an-array/">Source</a>
 *
 * @return IntArray with ranks assigned to elements.
 */
@Easy("https://leetcode.com/problems/rank-transform-of-an-array")
fun interface RankTransformOfArray {
    operator fun invoke(arr: IntArray): IntArray
}

class RankTransformOfArraySort : RankTransformOfArray {
    override fun invoke(arr: IntArray): IntArray {
        val numToRank = mutableMapOf<Int, Int>()
        val sortedArr = arr.copyOf()
        sortedArr.sort()
        var rank = 1
        for (i in sortedArr.indices) {
            if (i > 0 && sortedArr[i] > sortedArr[i - 1]) {
                rank++
            }
            numToRank[sortedArr[i]] = rank
        }
        for (i in arr.indices) {
            arr[i] = numToRank[arr[i]] ?: 0
        }
        return arr
    }
}

class RankTransformOfArraySet : RankTransformOfArray {
    override fun invoke(arr: IntArray): IntArray {
        val numToRank = mutableMapOf<Int, Int>()
        // Deduplicate and sort arr
        val nums = sortedSetOf<Int>()
        for (num in arr) {
            nums.add(num)
        }
        var rank = 1
        for (num in nums) {
            numToRank[num] = rank
            rank++
        }
        for (i in arr.indices) {
            arr[i] = numToRank[arr[i]] ?: 0
        }
        return arr
    }
}

class RankTransformOfArrayTree : RankTransformOfArray {
    override fun invoke(arr: IntArray): IntArray {
        return arr.arrayRankTransform()
    }

    fun IntArray.arrayRankTransform(): IntArray {
        // Using TreeMap to automatically sort elements and keep track of their indices
        val map: MutableMap<Int, MutableList<Int>> = TreeMap()

        // Populate the map with indices for each unique element
        for (i in indices) {
            val current = map.getOrDefault(this[i], mutableListOf())
            current.add(i)
            map[this[i]] = current
        }

        // Assign ranks to elements in the array
        var rank = 1
        for ((_, currentList) in map) {
            for (i in currentList) {
                this[i] = rank
            }
            rank++
        }

        return this
    }
}
