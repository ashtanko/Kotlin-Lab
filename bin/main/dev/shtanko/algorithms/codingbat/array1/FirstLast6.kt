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

package dev.shtanko.algorithms.codingbat.array1

/**
 * Array-1 > firstLast6
 * @see <a href="https://codingbat.com/prob/p185685">Source</a>
 */
fun interface FirstLast6 {
    operator fun invoke(nums: IntArray): Boolean
}

class FirstLast6Array : FirstLast6 {
    override fun invoke(nums: IntArray): Boolean {
        if (nums.isEmpty()) {
            return false
        } else if (nums.size == 1 && nums.first() == 6) {
            return true
        }
        return nums.first() == 6 || nums.last() == 6
    }
}
