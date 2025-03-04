/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.PriorityQueue

/**
 * 2593. Find Score of an Array After Marking All Elements
 * @see <a href="https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements/">Source</a>
 */
@Medium
sealed interface FindScore {
    operator fun invoke(nums: IntArray): Long

    data object Sorting : FindScore {
        override fun invoke(nums: IntArray): Long {
            var ans = 0L
            val sorted = Array(nums.size) { i -> intArrayOf(nums[i], i) }
            val marked = BooleanArray(nums.size)

            sorted.sortWith(compareBy { it[0] })

            for (element in sorted) {
                val number = element[0]
                val index = element[1]
                if (!marked[index]) {
                    ans += number
                    marked[index] = true
                    // mark adjacent elements if they exist
                    if (index - 1 >= 0) {
                        marked[index - 1] = true
                    }
                    if (index + 1 < nums.size) {
                        marked[index + 1] = true
                    }
                }
            }

            return ans
        }
    }

    data object Heap : FindScore {
        override fun invoke(nums: IntArray): Long {
            var ans = 0L
            val marked = BooleanArray(nums.size)

            val heap = PriorityQueue(compareBy<IntArray>({ it[0] }, { it[1] }))

            for (i in nums.indices) {
                heap.add(intArrayOf(nums[i], i))
            }

            while (heap.isNotEmpty()) {
                val (number, index) = heap.poll()
                if (!marked[index]) {
                    ans += number
                    marked[index] = true
                    // mark adjacent elements if they exist
                    if (index - 1 >= 0) {
                        marked[index - 1] = true
                    }
                    if (index + 1 < nums.size) {
                        marked[index + 1] = true
                    }
                }
            }

            return ans
        }
    }
}
