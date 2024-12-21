/*
 * Copyright 2022 Oleksii Shtanko
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

/**
 * 330. Patching Array
 * @see <a href="https://leetcode.com/problems/patching-array/">Source</a>
 */
fun interface PatchingArray {
    operator fun invoke(nums: IntArray, n: Int): Int
}

/**
 * This class provides a simple solution to the patching array problem.
 * It implements the PatchingArray interface.
 */
class PatchingArraySimple : PatchingArray {
    /**
     * This method finds the minimum number of patches required.
     * It uses a greedy approach, always adding the smallest number that cannot be formed.
     *
     * @param nums The original array.
     * @param n The upper limit of the range.
     * @return The minimum number of patches required.
     */
    override fun invoke(nums: IntArray, n: Int): Int {
        // The smallest number that cannot be formed
        var miss: Long = 1
        // The number of patches added
        var added = 0
        // The index of the current number in the array
        var i = 0
        // While there are numbers that cannot be formed
        while (miss <= n) {
            // If the current number is less than or equal to the smallest number that cannot be formed
            if (i < nums.size && nums[i] <= miss) {
                // Add the current number to the smallest number that cannot be formed
                miss += nums[i++]
            } else {
                // Add the smallest number that cannot be formed to itself and increment the number of patches
                miss += miss
                added++
            }
        }
        // Return the number of patches
        return added
    }
}
