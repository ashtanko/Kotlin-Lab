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

@file:Bitwise

package dev.shtanko.algorithms.bitwise

import dev.shtanko.algorithms.annotations.Bitwise

/**
 * Finds the unique element in an array where every other element appears twice.
 *
 * This method uses the XOR operation, which has the property that:
 * - `x xor x = 0` (XOR-ing a number with itself cancels it out).
 * - `x xor 0 = x` (XOR-ing a number with zero leaves it unchanged).
 *
 * By XOR-ing all elements in the array, the elements that appear twice will cancel each other out, leaving only the
 * unique element.
 *
 * @return The unique integer that appears once in the array.
 *
 * Example:
 * ```
 * val arr = intArrayOf(2, 3, 2, 4, 4)
 * val unique = arr.findUnique() // unique = 3
 * ```
 */
fun IntArray.findUnique(): Int {
    var result = 0
    for (num in this) {
        result = result xor num
    }
    return result
}
