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
 * 2053. Kth Distinct String in an Array
 * @see <a href="https://leetcode.com/problems/kth-distinct-string-in-an-array">Source</a>
 */
fun interface KTHDistinct {
    operator fun invoke(arr: Array<String>, k: Int): String
}

class KTHDistinctImpl : KTHDistinct {
    override operator fun invoke(arr: Array<String>, k: Int): String {
        val distinctMap: MutableMap<String, Boolean> = HashMap()
        var remainingK = k
        for (str in arr) {
            distinctMap[str] = !distinctMap.containsKey(str)
        }
        for (str in arr) {
            if (distinctMap.getOrDefault(str, false) && remainingK-- == 1) return str
        }
        return ""
    }
}
