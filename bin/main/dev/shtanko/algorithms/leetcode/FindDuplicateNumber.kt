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

/**
 * Find the Duplicate Number
 * @see <a href="https://leetcode.com/problems/find-the-duplicate-number/">Source</a>
 */
fun interface FindDuplicateNumber {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Time complexity : O(n lg n)
 * Space complexity : O(1) or O(n)
 */
class FindDuplicateSort : FindDuplicateNumber {
    override operator fun invoke(nums: IntArray): Int {
        nums.sort()
        for (i in 1 until nums.size) {
            if (nums[i] == nums[i - 1]) {
                return nums[i]
            }
        }
        return 0
    }
}

/**
 * Time complexity : O(n)
 * Space complexity : O(n)
 */
class FindDuplicateSet : FindDuplicateNumber {
    override operator fun invoke(nums: IntArray): Int {
        val seen: MutableSet<Int> = HashSet()
        nums.forEach {
            if (seen.contains(it)) {
                return it
            } else {
                seen.add(it)
            }
        }
        return 0
    }
}

class FindDuplicateArray : FindDuplicateNumber {
    override fun invoke(nums: IntArray): Int {
        // Initialize a count array of size nums.size with zeros
        val cnt = IntArray(nums.size) { 0 }
        var ind = 0
        if (nums.size == 1) {
            return 0
        }

        // Store the count of each value in the count array
        for (i in nums.indices) {
            cnt[nums[i]]++
        }

        for (i in cnt.indices) {
            // If cnt[i] > 1, this means that the element occurs more than once in nums
            // Return i as the duplicate value
            if (cnt[i] > 1) {
                ind = i
                break
            }
        }

        return ind
    }
}

class FindDuplicateMap : FindDuplicateNumber {
    override fun invoke(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        var duplicate = 0

        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }

        for ((key, value) in map) {
            if (value > 1) {
                duplicate = key
                break
            }
        }

        return duplicate
    }
}

class FindDuplicateBS : FindDuplicateNumber {
    override fun invoke(nums: IntArray): Int {
        var low = 0
        var high = nums.size - 1
        if (nums.size == 1) {
            return 0
        }

        while (low <= high) {
            val mid = low + (high - low) / 2
            var cnt = 0

            // Count numbers less than or equal to mid
            for (n in nums) {
                if (n <= mid) {
                    cnt++
                }
            }

            // Binary search on the left
            if (cnt <= mid) {
                low = mid + 1
            } else {
                // Binary search on the right
                high = mid - 1
            }
        }

        return low
    }
}

class FindDuplicateTortoise : FindDuplicateNumber {
    override fun invoke(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size == 1) {
            return 0
        }
        var fast = nums[0]
        var slow = nums[0]

        // Phase 1: Detect intersection point of the two pointers
        do {
            fast = nums[nums[fast]]
            slow = nums[slow]
        } while (fast != slow)

        // Phase 2: Find the entrance to the cycle
        fast = nums[0]

        while (fast != slow) {
            fast = nums[fast]
            slow = nums[slow]
        }

        return slow
    }
}
