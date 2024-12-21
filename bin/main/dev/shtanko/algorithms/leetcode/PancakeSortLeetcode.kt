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

object PancakeSortLeetcode {
    fun pancakeSort(arr: IntArray): List<Int> {
        val ans: MutableList<Int> = ArrayList()

        for (valueToSort in arr.size downTo 1) {
            // locate the position for the value to sort in this round
            val index = this.find(arr, valueToSort)

            // sink the value_to_sort to the bottom,
            // with at most two steps of pancake flipping.
            if (index == valueToSort - 1) continue
            // 1). flip the value to the head if necessary
            if (index != 0) {
                ans.add(index + 1)
                flip(arr, index + 1)
            }
            // 2). now that the value is at the head, flip it to the bottom
            ans.add(valueToSort)
            flip(arr, valueToSort)
        }

        return ans
    }

    private fun flip(sublist: IntArray, k: Int) {
        var i = 0
        while (i < k / 2) {
            val temp = sublist[i]
            sublist[i] = sublist[k - i - 1]
            sublist[k - i - 1] = temp
            i += 1
        }
    }

    private fun find(a: IntArray, target: Int): Int {
        for (i in a.indices) if (a[i] == target) return i
        return -1
    }
}
