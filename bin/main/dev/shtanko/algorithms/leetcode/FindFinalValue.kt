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
 * 2154. Keep Multiplying Found Values by Two
 * @see <a href="https://leetcode.com/problems/keep-multiplying-found-values-by-two">Source</a>
 */
fun interface FindFinalValue {
    operator fun invoke(nums: IntArray, original: Int): Int
}

class FindFinalValueHashMap : FindFinalValue {
    override operator fun invoke(nums: IntArray, original: Int): Int {
        var o = original
        val set = HashSet<Int>()
        for (i in nums) if (i >= o) set.add(i)
        while (true) o *= if (set.contains(o)) 2 else break
        return o
    }
}
