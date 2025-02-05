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

package dev.shtanko.algorithms.leetcode

import kotlin.math.abs

/**
 * 645. Set Mismatch
 * @see <a href="https://leetcode.com/problems/set-mismatch">Source</a>
 */
fun interface SetMismatch {
    operator fun invoke(nums: IntArray): IntArray
}

class SetMismatchBruteForce : SetMismatch {
    override fun invoke(nums: IntArray): IntArray {
        var dup = -1
        var missing = -1

        for (i in 1..nums.size) {
            var count = 0
            for (element in nums) {
                if (element == i) {
                    count++
                }
            }
            if (count == 2) {
                dup = i
            } else if (count == 0) {
                missing = i
            }
        }

        return intArrayOf(dup, missing)
    }
}

class SetMismatchSet : SetMismatch {
    override fun invoke(nums: IntArray): IntArray {
        val n: Int = nums.size
        val actualSum = n * (n + 1) / 2
        var arraySum = 0
        var uniqueSum = 0
        val s: MutableSet<Int> = HashSet()

        for (a in nums) {
            arraySum += a
        }

        for (a in nums) {
            s.add(a)
        }

        for (a in s) {
            uniqueSum += a
        }

        val missing = actualSum - uniqueSum
        val duplicate = arraySum - uniqueSum

        return intArrayOf(duplicate, missing)
    }
}

class SetMismatchMap : SetMismatch {
    override fun invoke(nums: IntArray): IntArray {
        val res = IntArray(2) // duplicate, missing

        // For each number we found, set nums[number-1] to its negative value (<0)
        for (element in nums) {
            val idx = abs(element) - 1 // since index starts from 0, and the set starts from 1
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx]
            } else {
                res[0] = idx + 1 // have already been found
            }
        }

        // At this point, only nums[missingNumber-1] > 0
        for (i in nums.indices) {
            if (nums[i] < 0) {
                nums[i] = -nums[i] // restore the original values
            } else {
                res[1] = i + 1 // since index starts from 0, and the set starts from 1
            }
        }
        return res
    }
}
