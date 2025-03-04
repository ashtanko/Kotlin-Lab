/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2425. Bitwise XOR of All Pairings
 * @see <a href="https://leetcode.com/problems/bitwise-xor-of-all-pairings/">Source</a>
 */
@Medium("https://leetcode.com/problems/bitwise-xor-of-all-pairings")
fun interface XorAllNums {
    operator fun invoke(nums1: IntArray, nums2: IntArray): Int
}

class XorAllNumsConcise : XorAllNums {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): Int {
        var x = 0
        var y = 0
        for (a in nums1) {
            x = x xor a
        }
        for (b in nums2) {
            y = y xor b
        }
        return nums1.size % 2 * y xor nums2.size % 2 * x
    }
}

class XorAllNumsSimple : XorAllNums {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): Int {
        if (nums1.size % 2 == 0 && nums2.size % 2 == 0) { // if both arrays have even length
            return 0
        }
        val xorOne = xor(nums1)
        val xorTwo = xor(nums2)
        // if both arrays have odd length then xor of both arrays is the answer or else
        // xor of one even length array is the answer
        return if (nums1.size % 2 == 1 && nums2.size % 2 == 1) {
            xorOne xor xorTwo
        } else if (nums1.size % 2 != 0) {
            xorTwo
        } else {
            xorOne
        }
    }

    private fun xor(nums: IntArray): Int {
        var res = 0
        for (num in nums) {
            res = res xor num
        }
        return res
    }
}
