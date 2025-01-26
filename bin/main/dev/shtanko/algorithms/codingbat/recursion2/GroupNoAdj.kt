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

package dev.shtanko.algorithms.codingbat.recursion2

import java.util.Stack

/**
 * Recursion-2 > groupNoAdj
 * @see <a href="https://codingbat.com/prob/p169605">Source</a>
 */
fun interface GroupNoAdj {
    operator fun invoke(start: Int, nums: IntArray, target: Int): Boolean
}

class GroupNoAdjIterative : GroupNoAdj {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        val stack = Stack<GroupNoAdj>()
        stack.push(GroupNoAdj(start, 0, target))
        while (stack.isNotEmpty()) {
            val (currStart, currIndex, currTarget) = stack.pop()
            if (currStart >= nums.size) {
                if (currTarget == 0) {
                    return true
                }
                continue
            }
            stack.push(GroupNoAdj(currStart + 2, currIndex + 1, currTarget - nums[currStart]))
            stack.push(GroupNoAdj(currStart + 1, currIndex + 1, currTarget))
        }
        return false
    }

    private data class GroupNoAdj(
        val start: Int,
        val index: Int,
        val target: Int,
    )
}

class GroupNoAdjRecursive : GroupNoAdj {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        if (start >= nums.size) {
            return target == 0
        }
        if (invoke(start + 2, nums, target - nums[start])) {
            return true
        }
        if (invoke(start + 1, nums, target)) {
            return true
        }
        return false
    }
}
