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

import dev.shtanko.algorithms.DECIMAL

/**
 * Recursion-1 > array220
 * @see <a href="https://codingbat.com/prob/p173469">Source</a>
 */
fun interface Array220 {
    operator fun invoke(nums: IntArray, index: Int): Boolean
}

class Array220Iterative : Array220 {
    override fun invoke(nums: IntArray, index: Int): Boolean {
        for (i in 0 until nums.size - 1) {
            val curr = nums[i]
            val next = curr * DECIMAL
            if (nums[i + 1] == next) {
                return true
            }
        }
        return false
    }
}

class Array220Recursive : Array220 {
    override fun invoke(nums: IntArray, index: Int): Boolean {
        if (index >= nums.size) {
            return false
        }
        if (nums.sliceArray(index..<nums.size).contains(nums[index] * DECIMAL)) {
            return true
        }
        return invoke(nums, index + 1)
    }
}

class Array220Recursive2 : Array220 {
    override fun invoke(nums: IntArray, index: Int): Boolean {
        if (index >= nums.size) {
            return false
        }

        for (i in index until nums.size) {
            if (nums[index] * DECIMAL == nums[i]) {
                return true
            }
        }

        return invoke(nums, index + 1)
    }
}
