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
 * 2610. Convert an Array Into a 2D Array With Conditions
 * @see <a href="https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions">Source</a>
 */
fun interface FindMatrix {
    operator fun invoke(nums: IntArray): List<List<Int>>
}

class FrequencyCounter : FindMatrix {
    override fun invoke(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) {
            return emptyList()
        }
        val freq = nums.groupBy { it }
            .mapValues { it.value.toMutableList() }
        return buildList {
            repeat(freq.values.maxOf { it.size }) {
                add(
                    buildList {
                        for ((_, v) in freq)
                            if (v.isNotEmpty()) add(v.removeLast())
                    },
                )
            }
        }
    }
}
