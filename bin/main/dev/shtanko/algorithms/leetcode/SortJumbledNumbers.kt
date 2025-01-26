/*
 * Copyright 2024 Oleksii Shtanko
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
 * 2191. Sort the Jumbled Numbers
 * @see <a href="https://leetcode.com/problems/sort-the-jumbled-numbers">Source</a>
 */
fun interface SortJumbledNumbers {
    operator fun invoke(mapping: IntArray, nums: IntArray): IntArray
}

class SortJumbledNumbersSorting : SortJumbledNumbers {
    override fun invoke(mapping: IntArray, nums: IntArray): IntArray {
        val storePairs = mutableListOf<Pair<Int, Int>>()
        for (i in nums.indices) {
            val number = nums[i].toString()
            var formed = ""
            for (j in number.indices) {
                formed += mapping[number[j] - '0'].toString()
            }
            val mappedValue = formed.toInt()
            storePairs.add(Pair(mappedValue, i))
        }
        storePairs.sortWith(compareBy { it.first })

        return IntArray(nums.size) { nums[storePairs[it].second] }
    }
}

class SortJumbledNumbersNoSort : SortJumbledNumbers {
    override fun invoke(mapping: IntArray, nums: IntArray): IntArray {
        val storePairs = mutableListOf<Pair<Int, Int>>()

        for (i in nums.indices) {
            var mappedValue = 0
            var temp = nums[i]
            var place = 1

            if (temp == 0) {
                storePairs.add(Pair(mapping[0], i))
                continue
            }
            while (temp != 0) {
                mappedValue = place * mapping[temp % 10] + mappedValue
                place *= 10
                temp /= 10
            }
            storePairs.add(Pair(mappedValue, i))
        }

        storePairs.sortBy { it.first }

        val answer = IntArray(nums.size)
        for (i in storePairs.indices) {
            answer[i] = nums[storePairs[i].second]
        }

        return answer
    }
}
