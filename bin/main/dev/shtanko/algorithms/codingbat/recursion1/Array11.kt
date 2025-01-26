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

package dev.shtanko.algorithms.codingbat.recursion1

/**
 * Recursion-1 > array11
 * @see <a href="https://codingbat.com/prob/p135988">Source</a>
 */
fun interface Array11 {
    operator fun invoke(nums: IntArray, index: Int): Int
}

private const val NUM_TO_SEARCH = 11

class Array11Iterative : Array11 {
    override fun invoke(nums: IntArray, index: Int): Int {
        return nums.sliceArray(index..<nums.size).filter { it == NUM_TO_SEARCH }.size
    }
}

class Array11Recursive : Array11 {
    override fun invoke(nums: IntArray, index: Int): Int {
        if (index >= nums.size) {
            return 0
        }
        return if (nums[index] == NUM_TO_SEARCH) {
            1 + invoke(nums, index + 1)
        } else {
            invoke(nums, index + 1)
        }
    }
}
