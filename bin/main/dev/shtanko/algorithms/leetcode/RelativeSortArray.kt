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

import java.util.TreeMap

private const val MAX_ARRAY_SIZE = 1001

/**
 * 1122. Relative Sort Array
 * @see <a href="https://leetcode.com/problems/relative-sort-array/">Source</a>
 */
fun interface RelativeSortArray {
    operator fun invoke(arr1: IntArray, arr2: IntArray): IntArray
}

/**
 * # Intuition
 * The problem requires sorting one array (arr1) according to the order defined by another array (arr2).
 * Elements not in arr2 should be appended at the end in ascending order. This suggests using a counting
 * mechanism to track occurrences and sorting for the remaining elements.
 *
 * # Approach
 * 1. Create a count array to track the frequency of each element in arr1.
 * 2. Traverse arr2, adding elements to the result list based on their frequency in the count array.
 * 3. Append remaining elements in ascending order by traversing the count array.
 * 4. Convert the result list to an integer array and return it.
 *
 * # Complexity
 * - Time complexity:
 * The time complexity is $$O(n + m + k \log k)$$ where $$n$$ is the length of arr1, $$m$$ is the length
 * of arr2, and $$k$$ is the range of numbers in arr1. Counting occurrences is $$O(n)$$, processing
 * elements from arr2 is $$O(m)$$, and sorting remaining elements (up to $$k$$ elements) is $$O(k \log k)$$.
 *
 * - Space complexity:
 * The space complexity is $$O(k)$$ due to the count array and $$O(n)$$ for the result list, resulting in
 * $$O(n + k)$$ overall.
 */
class RelativeSortArrayTwoLoops : RelativeSortArray {
    override fun invoke(arr1: IntArray, arr2: IntArray): IntArray {
        val result = mutableListOf<Int>()

        // Traverse through the relative order array
        for (i in arr2.indices) {
            // Traverse through the target array
            for (j in arr1.indices) {
                // If element in target array matches with relative order element
                if (arr1[j] == arr2[i]) {
                    // Add it to the result array
                    result.add(arr1[j])
                    // Mark the element in target array as visited
                    arr1[j] = -1
                }
            }
        }

        // Sort the remaining elements in the target array
        arr1.sort()
        // Add the remaining elements to the result array
        for (i in arr1.indices) {
            if (arr1[i] != -1) {
                result.add(arr1[i])
            }
        }

        // Convert ArrayList to array
        return result.toIntArray()
    }
}

/**
 * # Intuition
 * The problem requires sorting one array (arr1) according to the order defined by another array (arr2).
 * Elements not in arr2 should be appended at the end in ascending order. This suggests using a counting
 * mechanism to track occurrences and sorting for the remaining elements.
 *
 * # Approach
 * 1. Create a count map to track the frequency of each element in arr1 that appears in arr2.
 * 2. Traverse arr1, populating the count map and collecting elements not present in arr2.
 * 3. Sort the remaining elements.
 * 4. Construct the result array by adding elements from arr2 based on their frequency in the count map,
 *    followed by the remaining elements in sorted order.
 * 5. Convert the result list to an integer array and return it.
 *
 * # Complexity
 * - Time complexity:
 * The time complexity is $$O(n \log n + m)$$ where $$n$$ is the length of arr1 and $$m$$ is the length of arr2.
 * Counting occurrences is $$O(n)$$, sorting remaining elements is $$O(n \log n)$$, and constructing the result
 * array is $$O(n + m)$$.
 *
 * - Space complexity:
 * The space complexity is $$O(n + m)$$ due to the count map, the remaining list, and the result list.
 */
class RelativeSortArrayHashMap : RelativeSortArray {
    override fun invoke(arr1: IntArray, arr2: IntArray): IntArray {
        val countMap = mutableMapOf<Int, Int>()
        val remaining = mutableListOf<Int>()
        val result = mutableListOf<Int>()

        // Initialize count map with relative order elements
        for (value in arr2) {
            countMap[value] = 0
        }

        // Count occurrences of elements in target array
        for (value in arr1) {
            if (countMap.containsKey(value)) {
                countMap[value] = countMap.getOrDefault(value, 0) + 1
            } else {
                remaining.add(value)
            }
        }

        // Sort the remaining elements
        remaining.sort()

        // Add elements as per relative order
        for (value in arr2) {
            repeat(countMap.getOrDefault(value, 0)) {
                result.add(value)
            }
        }

        // Add remaining elements
        result.addAll(remaining)

        // Convert ArrayList to array
        return result.toIntArray()
    }
}

