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
 * The Suffix Sum approach is a technique used to efficiently compute the sum of elements in a subarray starting from a
 * given index to the end of the array. It is particularly useful for answering multiple range sum queries quickly,
 * similar to the Prefix Sum approach, but with the sum computed from the end of the array rather than the beginning.
 *
 * # How It Works
 *
 * 1: Preprocessing:
 * Construct an auxiliary array called the suffix sum array, where each element at index i represents the sum of the
 * array elements from index i to the end of the array.
 * Given an array arr of length n, the suffix sum array suffixSum is defined as:
 * ```kotlin
 * suffixSum[i] = arr[i] + arr[i + 1] + ... + arr[n - 1]
 * ```
 *
 * The suffix sum array is built using a loop:
 * ```kotlin
 * suffixSum[n - 1] = arr[n - 1]
 * for (i in n - 2 downTo 0) {
 *     suffixSum[i] = suffixSum[i + 1] + arr[i]
 * }
 * ```
 *
 * 2: Querying the Sum of a Subarray:
 * To find the sum of elements between indices left and right (inclusive) in the original array, use the suffix sum
 * array:
 * ```kotlin
 * sum(left, right) = suffixSum[left] - suffixSum[right + 1]
 * ```
 * If right is n - 1 (the last index), then:
 * ```kotlin
 * sum(left, n - 1) = suffixSum[left]
 * ```
 *
 * # Advantages:
 * * Efficient Queries: Once the suffix sum array is built, querying the sum of any subarray starting from index left
 * to right is done in constant time, `O(1)`.
 * * Preprocessing Time: Building the suffix sum array takes linear time, O(n).
 *
 * The Suffix Sum approach is particularly useful when you need to perform multiple range sum queries on an array
 * starting from a given index, as it allows for quick query times after an initial linear preprocessing step.
 *
 * @property info An optional string that provides additional information about the suffix sum algorithm or its usage.
 * @constructor Creates a new SuffixSum annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class SuffixSum(val info: String = "")
