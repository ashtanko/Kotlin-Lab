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

fun interface SubarraySumStrategy {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class SubarraySumBruteForce : SubarraySumStrategy {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        var count = 0
        for (start in nums.indices) {
            for (end in start + 1..nums.size) {
                var sum = 0
                for (i in start until end) sum += nums[i]
                if (sum == k) count++
            }
        }
        return count
    }
}

class SubarraySumUsingCumulativeSum : SubarraySumStrategy {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        var count = 0
        val sum = IntArray(nums.size + 1)
        sum[0] = 0
        for (i in 1..nums.size) sum[i] = sum[i - 1] + nums[i - 1]
        for (start in nums.indices) {
            for (end in start + 1..nums.size) {
                if (sum[end] - sum[start] == k) count++
            }
        }
        return count
    }
}

class SubarraySumWithoutSpace : SubarraySumStrategy {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        var count = 0
        for (start in nums.indices) {
            var sum = 0
            for (end in start until nums.size) {
                sum += nums[end]
                if (sum == k) count++
            }
        }
        return count
    }
}

class SubarraySumUsingHashmap : SubarraySumStrategy {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        var count = 0
        var sum = 0
        val map: HashMap<Int, Int> = HashMap()
        map[0] = 1
        for (element in nums) {
            sum += element
            if (map.containsKey(sum - k)) count += map.getOrDefault(sum - k, 0)
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        return count
    }
}
