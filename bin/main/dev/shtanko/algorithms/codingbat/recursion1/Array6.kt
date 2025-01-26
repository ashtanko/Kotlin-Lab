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
 * Recursion-1 > array6
 * @see <a href="https://codingbat.com/prob/p108997">Source</a>
 */
fun interface Array6 {
    operator fun invoke(nums: IntArray, index: Int): Boolean
}

class Array6Iterative : Array6 {
    override fun invoke(nums: IntArray, index: Int) = nums.sliceArray(index..<nums.size).contains(6)
}

class Array6Recursive : Array6 {
    override fun invoke(nums: IntArray, index: Int): Boolean {
        if (index >= nums.size) {
            return false
        }
        if (nums[index] == 6) {
            return true
        }
        return invoke(nums, index + 1)
    }
}
