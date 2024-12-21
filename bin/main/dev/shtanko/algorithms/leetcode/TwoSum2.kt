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

/**
 * This function is an implementation of the Two Sum II problem from LeetCode.
 * It takes an array of integers and a target integer as input.
 * The function returns an array of two integers, which are the 1-indexed positions of the two numbers in the input
 * array that add up to the target.
 * The input array is assumed to be sorted in ascending order, and only one valid answer exists.
 *
 * @param target The target integer.
 * @return An array of two integers, which are the 1-indexed positions of the two numbers in the input array that add
 * up to the target.
 */
fun IntArray.twoSum2(target: Int): IntArray {
    // Initialize two pointers, one at the start of the array and one at the end.
    var start = 0
    var end = size - 1

    // While the start pointer is less than the end pointer...
    while (start < end) {
        // If the sum of the numbers at the start and end pointers equals the target, break the loop.
        if (this[start] + this[end] == target) {
            break
        }
        // If the sum of the numbers at the start and end pointers is less than the target, increment the start pointer.
        if (this[start] + this[end] < target) {
            start++
        } else {
            // Otherwise, decrement the end pointer.
            end--
        }
    }

    // Return an array of the 1-indexed positions of the start and end pointers.
    return intArrayOf(start + 1, end + 1)
}
