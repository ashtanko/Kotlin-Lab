/*
 * Copyright 2021 Oleksii Shtanko
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

import java.util.Stack

fun interface NextGreaterElement1 {
    operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray
}

class NGBruteForce : NextGreaterElement1 {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        val res = IntArray(nums1.size)
        var j: Int
        for (i in nums1.indices) {
            var found = false
            j = 0
            while (j < nums2.size) {
                if (found && nums2[j] > nums1[i]) {
                    res[i] = nums2[j]
                    break
                }
                if (nums2[j] == nums1[i]) {
                    found = true
                }
                j++
            }
            if (j == nums2.size) {
                res[i] = -1
            }
        }
        return res
    }
}

class NGBetterForce : NextGreaterElement1 {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        val hash: HashMap<Int, Int> = HashMap()
        val res = IntArray(nums1.size)
        var j: Int
        for (i in nums2.indices) {
            hash[nums2[i]] = i
        }
        for (i in nums1.indices) {
            j = hash[nums1[i]]!! + 1
            while (j < nums2.size) {
                if (nums1[i] < nums2[j]) {
                    res[i] = nums2[j]
                    break
                }
                j++
            }
            if (j == nums2.size) {
                res[i] = -1
            }
        }
        return res
    }
}

class NGStack : NextGreaterElement1 {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        val stack: Stack<Int> = Stack()
        val map: HashMap<Int, Int> = HashMap()
        val res = IntArray(nums1.size)
        for (i in nums2.indices) {
            while (!stack.empty() && nums2[i] > stack.peek()) map[stack.pop()] = nums2[i]
            stack.push(nums2[i])
        }
        while (!stack.empty()) map[stack.pop()] = -1
        for (i in nums1.indices) {
            res[i] = map[nums1[i]]!!
        }
        return res
    }
}
