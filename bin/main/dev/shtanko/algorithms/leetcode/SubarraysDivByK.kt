/*
 * Copyright 2023 Oleksii Shtanko
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
 * 974. Subarray Sums Divisible by K
 * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/">Source</a>
 */
fun interface SubarraysDivByK {
    operator fun invoke(numbers: IntArray, divisor: Int): Int
}

/**
 * # Intuition
 * To determine the number of subarrays divisible by a given number `K`, we can use the concept of prefix sums combined
 * with a hashmap.
 * The idea is that if the sum of elements up to indices `i` and `j` have the same remainder when divided by `K`,
 * then the subarray between `i` and `j` is divisible by `K`. This works because the difference between the sums of
 * elements up to these indices will be a multiple of `K`.
 *
 * # Approach
 * 1. Use a hashmap `remainderCountMap` to store the frequency of each remainder when the cumulative sum is divided
 * by `K`.
 *    Initialize `remainderCountMap` with `{0: 1}` to account for subarrays that themselves are directly divisible by
 *    `K`.
 * 2. Iterate through the array while maintaining a cumulative sum.
 * 3. Calculate the remainder of the cumulative sum modulo `K`. Adjust it to be positive if it is negative
 * (to handle negative numbers).
 * 4. Increment the subarray count by the number of times this remainder has been seen before (using the hashmap).
 * 5. Update the remainder count in the hashmap for the current remainder.
 * 6. Return the total count of subarrays whose sum is divisible by `K`.
 *
 * # Complexity
 * - Time complexity:
 *   The algorithm processes each element of the array exactly once, resulting in a time complexity of $$O(n)$$,
 *   where `n` is the length of the input array.
 *
 * - Space complexity:
 *   The space complexity is $$O(K)$$ in the worst case, as we store the count of each remainder in a hashmap,
 *   which can have at most `K` different keys.
 */
class SubarraysDivisibleByKUsingMap : SubarraysDivByK {
    override operator fun invoke(numbers: IntArray, divisor: Int): Int {
        val remainderCountMap: MutableMap<Int, Int> = HashMap()
        remainderCountMap[0] = 1
        var subarrayCount = 0
        var cumulativeSum = 0
        for (number in numbers) {
            cumulativeSum = (cumulativeSum + number) % divisor
            if (cumulativeSum < 0) cumulativeSum += divisor // Because -1 % 5 = -1, but we need the positive mod
            subarrayCount += remainderCountMap.getOrDefault(cumulativeSum, 0)
            remainderCountMap[cumulativeSum] = remainderCountMap.getOrDefault(cumulativeSum, 0) + 1
        }
        return subarrayCount
    }
}

/**
 * # Intuition
 * To solve the problem of finding subarrays divisible by a given number `K`, we can use the prefix sum technique.
 * The key observation is that if the sum of elements up to indices `i` and `j` have the same remainder when divided
 * by `K`,
 * then the subarray between `i` and `j` is divisible by `K`. This is because the difference between the sums is a
 * multiple of `K`.
 *
 * # Approach
 * 1. Initialize an array `remainderCountArray` to count the occurrences of each possible remainder (from `0` to `K-1`).
 *    Set `remainderCountArray[0]` to `1` to account for subarrays that are themselves directly divisible by `K`.
 * 2. Iterate through the array while maintaining a cumulative sum.
 * 3. Compute the remainder of the cumulative sum with `K`. Adjust it to be positive if necessary
 * (to handle negative numbers).
 * 4. Increment the subarray count by the number of times this remainder has been seen before.
 * 5. Update the remainder count for the current remainder.
 * 6. Finally, return the total count of subarrays whose sum is divisible by `K`.
 *
 * # Complexity
 * - Time complexity:
 *   The algorithm processes each element of the array exactly once, resulting in a time complexity of $$O(n)$$,
 *   where `n` is the length of the input array.
 *
 * - Space complexity:
 *   The space complexity is $$O(K)$$, as we use an array of size `K` to store the count of each remainder.
 */
class SubarraysDivisibleByKUsingPrefixSum : SubarraysDivByK {
    override operator fun invoke(numbers: IntArray, divisor: Int): Int {
        if (numbers.isEmpty()) return 0
        val remainderCountArray = IntArray(divisor)
        remainderCountArray[0] = 1
        var subarrayCount = 0
        var cumulativeSum = 0
        for (number in numbers) {
            cumulativeSum = (cumulativeSum + number) % divisor
            if (cumulativeSum < 0) cumulativeSum += divisor // Because -1 % 5 = -1, but we need the positive mod 4
            subarrayCount += remainderCountArray[cumulativeSum]
            remainderCountArray[cumulativeSum]++
        }
        return subarrayCount
    }
}
