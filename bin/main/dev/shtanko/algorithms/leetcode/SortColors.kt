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

private const val RED = 0
private const val WHITE = 1
private const val BLUE = 2

/**
 * 75. Sort Colors
 * @see <a href="https://leetcode.com/problems/sort-colors/">Source</a>
 */
fun interface SortColors {
    operator fun invoke(colors: IntArray)
}

/**
 * # Intuition
 * The problem is essentially about sorting an array with three unique elements (0, 1, 2). A common approach for
 * such problems is the Dutch National Flag algorithm, which efficiently sorts the array in a single pass.
 *
 * # Approach
 * We use three pointers:
 * - `redPointer` to keep track of the position to place the next red (0).
 * - `bluePointer` to keep track of the position to place the next blue (2).
 * - `currentIndex` to iterate through the array.
 *
 * We iterate through the array, and for each element:
 * - If it's a red (0), we swap it with the element at `redPointer` and move both `redPointer` and `currentIndex`
 * forward.
 * - If it's a blue (2), we swap it with the element at `bluePointer` and move `bluePointer` backward.
 * - If it's a white (1), we simply move `currentIndex` forward.
 *
 * This way, we ensure that all 0s are at the beginning, all 2s are at the end, and all 1s are in the middle.
 *
 * # Complexity
 * - Time complexity: $$O(n)$$
 *   We iterate through the array once, making constant time operations for each element.
 *
 * - Space complexity: $$O(1)$$
 *   We use a constant amount of extra space for the pointers.
 */
class SortColorsOnePass : SortColors {
    override operator fun invoke(colors: IntArray) {
        var redPointer = 0
        var bluePointer = colors.size - 1
        var currentIndex = 0
        while (currentIndex <= bluePointer) {
            if (colors[currentIndex] == RED) {
                colors[currentIndex] = colors[redPointer]
                colors[redPointer] = RED
                redPointer++
            }
            if (colors[currentIndex] == BLUE) {
                colors[currentIndex] = colors[bluePointer]
                colors[bluePointer] = BLUE
                bluePointer--
                currentIndex--
            }
            currentIndex++
        }
    }
}

/**
 * # Intuition
 * Counting the number of each color and then overwriting the array based on these counts is a straightforward approach.
 *
 * # Approach
 * - First, we iterate through the array and count the number of reds (0s), whites (1s), and blues (2s).
 * - Then, we overwrite the array:
 *   - Set the first `redCount` elements to 0,
 *   - The next `whiteCount` elements to 1,
 *   - The remaining elements to 2.
 *
 * This ensures the array is sorted correctly.
 *
 * # Complexity
 * - Time complexity: $$O(n)$$
 *   We iterate through the array twice: once for counting and once for overwriting.
 *
 * - Space complexity: $$O(1)$$
 *   We use a constant amount of extra space for the counts.
 */
class SortColorsTwoPass : SortColors {
    override operator fun invoke(colors: IntArray) {
        var redCount = 0
        var whiteCount = 0
        var blueCount = 0
        for (color in colors) {
            when (color) {
                RED -> redCount++
                WHITE -> whiteCount++
                BLUE -> blueCount++
            }
        }
        for (index in colors.indices) {
            when {
                index < redCount -> colors[index] = RED
                index < redCount + whiteCount -> colors[index] = WHITE
                else -> colors[index] = BLUE
            }
        }
    }
}
