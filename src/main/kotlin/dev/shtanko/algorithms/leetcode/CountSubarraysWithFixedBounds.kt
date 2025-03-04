/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Hard
import kotlin.math.max
import kotlin.math.min

/**
 * 2444. Count Subarrays With Fixed Bounds
 * @see <a href="https://leetcode.com/problems/count-subarrays-with-fixed-bounds/">Source</a>
 */
@Hard("https://leetcode.com/problems/count-subarrays-with-fixed-bounds")
fun interface CountSubarraysWithFixedBounds {
    fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long
}

/**
 * Count the number of subarrays, using sliding window(three pointers).
 * We maintain a maximum sliding window with all elements in range [minK, maxK],
 * For all nums[i] as rightmost element of the subarray,
 * we find the three indices j, where:
 * jbad is index of last seen nums[jbad] < minK || nums[jbad] > maxK
 * jmin is index of last seen nums[jmin] = mink
 * jmax is index of last seen nums[jmax] = maxk
 *
 * Complexity:
 * Time O(n)
 * Space O(1)
 */
class CountSubarraysWithFixedBoundsSlidingWindow : CountSubarraysWithFixedBounds {
    override fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        val n: Int = nums.size
        var leftBound = -1
        var lastMin = -1
        var lastMax = -1
        var count: Long = 0

        for (i in 0 until n) {
            if (nums[i] in minK..maxK) {
                lastMin = if (nums[i] == minK) i else lastMin
                lastMax = if (nums[i] == maxK) i else lastMax
                count += max(0, min(lastMin, lastMax) - leftBound).toLong()
            } else {
                leftBound = i
                lastMin = -1
                lastMax = -1
            }
        }

        return count
    }
}
