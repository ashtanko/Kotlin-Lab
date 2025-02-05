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

import java.util.Deque
import java.util.LinkedList

// Number of Valid Subarrays
class ValidSubArrays {
    operator fun invoke(nums: IntArray): Int {
        var res: Int = nums.size
        if (nums.size <= 1) {
            return res
        }
        val stack: Deque<Int> = LinkedList()
        stack.push(nums[0])
        for (i in 1 until nums.size) {
            val num = nums[i]
            while (stack.isNotEmpty() && num < stack.peek()) {
                stack.pop()
            }
            res += stack.size
            stack.push(num)
        }
        return res
    }
}
