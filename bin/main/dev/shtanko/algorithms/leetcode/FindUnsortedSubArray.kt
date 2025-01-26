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
import kotlin.math.max
import kotlin.math.min

/**
 * Shortest Unsorted Continuous Subarray
 * @see <a href="https://leetcode.com/problems/shortest-unsorted-continuous-subarray/">Source</a>
 */
fun interface FindUnsortedSubArray {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Brute Force
 * Time complexity : O(n^3)
 * Space complexity : O(1)
 */
class FindUnsortedSubArrayBruteForce : FindUnsortedSubArray {
    override operator fun invoke(nums: IntArray): Int {
        var res: Int = nums.size
        for (i in nums.indices) {
            for (j in i..nums.size) {
                var min = Int.MAX_VALUE
                var max = Int.MIN_VALUE
                var prev = Int.MIN_VALUE
                for (k in i until j) {
                    min = min(min, nums[k])
                    max = max(max, nums[k])
                }
                val first = i > 0 && nums[i - 1] > min
                val second = j < nums.size && nums[j] < max
                if (first || second) continue
                var k = 0
                while (k < i && prev <= nums[k]) {
                    prev = nums[k]
                    k++
                }
                if (k != i) continue
                k = j
                while (k < nums.size && prev <= nums[k]) {
                    prev = nums[k]
                    k++
                }
                if (k == nums.size) {
                    res = min(res, j - i)
                }
            }
        }
        return res
    }
}

/**
 * Approach 2: Better Brute Force
 * Time complexity : O(n^2)
 * Space complexity : O(1)
 */
class FindUnsortedSubArrayBetterBruteForce : FindUnsortedSubArray {
    override operator fun invoke(nums: IntArray): Int {
        var l: Int = nums.size
        var r = 0
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[j] < nums[i]) {
                    r = max(r, j)
                    l = min(l, i)
                }
            }
        }
        return if (r - l <= 0) 0 else r - l + 1
    }
}

/**
 * Approach 3: Using Sorting
 * Time complexity : O(n\log n)
 * Space complexity : O(n)
 */
class FindUnsortedSubArraySort : FindUnsortedSubArray {
    override operator fun invoke(nums: IntArray): Int {
        val snums = nums.clone().sorted()
        var start = snums.size
        var end = 0
        for (i in snums.indices) {
            if (snums[i] != nums[i]) {
                start = min(start, i)
                end = max(end, i)
            }
        }
        return if (end - start > 0) end - start + 1 else 0
    }
}

/**
 * Approach 4: Using Stack
 * Time complexity : O(n)
 * Space complexity : O(n)
 */
class FindUnsortedSubArrayStack : FindUnsortedSubArray {
    override operator fun invoke(nums: IntArray): Int {
        val stack: Stack<Int> = Stack<Int>()
        var l: Int = nums.size
        var r = 0
        for (i in nums.indices) {
            while (stack.isNotEmpty() && nums[stack.peek()] > nums[i]) l = min(l, stack.pop())
            stack.push(i)
        }
        stack.clear()
        for (i in nums.size - 1 downTo 0) {
            while (stack.isNotEmpty() && nums[stack.peek()] < nums[i]) r = max(r, stack.pop())
            stack.push(i)
        }
        return if (r - l > 0) r - l + 1 else 0
    }
}

/**
 * Approach 5: Without Using Extra Space
 * Time complexity : O(n)
 * Space complexity : O(1)
 */
class FindUnsortedSubArrayConstSpace : FindUnsortedSubArray {
    override operator fun invoke(nums: IntArray): Int {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        var flag = false
        for (i in 1 until nums.size) {
            if (nums[i] < nums[i - 1]) flag = true
            if (flag) min = min(min, nums[i])
        }
        flag = false
        for (i in nums.size - 2 downTo 0) {
            if (nums[i] > nums[i + 1]) flag = true
            if (flag) max = max(max, nums[i])
        }
        var l = 0
        while (l < nums.size) {
            if (min < nums[l]) break
            l++
        }
        var r: Int = nums.size - 1
        while (r >= 0) {
            if (max > nums[r]) break
            r--
        }

        return if (r - l < 0) 0 else r - l + 1
    }
}
