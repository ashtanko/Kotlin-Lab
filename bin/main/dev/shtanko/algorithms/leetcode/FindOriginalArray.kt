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
 * 2007. Find Original Array From Doubled Array
 * @see <a href="https://leetcode.com/problems/find-original-array-from-doubled-array/">Source</a>
 */
fun interface FindOriginalArray {
    fun findOriginalArray(changed: IntArray): IntArray
}

class FindOriginalArrayMatch : FindOriginalArray {
    override fun findOriginalArray(changed: IntArray): IntArray {
        val n: Int = changed.size
        var i = 0
        if (n % 2 == 1) return IntArray(0)
        val res = IntArray(n / 2)
        val count: MutableMap<Int, Int> = TreeMap()
        for (a in changed) {
            count[a] = count.getOrDefault(a, 0) + 1
        }
        for (x in count.keys) {
            if (count.getOrDefault(x, 0) > count.getOrDefault(x + x, 0)) {
                return IntArray(0)
            }
            for (j in 0 until count.getOrDefault(x, 0)) {
                res[i++] = x
                count[x + x] = count.getOrDefault(x + x, 0) - 1
            }
        }
        return res
    }
}
