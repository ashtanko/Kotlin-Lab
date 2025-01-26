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
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order,
 * return a sorted array of only the integers that appeared in all three arrays.
 * @see <a href="https://leetcode.com/problems/intersection-of-three-sorted-arrays/">Source</a>
 */
fun interface IntersectionThreeSortedArrays {
    operator fun invoke(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int>
}

/**
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class IntersectionThreeSortedBruteForce : IntersectionThreeSortedArrays {
    override operator fun invoke(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
        val ans: MutableList<Int> = ArrayList()
        val counter: MutableMap<Int, Int> = HashMap()
        // iterate through arr1, arr2, and arr3 to count the frequencies
        for (e in arr1) {
            counter[e] = counter.getOrDefault(e, 0) + 1
        }
        for (e in arr2) {
            counter[e] = counter.getOrDefault(e, 0) + 1
        }
        for (e in arr3) {
            counter[e] = counter.getOrDefault(e, 0) + 1
        }

        for (item in counter.keys) {
            if (counter[item] == 3) {
                ans.add(item)
            }
        }
        return ans
    }
}

/**
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class IntersectionThreeSortedThreePointers : IntersectionThreeSortedArrays {
    override operator fun invoke(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
        val ans: MutableList<Int> = ArrayList()
        // prepare three pointers to iterate through three arrays
        // p1, p2, and p3 point to the beginning of arr1, arr2, and arr3 accordingly
        // prepare three pointers to iterate through three arrays
        // p1, p2, and p3 point to the beginning of arr1, arr2, and arr3 accordingly
        var p1 = 0
        var p2 = 0
        var p3 = 0

        while (p1 < arr1.size && p2 < arr2.size && p3 < arr3.size) {
            if (arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
                ans.add(arr1[p1])
                p1++
                p2++
                p3++
            } else {
                if (arr1[p1] < arr2[p2]) {
                    p1++
                } else if (arr2[p2] < arr3[p3]) {
                    p2++
                } else {
                    p3++
                }
            }
        }
        return ans
    }
}
