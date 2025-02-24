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

import dev.shtanko.extensions.swap

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 */
fun IntArray.nextPermutation() {
    val n = this.size
    if (n < 2) return

    var index = n - 1
    while (index > 0) {
        if (this[index - 1] < this[index]) {
            break
        }
        index--
    }

    if (index == 0) {
        reverseSort(0, n - 1)
        return
    } else {
        val value = this[index - 1]
        var j = n - 1
        while (j > index) {
            if (this[j] > value) break
            j--
        }
        swap(j, index - 1)
        reverseSort(index, n - 1)
    }
}

private fun IntArray.reverseSort(start: Int, end: Int) {
    var i = start
    var j = end
    while (i < j) swap(i++, j--)
}
