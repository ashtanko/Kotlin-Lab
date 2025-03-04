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
 * The Binary Search algorithm is a highly efficient method for finding the position of a target element in a
 * sorted array. It works by repeatedly dividing the search interval in half, making it much faster than linear search
 * for large datasets.
 *
 * # How It Works
 *
 * 1: Initialization:
 * Define the search range with two pointers, `low` and `high`, representing the start and end of the array.
 * Example:
 * ```kotlin
 * var low = 0
 * var high = arr.size - 1
 * ```
 *
 * 2: Iterative or Recursive Search:
 * Compute the middle index and compare the middle element with the target:
 * - If the middle element equals the target, the search is complete.
 * - If the middle element is greater than the target, reduce the search range to the left half.
 * - If the middle element is less than the target, reduce the search range to the right half.
 *
 * Repeat until the target is found or the search range becomes empty.
 * Example:
 * ```kotlin
 * while (low <= high) {
 *     val mid = low + (high - low) / 2
 *     when {
 *         arr[mid] == target -> return mid
 *         arr[mid] < target -> low = mid + 1
 *         else -> high = mid - 1
 *     }
 * }
 * return -1 // Target not found
 * ```
 *
 * 3: Recursive Implementation (Optional):
 * Binary Search can also be implemented recursively by updating the search range in each recursive call.
 * Example:
 * ```kotlin
 * fun binarySearch(arr: IntArray, low: Int, high: Int, target: Int): Int {
 *     if (low > high) return -1
 *     val mid = low + (high - low) / 2
 *     return when {
 *         arr[mid] == target -> mid
 *         arr[mid] < target -> binarySearch(arr, mid + 1, high, target)
 *         else -> binarySearch(arr, low, mid - 1, target)
 *     }
 * }
 * ```
 *
 * # Advantages:
 * * Efficiency: Binary Search has a time complexity of O(log n), making it significantly faster than linear search
 *   for large datasets.
 * * Simplicity: The algorithm is straightforward to implement and requires minimal additional memory.
 *
 * # Considerations:
 * * Sorted Input: Binary Search requires the input array to be sorted. If the array is unsorted, sorting it beforehand
 *   will add an O(n log n) preprocessing step.
 * * Applicability: The algorithm is only suitable for datasets that allow random access, such as arrays. For linked
 *   lists or similar structures, alternative methods are preferred.
 *
 * Binary Search is a versatile algorithm with applications in searching, finding boundaries, and solving optimization
 * problems. Its efficiency and simplicity make it a fundamental tool in computer science.
 *
 * @property info An optional string that provides additional information about the Binary Search algorithm
 * implementation or usage.
 * @constructor Creates a new BinarySearch annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BinarySearch(val info: String = "")
