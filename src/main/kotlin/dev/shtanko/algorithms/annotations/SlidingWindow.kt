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
 * The SlidingWindow annotation is used to indicate that a class or function implements an algorithm that utilizes
 * the sliding window technique. The sliding window is an optimization technique that is particularly useful for
 * problems involving subarrays, substrings, or continuous sequences within an array or list, where you need to perform
 * operations over a moving window of elements.
 *
 * # How It Works
 *
 * 1: Sliding Window Technique:
 * The sliding window technique involves creating a "window" over a subset of elements in an array or list. This window
 * moves across the data structure, typically one element at a time, and computes or updates values based on the
 * elements within the window.
 *
 * The window can be:
 * - A fixed-size window: where the size of the window does not change.
 * - A dynamic window: where the window size can grow or shrink based on certain conditions
 * (e.g., maintaining a valid range).
 *
 * 2: Example (Fixed-Size Sliding Window: Maximum Sum of Subarray of Size k):
 * The following function finds the maximum sum of a subarray of a fixed size using the sliding window technique:
 *
 * ```kotlin
 * fun maxSum(arr: IntArray, k: Int): Int {
 *     var maxSum = 0
 *     var windowSum = 0
 *     for (i in 0 until k) {
 *         windowSum += arr[i]
 *     }
 *     maxSum = windowSum
 *
 *     for (i in k until arr.size) {
 *         windowSum += arr[i] - arr[i - k]
 *         maxSum = maxOf(maxSum, windowSum)
 *     }
 *
 *     return maxSum
 * }
 * ```
 *
 * 3: Dynamic Sliding Window (Variable Window Size):
 * In problems where the window size can change based on specific conditions, such as maintaining a valid range of
 * elements
 * in a subarray, the sliding window adjusts its boundaries dynamically.
 *
 * Example (Dynamic Window: Longest Substring with K Distinct Characters):
 * ```kotlin
 * fun longestSubstring(s: String, k: Int): Int {
 *     val map = mutableMapOf<Char, Int>()
 *     var left = 0
 *     var maxLength = 0
 *
 *     for (right in s.indices) {
 *         map[s[right]] = map.getOrDefault(s[right], 0) + 1
 *
 *         while (map.size > k) {
 *             map[s[left]] = map[s[left]]!! - 1
 *             if (map[s[left]] == 0) map.remove(s[left])
 *             left++
 *         }
 *
 *         maxLength = maxOf(maxLength, right - left + 1)
 *     }
 *
 *     return maxLength
 * }
 * ```
 *
 * # Advantages:
 * * **Time Efficiency**: Sliding window algorithms can significantly reduce time complexity compared to brute-force
 * solutions by avoiding redundant calculations. For example, moving the window one element at a time is much more
 * efficient than recalculating values for every possible subarray.
 * * **Space Efficiency**: Sliding window algorithms typically require only a constant amount of extra space, making
 * them memory efficient.
 * * **Versatility**: The sliding window technique is applicable to a wide range of problems involving continuous
 * sequences, such as finding sums, maximums, minimums, and substring problems.
 *
 * # Considerations:
 * * **Problem Constraints**: Sliding window techniques are most useful when the problem involves sequences or
 * subarrays, and may not be applicable to all types of problems.
 * * **Edge Cases**: It's important to handle edge cases, such as an empty array or invalid window sizes, properly in
 * sliding window implementations.
 *
 * The sliding window technique is a powerful optimization that can simplify and improve the performance of algorithms
 * that need to process continuous subsequences in linear time.
 *
 * @property info An optional string that provides additional information about the sliding window algorithm or its
 * usage.
 * @constructor Creates a new SlidingWindow annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SlidingWindow(val info: String = "")
