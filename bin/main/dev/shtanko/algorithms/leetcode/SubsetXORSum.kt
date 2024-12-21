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
 * 1863. Sum of All Subset XOR Totals
 * @see <a href="https://leetcode.com/problems/sum-of-all-subset-xor-totals/">Source</a>
 */
fun interface SubsetXORSum {
    operator fun invoke(nums: IntArray): Int
}

/**
 * This class implements the SubsetXORSum interface using a bitwise approach.
 * It calculates the XOR of all elements in the array and then multiplies it by 2 to the power of (n-1), where n
 * is the size of the array.
 */
class SubsetXORSumBitwise : SubsetXORSum {
    /**
     * This function calculates the sum of all subset XOR totals using a bitwise approach.
     * @param nums The input array of integers.
     * @return The sum of XOR of all subsets.
     */
    override operator fun invoke(nums: IntArray): Int {
        var res = 0
        for (num in nums) {
            res = res or num
        }
        return res * (1 shl nums.size - 1)
    }
}
