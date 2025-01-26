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
 * Recursion-2 > split53
 * @see <a href="https://codingbat.com/prob/p168295">Source</a>
 */
fun interface Split53 {
    operator fun invoke(nums: IntArray): Boolean
}

class Split53Iterative : Split53 {
    override fun invoke(nums: IntArray): Boolean {
        val stack = Stack<Split53Group>()
        stack.push(Split53Group(0, 0, 0))

        while (stack.isNotEmpty()) {
            val (index, sumOfFive, sumOfThree) = stack.pop()
            if (index == nums.size) {
                if (sumOfFive == sumOfThree) {
                    return true
                }
                continue
            }
            val num = nums[index]
            if (num % 5 == 0) {
                stack.push(Split53Group(index + 1, sumOfFive + num, sumOfThree))
            } else if (num % 3 == 0) {
                stack.push(Split53Group(index + 1, sumOfFive, sumOfThree + num))
            } else {
                stack.push(Split53Group(index + 1, sumOfFive + num, sumOfThree))
                stack.push(Split53Group(index + 1, sumOfFive, sumOfThree + num))
            }
        }
        return false
    }

    private data class Split53Group(val index: Int, val sumOfFive: Int, val sumOfThree: Int)
}

class Split53Recursive : Split53 {
    override fun invoke(nums: IntArray): Boolean {
        return canSplit(nums, 0, 0, 0)
    }

    private fun canSplit(nums: IntArray, index: Int, sumOfFive: Int, sumOfThree: Int): Boolean {
        if (index >= nums.size) {
            return sumOfFive == sumOfThree
        }

        return when {
            nums[index] % 5 == 0 -> {
                canSplit(nums, index + 1, sumOfFive + nums[index], sumOfThree)
            }

            nums[index] % 3 == 0 -> {
                canSplit(nums, index + 1, sumOfFive, sumOfThree + nums[index])
            }

            else -> {
                canSplit(nums, index + 1, sumOfFive + nums[index], sumOfThree) || canSplit(
                    nums,
                    index + 1,
                    sumOfFive,
                    sumOfThree + nums[index],
                )
            }
        }
    }
}
