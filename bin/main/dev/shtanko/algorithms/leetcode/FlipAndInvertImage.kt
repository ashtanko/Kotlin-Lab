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
 * This function flips and inverts an image represented as a 2D array.
 * The flipping is done in-place for efficiency.
 *
 * @param arr The 2D array representing the image. Each inner array represents a row of the image.
 *            Each integer in the inner array represents a pixel in the image. The value of the integer
 *            is 0 for a white pixel and 1 for a black pixel.
 * @return The 2D array representing the flipped and inverted image.
 */
fun flipAndInvertImage(image: Array<IntArray>): Array<IntArray> {
    val columnCount: Int = image[0].size
    for (row in image) for (i in 0 until (columnCount + 1) / 2) {
        val temp = row[i] xor 1
        row[i] = row[columnCount - 1 - i] xor 1
        row[columnCount - 1 - i] = temp
    }

    return image
}
