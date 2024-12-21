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

/**
 * 1207. Unique Number of Occurrences
 * @see <a href="https://leetcode.com/problems/unique-number-of-occurrences">Source</a>
 */
fun interface UniqueOccurrences {
    operator fun invoke(arr: IntArray): Boolean
}

class UniqueOccurrencesMap : UniqueOccurrences {
    override fun invoke(arr: IntArray): Boolean {
        val count: MutableMap<Int, Int> = HashMap()
        for (num in arr) {
            count[num] = 1 + count.getOrDefault(num, 0)
        }
        return count.size == count.values.toHashSet().size
    }
}

class UniqueOccurrencesSort : UniqueOccurrences {
    override fun invoke(arr: IntArray): Boolean {
        arr.sort()
        val ans = mutableListOf<Int>()
        var i = 0
        while (i < arr.size) {
            var count = 1
            var j = i + 1
            while (j < arr.size && arr[i] == arr[j]) {
                count++
                j++
            }
            ans.add(count)
            i += count
        }
        ans.sort()
        for (j in 0 until ans.size - 1) {
            if (ans[j] == ans[j + 1]) {
                return false
            }
        }
        return true
    }
}
