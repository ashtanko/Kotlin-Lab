/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

/**
 * 976. Largest Perimeter Triangle
 * @see <a href="https://leetcode.com/problems/largest-perimeter-triangle/">Source</a>
 */
fun interface LargestPerimeterTriangle {
    operator fun invoke(nums: IntArray): Int
}

class LargestPerimeterTriangleSort : LargestPerimeterTriangle {
    override fun invoke(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        nums.sort()
        for (i in nums.size - 3 downTo 0) {
            val sum = nums[i].plus(nums[i + 1])
            if (sum > nums[i + 2]) {
                return sum.plus(nums[i + 2])
            }
        }
        return 0
    }
}
