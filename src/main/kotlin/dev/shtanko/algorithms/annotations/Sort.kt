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
 * The Sort annotation is used to indicate that a class or function implements a sorting algorithm. Sorting algorithms
 * are used to reorder a collection of elements, such as an array or list, into a specific
 * order (e.g., ascending or descending).
 * This annotation is typically applied to sorting algorithms such as QuickSort, MergeSort, BubbleSort, and others.
 *
 * # How It Works
 *
 * 1: Sorting Algorithms:
 * Sorting algorithms can be categorized into several types based on their performance, approach, and space complexity.
 * Common sorting algorithms include:
 *
 * - **Bubble Sort**: Repeatedly compares and swaps adjacent elements to order the list. Simple but inefficient for
 * large datasets.
 * - **QuickSort**: A divide-and-conquer algorithm that partitions the array around a pivot and recursively sorts
 * the subarrays.
 * - **MergeSort**: A divide-and-conquer algorithm that splits the array into halves, recursively sorts them, and merges
 * them back together.
 * - **Insertion Sort**: Builds a sorted list one element at a time, suitable for small datasets or nearly sorted lists.
 * - **HeapSort**: Uses a binary heap to efficiently sort elements, with good worst-case performance.
 *
 * 2: Example (QuickSort):
 * The following is an implementation of the QuickSort algorithm, which uses a divide-and-conquer strategy to sort
 * an array:
 *
 * ```kotlin
 * fun quickSort(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
 *     if (low < high) {
 *         val pi = partition(arr, low, high)
 *         quickSort(arr, low, pi - 1)
 *         quickSort(arr, pi + 1, high)
 *     }
 * }
 *
 * fun partition(arr: IntArray, low: Int, high: Int): Int {
 *     val pivot = arr[high]
 *     var i = low - 1
 *     for (j in low until high) {
 *         if (arr[j] <= pivot) {
 *             i++
 *             arr[i] = arr[j].also { arr[j] = arr[i] }
 *         }
 *     }
 *     arr[i + 1] = arr[high].also { arr[high] = arr[i + 1] }
 *     return i + 1
 * }
 * ```
 *
 * 3: Example (MergeSort):
 * The following is an implementation of the MergeSort algorithm, which recursively divides the array into halves and
 * merges them:
 *
 * ```kotlin
 * fun mergeSort(arr: IntArray) {
 *     if (arr.size > 1) {
 *         val mid = arr.size / 2
 *         val left = arr.sliceArray(0 until mid)
 *         val right = arr.sliceArray(mid until arr.size)
 *         mergeSort(left)
 *         mergeSort(right)
 *         merge(left, right, arr)
 *     }
 * }
 *
 * fun merge(left: IntArray, right: IntArray, arr: IntArray) {
 *     var i = 0
 *     var j = 0
 *     var k = 0
 *
 *     while (i < left.size && j < right.size) {
 *         if (left[i] <= right[j]) {
 *             arr[k] = left[i]
 *             i++
 *         } else {
 *             arr[k] = right[j]
 *             j++
 *         }
 *         k++
 *     }
 *
 *     while (i < left.size) {
 *         arr[k] = left[i]
 *         i++
 *         k++
 *     }
 *
 *     while (j < right.size) {
 *         arr[k] = right[j]
 *         j++
 *         k++
 *     }
 * }
 * ```
 *
 * # Advantages:
 * * **Improved Efficiency**: Sorting algorithms like QuickSort and MergeSort offer efficient performance with average
 * time complexities of O(n log n), compared to O(n²) for simpler algorithms like Bubble Sort and Insertion Sort.
 * * **Stable Sort**: Some sorting algorithms, like MergeSort, are stable, meaning they maintain the relative order of
 * equal elements.
 * * **Wide Range of Use**: Sorting is a fundamental operation used in numerous applications, including searching,
 * database management, and data visualization.
 *
 * # Considerations:
 * * **Worst-case Performance**: Some sorting algorithms, like QuickSort, have worst-case time complexities of O(n²),
 * although these can be mitigated by using strategies like random pivot selection.
 * * **Space Complexity**: Algorithms like MergeSort require additional space proportional to the size of the array,
 * while algorithms like QuickSort can be more space-efficient
 * (though they may still require extra space for recursion).
 *
 * Sorting is a fundamental and widely used operation in computer science, and different algorithms can be applied
 * based on the specific performance and space requirements of the problem at hand.
 *
 * @property info An optional string that provides additional information about the sorting algorithm or its usage.
 * @constructor Creates a new Sort annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Sort(val info: String = "")
