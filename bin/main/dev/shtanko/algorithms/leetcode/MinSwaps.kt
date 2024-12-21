/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.TwoPointers
import kotlin.math.max

/**
 * Minimum Swaps to Group All 1's Together
 * @see <a href="https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/">Source</a>
 */
fun interface MinSwaps {
    operator fun invoke(data: IntArray): Int
}

/**
 * Approach 1: Sliding Window with Two Pointers
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
@TwoPointers
class MinSwapsTwoPointers : MinSwaps {
    override operator fun invoke(data: IntArray): Int {
        val ones: Int = data.sum()
        var cntOne = 0
        var maxOne = 0
        var left = 0
        var right = 0

        while (right < data.size) {
            // updating the number of 1's by adding the new element
            cntOne += data[right++]
            // maintain the length of the window to ones
            if (right - left > ones) {
                // updating the number of 1's by removing the oldest element
                cntOne -= data[left++]
            }
            // record the maximum number of 1's in the window
            maxOne = max(maxOne, cntOne)
        }
        return ones - maxOne
    }
}

/**
 * Approach 2: Sliding Window with Deque (Double-ended Queue)
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
class MinSwapsDeque : MinSwaps {
    override operator fun invoke(data: IntArray): Int {
        val ones: Int = data.sum()
        var cntOne = 0
        var maxOne = 0
        // maintain a deque with the size = ones
        val deque = ArrayDeque<Int>()

        for (i in data.indices) {
            // we would always add the new element into the deque
            deque.addLast(data[i])
            cntOne += data[i]
            // when there are more than ones elements in the deque,
            // remove the leftmost one
            if (deque.size > ones) {
                cntOne -= deque.removeFirst()
            }
            maxOne = max(maxOne, cntOne)
        }
        return ones - maxOne
    }
}
