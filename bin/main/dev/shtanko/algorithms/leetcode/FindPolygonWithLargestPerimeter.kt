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
 * 2971. Find Polygon With the Largest Perimeter
 * @see <a href="https://leetcode.com/problems/find-polygon-with-the-largest-perimeter">Source</a>
 */
fun interface FindPolygonWithLargestPerimeter {
    operator fun invoke(nums: IntArray): Long
}

class FindPolygonWithLargestPerimeterSort : FindPolygonWithLargestPerimeter {
    override fun invoke(nums: IntArray): Long {
        nums.sort()
        var previousElementsSum: Long = 0
        var ans: Long = -1
        for (num in nums) {
            if (num < previousElementsSum) {
                ans = num + previousElementsSum
            }
            previousElementsSum += num.toLong()
        }
        return ans
    }
}
