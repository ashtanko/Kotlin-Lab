/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.codingbat.recursion2

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.extensions.isEven

/**
 * Recursion-2 > splitOdd10
 * @see <a href="https://codingbat.com/prob/p171660">Source</a>
 */
fun interface SplitOdd10 {
    operator fun invoke(nums: IntArray): Boolean
}

class SplitOdd10Recursive : SplitOdd10 {
    override fun invoke(nums: IntArray): Boolean {
        return canDivide(nums, 0, 0, 0)
    }

    private fun canDivide(nums: IntArray, index: Int, leftSum: Int, rightSum: Int): Boolean {
        if (index >= nums.size) {
            return leftSum % DECIMAL == 0 && rightSum.isEven.not()
        }
        val num = nums[index]
        if (canDivide(nums, index + 1, leftSum + num, rightSum)) {
            return true
        }
        if (canDivide(nums, index + 1, leftSum, rightSum + num)) {
            return true
        }
        return false
    }
}
