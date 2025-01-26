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

import dev.shtanko.algorithms.DECIMAL
import kotlin.math.abs

private const val ARR_MAX_SIZE = 101

/**
 * 1051. Height Checker
 * @see <a href="https://leetcode.com/problems/height-checker/">Source</a>
 */
fun interface HeightChecker {
    operator fun invoke(heights: IntArray): Int
}

class HeightCheckerBubbleSort : HeightChecker {
    override fun invoke(heights: IntArray): Int {
        // Sort the array using bubble sort.
        val sortedHeights = heights.clone()
        bubbleSort(sortedHeights)

        var count = 0

        // Loop through the original and sorted arrays.
        for (i in sortedHeights.indices) {
            // Increment count if elements at the same index differ.
            if (heights[i] != sortedHeights[i]) {
                count += 1
            }
        }

        // Return the total count of differing elements.
        return count
    }

    // Function to perform bubble sort on the input array.
    private fun bubbleSort(arr: IntArray) {
        val n = arr.size
        // Loop through the array for bubble sort passes.
        for (i in 0 until n - 1) {
            // Inner loop to compare and swap elements.
            for (j in 0 until n - i - 1) {
                // Compare and swap if elements are in the wrong order.
                if (arr[j] > arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
    }
}

class HeightCheckerStdSort : HeightChecker {
    override fun invoke(heights: IntArray): Int {
        var count = 0
        val copy = heights.clone()
        copy.sort()
        for (i in copy.indices) {
            if (heights[i] != copy[i]) {
                count++
            }
        }
        return count
    }
}

class HeightCheckerMergeSort : HeightChecker {
    override fun invoke(heights: IntArray): Int {
        // Sort the array using merge sort.
        val sortedHeights = heights.clone()
        val tempArray = IntArray(heights.size)
        mergeSort(sortedHeights, 0, sortedHeights.size - 1, tempArray)

        var count = 0

        // Loop through the original and sorted arrays.
        for (i in sortedHeights.indices) {
            // Increment count if elements at the same index differ.
            if (heights[i] != sortedHeights[i]) {
                count += 1
            }
        }

        // Return the total count of differing elements.
        return count
    }

    // Function to merge two sub-arrays in sorted order.
    private fun merge(arr: IntArray, left: Int, mid: Int, right: Int, tempArr: IntArray) {
        // Calculate the start and sizes of two halves.
        val start1 = left
        val start2 = mid + 1
        val n1 = mid - left + 1
        val n2 = right - mid

        // Copy elements of both halves into a temporary array.
        for (i in 0 until n1) {
            tempArr[start1 + i] = arr[start1 + i]
        }
        for (i in 0 until n2) {
            tempArr[start2 + i] = arr[start2 + i]
        }

        // Merge the sub-arrays in 'tempArray' back into the original array 'arr' in
        // sorted order.
        var i = 0
        var j = 0
        var k = left
        while (i < n1 && j < n2) {
            if (tempArr[start1 + i] <= tempArr[start2 + j]) {
                arr[k] = tempArr[start1 + i]
                i += 1
            } else {
                arr[k] = tempArr[start2 + j]
                j += 1
            }
            k += 1
        }

        // Copy remaining elements
        while (i < n1) {
            arr[k] = tempArr[start1 + i]
            i += 1
            k += 1
        }
        while (j < n2) {
            arr[k] = tempArr[start2 + j]
            j += 1
            k += 1
        }
    }

    // Recursive function to sort an array using merge sort.
    private fun mergeSort(arr: IntArray, left: Int, right: Int, tempArr: IntArray) {
        if (left >= right) {
            return
        }
        val mid = (left + right) / 2
        // Sort first and second halves recursively.
        mergeSort(arr, left, mid, tempArr)
        mergeSort(arr, mid + 1, right, tempArr)
        // Merge the sorted halves.
        merge(arr, left, mid, right, tempArr)
    }
}

class HeightCheckerHeapSort : HeightChecker {
    override fun invoke(heights: IntArray): Int {
        // Sort the array using heap sort.
        val sortedHeights = heights.clone()
        heapSort(sortedHeights)

        var count = 0

        // Loop through the original and sorted arrays.
        for (i in sortedHeights.indices) {
            // Increment count if elements at the same index differ.
            if (heights[i] != sortedHeights[i]) {
                count += 1
            }
        }

        // Return the total count of differing elements.
        return count
    }

    // Function to max heapify a subtree (in top-down order) rooted at index i.
    private fun heapify(arr: IntArray, n: Int, i: Int) {
        // Initialize largest as root 'i'.
        var largest = i
        val left = 2 * i + 1
        val right = 2 * i + 2

        // If the left child is larger than the root.
        if (left < n && arr[left] > arr[largest]) {
            largest = left
        }

        // If the right child is larger than the largest so far.
        if (right < n && arr[right] > arr[largest]) {
            largest = right
        }

        // If largest is not root swap root with the largest element
        // Recursively heapify the affected sub-tree (i.e. move down).
        if (largest != i) {
            val swap = arr[i]
            arr[i] = arr[largest]
            arr[largest] = swap
            heapify(arr, n, largest)
        }
    }

    private fun heapSort(arr: IntArray) {
        val n = arr.size
        // Build heap; heapify all elements except leaf nodes, in bottom-up order.
        for (i in n / 2 - 1 downTo 0) {
            heapify(arr, n, i)
        }

        // Traverse elements one by one, to move the current root to the end, and
        for (i in n - 1 downTo 0) {
            val swap = arr[0]
            arr[0] = arr[i]
            arr[i] = swap
            // call max heapify on the reduced array.
            heapify(arr, i, 0)
        }
    }
}

class HeightCheckerCountingSort : HeightChecker {
    override fun invoke(heights: IntArray): Int {
        // Sort the array using counting sort.
        val sortedHeights = heights.clone()
        countingSort(sortedHeights)

        var count = 0

        // Loop through the original and sorted arrays.
        for (i in sortedHeights.indices) {
            // Increment count if elements at the same index differ.
            if (heights[i] != sortedHeights[i]) {
                count += 1
            }
        }

        // Return the total count of differing elements.
        return count
    }

    private fun countingSort(arr: IntArray) {
        // Create the counting hash map.
        val counts: MutableMap<Int, Int> = HashMap()
        // Find the minimum and maximum values in the array.
        val minVal = arr.minOrNull() ?: 0
        val maxVal = arr.maxOrNull() ?: 0

        // Update element's count in the hash map.
        for (`val` in arr) {
            counts[`val`] = counts.getOrDefault(`val`, 0) + 1
        }

        var index = 0
        // Place each element in its correct position in the array.
        for (value in minVal..maxVal) {
            // Append all 'val's together if they exist.
            while (counts.getOrDefault(value, 0) > 0) {
                arr[index] = value
                index += 1
                counts[value] = counts.getOrDefault(value, 0) - 1
            }
        }
    }
}

class HeightCheckerRadixSort : HeightChecker {
    override fun invoke(heights: IntArray): Int {
        // Sort the array using radix sort.
        val sortedHeights = heights.clone()
        radixSort(sortedHeights)

        var count = 0

        // Loop through the original and sorted arrays.
        for (i in sortedHeights.indices) {
            // Increment count if elements at the same index differ.
            if (heights[i] != sortedHeights[i]) {
                count += 1
            }
        }

        // Return the total count of differing elements.
        return count
    }

    // Bucket sort function for each place value digit.
    private fun bucketSort(arr: IntArray, placeValue: Int) {
        val buckets: MutableList<MutableList<Int>> = ArrayList(DECIMAL)
        for (i in 0..<DECIMAL) {
            buckets.add(ArrayList())
        }

        // Store the respective number based on its digit.
        for (`val`: Int in arr) {
            var digit: Int = (abs(`val`.toDouble()) / placeValue).toInt()
            digit %= DECIMAL
            buckets[digit].add(`val`)
        }

        // Overwrite 'arr' in sorted order of current place digits.
        var index: Int = 0
        for (digit in 0..<DECIMAL) {
            for (value: Int in buckets[digit]) {
                arr[index] = value
                index++
            }
        }
    }

    // Radix sort function.
    private fun radixSort(arr: IntArray) {
        // Find the absolute maximum element to find max number of digits.
        var maxElement: Int = arr.maxOfOrNull { a: Int ->
            abs(a)
        } ?: 0
        var maxDigits: Int = 0
        while (maxElement > 0) {
            maxDigits += 1
            maxElement /= DECIMAL
        }

        // Radix sort, least significant digit place to most significant.
        var placeValue: Int = 1
        for (digit in 0 until maxDigits) {
            bucketSort(arr, placeValue)
            placeValue *= DECIMAL
        }
    }
}

class HeightCheckerIterative : HeightChecker {
    override fun invoke(heights: IntArray): Int {
        val heightToFreq = IntArray(ARR_MAX_SIZE)
        for (height in heights) {
            heightToFreq[height]++
        }
        var result = 0
        var curHeight = 0
        for (i in heights.indices) {
            while (heightToFreq[curHeight] == 0) {
                curHeight++
            }
            if (curHeight != heights[i]) {
                result++
            }
            heightToFreq[curHeight]--
        }
        return result
    }
}
