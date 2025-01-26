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
 * Recursion-2 > groupSum6
 * @see <a href="https://codingbat.com/prob/p199368">Source</a>
 */
fun interface GroupSum6 {
    operator fun invoke(start: Int, nums: IntArray, target: Int): Boolean
}

class GroupSum6Iterative : GroupSum6 {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        val stack = Stack<Group6>() // Using Group6 data class to store start, target, and index
        stack.push(Group6(start, target, 0))

        while (stack.isNotEmpty()) {
            val (currentStart, currentTarget, currentIndex) = stack.pop()

            if (currentIndex >= nums.size) {
                if (currentTarget == 0) {
                    return true
                }
                continue
            }

            if (nums[currentIndex] == 6) {
                stack.push(Group6(currentStart, currentTarget - 6, currentIndex + 1))
            } else {
                stack.push(Group6(currentStart, currentTarget - nums[currentIndex], currentIndex + 1))
                stack.push(Group6(currentStart, currentTarget, currentIndex + 1))
            }
        }

        return false
    }

    private data class Group6(
        val start: Int,
        val target: Int,
        val index: Int,
    )
}

class GroupSum6Recursive : GroupSum6 {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        if (start >= nums.size) {
            return target == 0
        }
        if (nums[start] == 6) {
            return invoke(start + 1, nums, target - 6)
        }

        return invoke(start + 1, nums, target - nums[start]) || return invoke(start + 1, nums, target)
    }
}
