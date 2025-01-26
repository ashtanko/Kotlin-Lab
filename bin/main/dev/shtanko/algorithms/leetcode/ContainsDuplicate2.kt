/*
 * Copyright 2020 Oleksii Shtanko
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

import java.util.TreeSet
import kotlin.math.max

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums of i = nums of j and the absolute difference between i and j is at most k.
 */
fun interface ContainsDuplicate2 {
    operator fun invoke(nums: IntArray, k: Int): Boolean
}

/**
 * Approach #1: Naive Linear Search.
 * Time complexity : O(n * min(k,n)).
 * Space complexity : O(1).
 */
class ContainsDuplicateLinear : ContainsDuplicate2 {
    override operator fun invoke(nums: IntArray, k: Int): Boolean {
        for (i in nums.indices) {
            for (j in max(i - k, 0) until i) {
                if (nums[i] == nums[j]) return true
            }
        }
        return false
    }
}

/**
 * Approach #2: Binary Search Tree.
 * Time complexity : O(nlog(min(k,n))).
 * Space complexity : O(min(n,k)).
 */
class ContainsDuplicateBinarySearchTree : ContainsDuplicate2 by ContainsDuplicateBehavior(TreeSet())

/**
 * Approach #3: Hash Table.
 *
 */
class ContainsDuplicateHash : ContainsDuplicate2 by ContainsDuplicateBehavior(HashSet())

class ContainsDuplicateBehavior(private val set: MutableSet<Int>) : ContainsDuplicate2 {
    override operator fun invoke(nums: IntArray, k: Int): Boolean {
        for (i in nums.indices) {
            if (set.contains(nums[i])) return true
            set.add(nums[i])
            if (set.size > k) {
                set.remove(nums[i - k])
            }
        }
        return false
    }
}
