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

/**
 * 2300. Successful Pairs of Spells and Potions
 * @see <a href="https://leetcode.com/problems/successful-pairs-of-spells-and-potions/">Source</a>
 */
fun interface SuccessfulPairs {
    operator fun invoke(spells: IntArray, potions: IntArray, success: Long): IntArray
}

/**
 * Complexity
 * Time O(mlogm + nlogm)
 * Space O(n)
 */
class SuccessfulPairsSF : SuccessfulPairs {
    override operator fun invoke(spells: IntArray, potions: IntArray, success: Long): IntArray {
        potions.sort()
        val map = TreeMap<Long, Int>()
        map[Long.MAX_VALUE] = potions.size
        for (i in potions.size - 1 downTo 0) {
            map[potions[i].toLong()] = i
        }
        for (i in spells.indices) {
            val need = (success + spells[i] - 1) / spells[i]
            spells[i] = potions.size - map.ceilingEntry(need).value
        }
        return spells
    }
}

/**
 * Solution II: Two Sum
 * Time O(nlogn + mlogm)
 * Space O(n)
 */
class SuccessfulPairsTwoSum : SuccessfulPairs {
    override operator fun invoke(spells: IntArray, potions: IntArray, success: Long): IntArray {
        val spells0 = spells.clone()
        potions.sort()
        spells.sort()
        val count = HashMap<Int, Int>()
        val n: Int = spells.size
        val m: Int = potions.size
        var j = m - 1
        val res = IntArray(n)
        for (i in 0 until n) {
            while (j >= 0 && 1L * spells[i] * potions[j] >= success) j--
            count[spells[i]] = m - j - 1
        }
        for (i in 0 until n) {
            res[i] = count[spells0[i]]!!
        }
        return res
    }
}
