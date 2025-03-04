/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.learn

import dev.shtanko.extensions.second

fun secondStraightForward(arr: IntArray): Int {
    if (arr.size < 2) return -1
    var first = Int.MAX_VALUE
    var second = Int.MAX_VALUE
    for (item in arr) {
        if (item < first) {
            second = first
            first = item
        } else if (item < second && item != first) {
            second = item
        }
    }
    return second
}

fun secondMinSort(arr: IntArray): Int {
    if (arr.size < 2) return -1
    arr.sort()
    return arr.second()
}

fun uniqueWholeNumbersSet(arr: IntArray): IntArray {
    if (arr.isEmpty()) return intArrayOf()
    if (arr.size < 2) return arr
    return arr.toSet().toIntArray()
}

fun mergeTwoSortedPlus(arr1: IntArray, arr2: IntArray): IntArray {
    if (arr1.isEmpty() && arr2.isEmpty()) return intArrayOf()
    return arr1.plus(arr2)
}

fun mergeTwoSortedSF(arr1: IntArray, arr2: IntArray): IntArray {
    if (arr1.isEmpty() && arr2.isEmpty()) return intArrayOf()
    val mergedArraySize = arr1.size + arr2.size
    val mergedArray = IntArray(mergedArraySize)
    var pos = 0
    for (value in arr1) {
        mergedArray[pos] = value
        pos++
    }
    for (value in arr2) {
        mergedArray[pos] = value
        pos++
    }
    return mergedArray
}
