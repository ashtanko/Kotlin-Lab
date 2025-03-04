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

import dev.shtanko.algorithms.annotations.TwoPointers
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1574. Shortest Subarray to be Removed to Make Array Sorted
 * @see <a href="https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/">Source</a>
 */
@Medium("https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/")
fun interface FindLengthOfShortestSubarray {
    operator fun invoke(arr: IntArray): Int
}

@TwoPointers
data object FindLenOfShortestSubarrayTwoPointers : FindLengthOfShortestSubarray {
    override fun invoke(arr: IntArray): Int {
        var right = arr.size - 1
        while (right > 0 && arr[right] >= arr[right - 1]) {
            right--
        }

        var ans = right
        var left = 0
        while (left < right && (left == 0 || arr[left - 1] <= arr[left])) {
            // Find next valid number after arr[left]
            while (right < arr.size && arr[left] > arr[right]) {
                right++
            }
            // Save length of removed subarray
            ans = minOf(ans, right - left - 1)
            left++
        }
        return ans
    }
}
