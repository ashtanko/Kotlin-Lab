/*
 * Copyright 2023 Oleksii Shtanko
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

import java.util.Deque
import java.util.LinkedList

/**
 * 239. Sliding Window Maximum
 * @see <a href="https://leetcode.com/problems/sliding-window-maximum/">Source</a>
 */
fun interface SlidingWindowMax {
    operator fun invoke(nums: IntArray, k: Int): IntArray
}

class MonotonicDeque : SlidingWindowMax {
    override operator fun invoke(nums: IntArray, k: Int): IntArray {
        val dq: Deque<Int> = LinkedList()
        val res: MutableList<Int> = ArrayList()

        for (i in 0 until k) {
            while (dq.isNotEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast()
            }
            dq.offerLast(i)
        }
        res.add(nums[dq.peekFirst()])

        for (i in k until nums.size) {
            if (dq.peekFirst() == i - k) {
                dq.pollFirst()
            }
            while (dq.isNotEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast()
            }
            dq.offerLast(i)
            res.add(nums[dq.peekFirst()])
        }
        // Return the result as an array.
        return res.stream().mapToInt { i: Int? -> i!! }.toArray()
    }
}
