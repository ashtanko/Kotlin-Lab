/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.annotations

/**
 * The Prefix Sum approach is a technique used to efficiently compute the sum of elements in a subarray of an array.
 * It is particularly useful for answering multiple range sum queries quickly after an initial preprocessing step.
 *
 * # How It Works
 *
 * 1: Preprocessing:
 * Construct an auxiliary array called the prefix sum array, where each element at index i represents the sum of the
 * array elements from the start up to index i.
 * Given an array arr of length n, the prefix sum array prefixSum is defined as:
 * ``` kotlin
 * prefixSum[i] = arr[0] + arr[1] + ... + arr[i]
 * ```
 *
 * The prefix sum array is built using a loop:
 *```kotlin
 * prefixSum[0] = arr[0]
 * for (i in 1 until n) {
 *     prefixSum[i] = prefixSum[i - 1] + arr[i]
 * }
 * ```
 * 2: Querying the Sum of a Subarray:
 * To find the sum of elements between indices left and right (inclusive) in the original array, use the prefix sum
 * array:
 * ```kotlin
 * sum(left, right) = prefixSum[right] - prefixSum[left - 1]
 *```
 * If left is 0, then:
 *```kotlin
 * sum(0, right) = prefixSum[right]
 * ```
 *
 * # Advantages:
 * * Efficient Queries: Once the prefix sum array is built, querying the sum of any subarray is done in
 * constant time, `O(1)`.
 * * Preprocessing Time: Building the prefix sum array takes linear time, O(n).
 *
 * The Prefix Sum approach is particularly useful when you need to perform multiple range sum queries on an array, as it
 * allows for quick query times after an initial linear preprocessing step.
 *
 * @property info An optional string that provides additional information about the prefix sum algorithm implementation
 * or usage.
 * @constructor Creates a new PrefixSum annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class PrefixSum(val info: String = "")
