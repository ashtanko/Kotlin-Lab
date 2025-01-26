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

import dev.shtanko.algorithms.HEXADECIMAL
import dev.shtanko.algorithms.SHUFFLE_CONST

/**
 * Shuffle Array
 * Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
 */
fun IntArray.shuffle(halfArraySize: Int): IntArray {
    if (isEmpty()) {
        return intArrayOf()
    }
    for (currentIndex in halfArraySize until 2 * halfArraySize) {
        this[currentIndex] = this[currentIndex] shl HEXADECIMAL
        this[currentIndex] = this[currentIndex] or this[currentIndex - halfArraySize]
    }
    var currentIndex = 0
    while (currentIndex < 2 * halfArraySize) {
        this[currentIndex] = this[halfArraySize + currentIndex / 2] and SHUFFLE_CONST
        this[currentIndex + 1] = this[halfArraySize + currentIndex / 2] shr HEXADECIMAL
        currentIndex += 2
    }
    return this
}
