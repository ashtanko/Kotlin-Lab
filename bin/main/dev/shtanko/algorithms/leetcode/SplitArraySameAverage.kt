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
 * This extension function checks if there exists a subset of the array with the same average as the whole array.
 * @param leftSum The sum of the elements in the subset.
 * @param leftNum The number of elements in the subset.
 * @param startIndex The starting index for the subset.
 * @return True if such a subset exists, false otherwise.
 */
private fun IntArray.check(leftSum: Int, leftNum: Int, startIndex: Int): Boolean {
    if (leftNum == 0) return leftSum == 0
    if (this[startIndex] > leftSum / leftNum) return false
    for (i in startIndex until this.size - leftNum + 1) {
        if (i > startIndex && this[i] == this[i - 1]) continue
        if (this.check(leftSum - this[i], leftNum - 1, i + 1)) return true
    }
    return false
}

/**
 * This function checks if it is possible to split the array into two non-empty subsets with the same average.
 * @return True if it is possible to split the array into two non-empty subsets with the same average, false otherwise.
 */
fun IntArray.splitArraySameAverage(): Boolean {
    if (this.size == 1) return false
    var sumA = 0
    for (a in this) {
        sumA += a
    }
    this.sort()
    for (lenOfB in 1..this.size / 2) {
        if (sumA * lenOfB % this.size == 0 && this.check(sumA * lenOfB / this.size, lenOfB, 0)) {
            return true
        }
    }
    return false
}
