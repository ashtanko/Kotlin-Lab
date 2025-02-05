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
 * Recursion-2 > splitArray
 * @see <a href="https://codingbat.com/prob/p185204">Source</a>
 */
fun interface SplitArray {
    operator fun invoke(nums: IntArray): Boolean
}

class SplitArrayIterative : SplitArray {
    override fun invoke(nums: IntArray): Boolean {
        val stack = Stack<Triple<Int, Int, Int>>() // <idx, leftSum, rightSum>
        stack.push(Triple(0, 0, 0))

        while (stack.isNotEmpty()) {
            val (idx, leftSum, rightSum) = stack.pop()
            if (idx == nums.size) {
                if (leftSum == rightSum) {
                    return true
                }
                continue
            }
            // Push the next states onto the stack
            stack.push(Triple(idx + 1, leftSum + nums[idx], rightSum))
            stack.push(Triple(idx + 1, leftSum, rightSum + nums[idx]))
        }
        return false
    }
}

class SplitArrayRecursive : SplitArray {
    override fun invoke(nums: IntArray): Boolean {
        return canSplit(nums, 0, 0, 0)
    }

    private fun canSplit(nums: IntArray, idx: Int, leftSum: Int, rightSum: Int): Boolean {
        if (idx == nums.size) {
            return leftSum == rightSum
        }
        if (canSplit(nums, idx + 1, leftSum + nums[idx], rightSum)) {
            return true
        }
        if (canSplit(nums, idx + 1, leftSum, rightSum + nums[idx])) {
            return true
        }
        return false
    }
}
