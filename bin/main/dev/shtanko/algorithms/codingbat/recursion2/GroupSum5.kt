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
 * Recursion-2 > groupSum5
 * @see <a href="https://codingbat.com/prob/p138907">Source</a>
 */
fun interface GroupSum5 {
    operator fun invoke(start: Int, nums: IntArray, target: Int): Boolean
}

class GroupSum5Iterative : GroupSum5 {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        val stack = Stack<Group5>()
        stack.push(Group5(start, target, false))

        while (stack.isNotEmpty()) {
            val (index, currentTarget, multipleOf5Included) = stack.pop()

            if (index >= nums.size) {
                if (currentTarget == 0) {
                    return true
                }
                continue
            }

            if (nums[index] % 5 == 0) {
                stack.push(Group5(index + 1, currentTarget - nums[index], true))
            } else if (index > 0 && nums[index - 1] % 5 == 0 && nums[index] == 1) {
                stack.push(Group5(index + 1, currentTarget, multipleOf5Included))
            } else {
                stack.push(Group5(index + 1, currentTarget - nums[index], multipleOf5Included))
                stack.push(Group5(index + 1, currentTarget, multipleOf5Included))
            }
        }
        return false
    }

    private data class Group5(
        val index: Int,
        val target: Int,
        val multipleOf5Included: Boolean,
    )
}

class GroupSum5Recursive : GroupSum5 {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        return groupSum5Helper(start, nums, target, false)
    }

    private fun groupSum5Helper(start: Int, nums: IntArray, target: Int, multipleOf5Included: Boolean): Boolean {
        // Base case
        if (start >= nums.size) {
            return target == 0
        }

        // Check if the current number is a multiple of 5
        if (nums[start] % 5 == 0) {
            return groupSum5Helper(start + 1, nums, target - nums[start], true)
        }
        // If the current number is not a multiple of 5 and the previous number was a multiple of 5
        // and the current number is 1, skip this number
        if (multipleOf5Included && nums[start] == 1) {
            return groupSum5Helper(start + 1, nums, target, multipleOf5Included)
        }

        // Include the current number in the sum
        if (groupSum5Helper(start + 1, nums, target - nums[start], multipleOf5Included)) {
            return true
        }
        // Exclude the current number from the sum
        return groupSum5Helper(start + 1, nums, target, multipleOf5Included)
    }
}