/**
 * # Intuition
 * To sort `arr1` according to the order defined by `arr2`, we can leverage the idea of counting the frequency of each
 * element in `arr1`.
 * By using a counting sort approach, we can efficiently sort the elements of `arr1` that are in `arr2` and append the
 * remaining elements in ascending order.
 *
 * # Approach
 * 1. Determine the maximum element in `arr1` to size the count array appropriately.
 * 2. Create a count array to store the frequency of each element in `arr1`.
 * 3. Iterate through `arr1` to populate the count array.
 * 4. Construct the result list by adding elements from `arr2` based on their frequency in the count array.
 * 5. Append the remaining elements from the count array in ascending order.
 * 6. Convert the result list to an integer array and return it.
 *
 * # Complexity
 * - Time complexity:
 * The time complexity is $$O(n + m + k)$$ where $$n$$ is the length of `arr1`, $$m$$ is the length of `arr2`, and $$k$$
 * is the range of numbers in `arr1` (maxElement). Counting occurrences is $$O(n)$$, constructing the result array by
 * traversing `arr2` is $$O(m)$$, and appending remaining elements is $$O(k)$$.
 *
 * - Space complexity:
 * The space complexity is $$O(k)$$ due to the count array, and $$O(n)$$ for the result list, resulting in $$O(n + k)$$
 * overall.
 */
class RelativeSortArrayCountingSort : RelativeSortArray {
    override fun invoke(arr1: IntArray, arr2: IntArray): IntArray {
        val maxElement = arr1.maxOrNull() ?: 0
        val count = IntArray(maxElement + 1)

        // Count occurrences of each element
        for (element in arr1) {
            count[element]++
        }

        val result = mutableListOf<Int>()

        // Add elements as per relative order
        for (value in arr2) {
            while (count[value] > 0) {
                result.add(value)
                count[value]--
            }
        }

        // Add remaining elements in ascending order
        for (num in 0..maxElement) {
            while (count[num] > 0) {
                result.add(num)
                count[num]--
            }
        }

        // Convert ArrayList to array
        return result.toIntArray()
    }
}

/**
 * # Intuition
 * The problem requires sorting an array `arr1` based on the order defined by another array `arr2`. Elements not in
 * `arr2` should be appended at the end in ascending order. Using a counting sort approach is efficient because it
 * allows us to count occurrences and place elements in the desired order with minimal additional sorting.
 *
 * # Approach
 * 1. Create a count array `cnt` to track the frequency of each element in `arr1`. The size of this array should be
 * large enough to accommodate the maximum possible value in `arr1`.
 * 2. Iterate through `arr1` to populate the count array with the frequencies of each element.
 * 3. Traverse `arr2` and use the count array to place the elements in the correct order in `arr1`.
 * 4. For elements not in `arr2`, append them to `arr1` in ascending order using the count array.
 * 5. Return the modified `arr1`.
 *
 * # Complexity
 * - Time complexity:
 * The time complexity is $$O(n + m + k)$$ where $$n$$ is the length of `arr1`, $$m$$ is the length of `arr2`, and $$k$$
 * is the size of the count array (determined by the range of values in `arr1`). Counting occurrences is $$O(n)$$,
 * placing elements from `arr2` is $$O(m)$$, and appending remaining elements is $$O(k)$$.
 *
 * - Space complexity:
 * The space complexity is $$O(k)$$ for the count array, where $$k$$ is the size of the count array.
 */
class RelativeSortArrayArray : RelativeSortArray {
    override fun invoke(arr1: IntArray, arr2: IntArray): IntArray {
        val cnt = IntArray(MAX_ARRAY_SIZE)
        for (n in arr1) {
            cnt[n]++
        }
        var i = 0
        for (n in arr2) {
            while (cnt[n]-- > 0) {
                arr1[i++] = n
            }
        }
        for (n in cnt.indices) {
            while (cnt[n]-- > 0) {
                arr1[i++] = n
            }
        }
        return arr1
    }
}

/**
 * # Intuition
 * The problem requires sorting `arr1` according to the order defined by `arr2`, with elements not in `arr2` appended
 * in ascending order. Using a TreeMap provides an efficient way to count and sort elements since TreeMap maintains
 * elements in sorted order.
 *
 * # Approach
 * 1. Use a TreeMap to count the frequency of each element in `arr1`. The keys in TreeMap are stored in sorted order.
 * 2. Iterate through `arr2` and place elements in `arr1` based on their count in the TreeMap, then remove these
 * elements from the TreeMap.
 * 3. Place remaining elements from the TreeMap into `arr1` in ascending order.
 * 4. Return the modified `arr1`.
 *
 * # Complexity
 * - Time complexity:
 * The time complexity is $$O(n \log n + m \log n)$$ where $$n$$ is the length of `arr1` and $$m$$ is the length of
 * `arr2`. Inserting and removing elements from the TreeMap is $$O(\log n)$$, and we do this for all elements in
 * `arr1` and `arr2`.
 *
 * - Space complexity:
 * The space complexity is $$O(n)$$ for the TreeMap that stores up to `n` elements from `arr1`.
 */
class RelativeSortArrayTreeMap : RelativeSortArray {
    override fun invoke(arr1: IntArray, arr2: IntArray): IntArray {
        val numberCountMap = TreeMap<Int, Int>()
        for (number in arr1) {
            numberCountMap[number] = numberCountMap.getOrDefault(number, 0) + 1
        }
        var index = 0
        for (number in arr2) {
            for (count in 0 until numberCountMap.getOrDefault(number, 0)) {
                arr1[index++] = number
            }
            numberCountMap.remove(number)
        }
        for (number in numberCountMap.keys) {
            for (count in 0 until numberCountMap.getOrDefault(number, 0)) {
                arr1[index++] = number
            }
        }
        return arr1
    }
}
