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

package dev.shtanko.algorithms.annotations

/**
 * The TwoPointers annotation is used to indicate that a function or class implements the two-pointer technique.
 * The two-pointer technique is a common approach for solving problems that involve arrays or lists,
 * where two pointers (or indices) are used to traverse the data structure, often to find pairs or subarrays that meet
 * a specific condition.
 *
 * # How It Works
 *
 * The two-pointer technique involves using two pointers, usually initialized at opposite ends of an array or list,
 * and moving them toward each other (or in some cases, in the same direction). The pointers can be adjusted based
 * on certain conditions to efficiently solve problems like finding pairs, subarrays, or performing certain
 * optimizations.
 *
 * The typical steps include:
 * 1. **Initialization**: Set the two pointers at the start (or end) of the array.
 * 2. **Condition Check**: Move the pointers based on a given
 * condition (e.g., sum, difference, or distance between elements).
 * 3. **Termination**: The algorithm ends when the pointers meet, or when a certain condition is satisfied.
 *
 * Example of using the two-pointer technique to find pairs in a sorted array that sum to a target value:
 *
 * ```kotlin
 * fun twoSum(nums: IntArray, target: Int): Pair<Int, Int>? {
 *     var left = 0
 *     var right = nums.size - 1
 *     while (left < right) {
 *         val sum = nums[left] + nums[right]
 *         when {
 *             sum == target -> return Pair(nums[left], nums[right])
 *             sum < target -> left++ // Move left pointer to the right to increase the sum
 *             else -> right-- // Move right pointer to the left to decrease the sum
 *         }
 *     }
 *     return null
 * }
 * ```
 *
 * In this example, the two pointers `left` and `right` are moved toward each other based on the sum of the elements
 * at those positions.
 * The algorithm is efficient because it reduces the complexity of searching for pairs from O(n^2) to O(n).
 *
 * # Advantages:
 * * **Efficient Time Complexity**: The two-pointer technique often reduces the time complexity of problems that would
 * otherwise require nested loops.
 * * **Space Efficiency**: It typically uses constant space, making it a space-efficient solution compared to other
 * techniques that require extra memory.
 * * **Versatility**: The two-pointer technique is applicable to a wide range of problems, including array partitioning,
 * searching for specific conditions, and optimization problems.
 *
 * # Disadvantages:
 * * **Limited to Certain Problem Types**: The two-pointer technique is most effective when the problem involves sorting
 * or searching through a data structure like an array or list.
 * * **Requires Sorted Data**: For many problems, the array or list needs to be sorted beforehand, which may add to the
 * computational cost.
 *
 * The two-pointer technique is particularly useful for problems related to finding pairs or subarrays that satisfy
 * certain conditions, such as in problems related to sums, differences, or distances.
 *
 * @property info An optional string that provides additional information about the two-pointer technique
 * implementation or its usage.
 * @constructor Creates a new TwoPointers annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class TwoPointers(val info: String = "")
