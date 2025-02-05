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

package dev.shtanko.algorithms.leetcode

import java.util.TreeMap

/**
 * 2418. Sort the People
 * @see <a href="https://leetcode.com/problems/sort-the-people">Source</a>
 */
fun interface SortThePeople {
    operator fun invoke(names: Array<String>, heights: IntArray): Array<String>
}

class SortThePeopleZip : SortThePeople {
    override fun invoke(names: Array<String>, heights: IntArray): Array<String> {
        return names.zip(heights.asList())
            .sortedByDescending { (_, height) -> height }
            .map { (name, _) -> name }
            .toTypedArray()
    }
}

class SortThePeopleMap : SortThePeople {
    override fun invoke(names: Array<String>, heights: IntArray): Array<String> {
        val numberOfPeople = names.size
        val heightToNameMap: MutableMap<Int, String> = mutableMapOf()
        for (i in 0 until numberOfPeople) {
            heightToNameMap[heights[i]] = names[i]
        }
        heights.sort()
        val sortedNames = Array(numberOfPeople) { "" }
        for (i in 0 until numberOfPeople) {
            sortedNames[i] = heightToNameMap[heights[numberOfPeople - i - 1]]!!
        }
        return sortedNames
    }
}

class SortThePeopleTreeMap : SortThePeople {
    override fun invoke(names: Array<String>, heights: IntArray): Array<String> {
        val namesMap = TreeMap<Int, String>()

        heights.forEachIndexed { index, height ->
            namesMap.put(height, names[index])
        }

        return namesMap.values.reversed().toTypedArray()
    }
}

class SortThePeopleSortPermutation : SortThePeople {
    override fun invoke(names: Array<String>, heights: IntArray): Array<String> {
        val numberOfPeople = names.size
        val sortedIndices = Array(numberOfPeople) { it }
        sortedIndices.sortByDescending { heights[it] }
        val sortedNames = Array(numberOfPeople) { "" }
        for (i in sortedIndices.indices) {
            sortedNames[i] = names[sortedIndices[i]]
        }

        return sortedNames
    }
}

class SortThePeopleQuickSort : SortThePeople {
    override fun invoke(names: Array<String>, heights: IntArray): Array<String> {
        quickSort(heights, names, 0, heights.size - 1)
        return names
    }

    private fun swap(heights: IntArray, names: Array<String>, index1: Int, index2: Int) {
        // Swap heights
        val tempHeight = heights[index1]
        heights[index1] = heights[index2]
        heights[index2] = tempHeight

        // Swap corresponding names
        val tempName = names[index1]
        names[index1] = names[index2]
        names[index2] = tempName
    }

    private fun partition(heights: IntArray, names: Array<String>, start: Int, end: Int): Int {
        val pivot = heights[end]
        var i = start - 1

        for (j in start until end) {
            // If current element is greater than or equal to pivot
            if (heights[j] >= pivot) {
                i++
                swap(heights, names, i, j)
            }
        }
        // Place the pivot in its correct position
        swap(heights, names, i + 1, end)
        return i + 1
    }

    private fun quickSort(heights: IntArray, names: Array<String>, start: Int, end: Int) {
        if (start < end) {
            // Find the partition index
            val partitionIndex = partition(heights, names, start, end)

            // Recursively sort the left and right sub-arrays
            quickSort(heights, names, start, partitionIndex - 1)
            quickSort(heights, names, partitionIndex + 1, end)
        }
    }
}

class SortThePeopleMergeSort : SortThePeople {
    override fun invoke(names: Array<String>, heights: IntArray): Array<String> {
        mergeSort(names, heights, 0, heights.size - 1)
        return names
    }

    private fun mergeSort(names: Array<String>, heights: IntArray, start: Int, end: Int) {
        if (start < end) {
            val mid = start + (end - start) / 2
            mergeSort(names, heights, start, mid)
            mergeSort(names, heights, mid + 1, end)
            merge(names, heights, start, mid, end)
        }
    }

    private fun merge(names: Array<String>, heights: IntArray, start: Int, mid: Int, end: Int) {
        val leftSize = mid - start + 1
        val rightSize = end - mid

        // Create temporary arrays
        val leftHeights = IntArray(leftSize)
        val rightHeights = IntArray(rightSize)
        val leftNames = Array(leftSize) { "" }
        val rightNames = Array(rightSize) { "" }

        // Copy data to temporary arrays
        for (i in 0 until leftSize) {
            leftHeights[i] = heights[start + i]
            leftNames[i] = names[start + i]
        }
        for (j in 0 until rightSize) {
            rightHeights[j] = heights[mid + 1 + j]
            rightNames[j] = names[mid + 1 + j]
        }

        // Merge the temporary arrays
        var leftIndex = 0
        var rightIndex = 0
        var mergeIndex = start
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftHeights[leftIndex] >= rightHeights[rightIndex]) { // Descending order
                heights[mergeIndex] = leftHeights[leftIndex]
                names[mergeIndex] = leftNames[leftIndex]
                leftIndex++
            } else {
                heights[mergeIndex] = rightHeights[rightIndex]
                names[mergeIndex] = rightNames[rightIndex]
                rightIndex++
            }
            mergeIndex++
        }

        // Copy remaining elements of leftHeights[], if any
        while (leftIndex < leftSize) {
            heights[mergeIndex] = leftHeights[leftIndex]
            names[mergeIndex] = leftNames[leftIndex]
            leftIndex++
            mergeIndex++
        }

        // Copy remaining elements of rightHeights[], if any
        while (rightIndex < rightSize) {
            heights[mergeIndex] = rightHeights[rightIndex]
            names[mergeIndex] = rightNames[rightIndex]
            rightIndex++
            mergeIndex++
        }
    }
}
