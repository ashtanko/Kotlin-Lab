/*
 * Copyright 2023 Oleksii Shtanko
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

import dev.shtanko.algorithms.HALF_OF_BYTE
import dev.shtanko.algorithms.OCTAL

/**
 * 661. Image Smoother
 * @see <a href="https://leetcode.com/problems/image-smoother">Source</a>
 */
fun interface ImageSmoother {
    operator fun invoke(img: Array<IntArray>): Array<IntArray>
}

sealed interface ImageSmootherStrategy {
    /**
     * Approach 1: Create a New Smoothened Image
     */
    data object NewSmoothenedImage : ImageSmoother, ImageSmootherStrategy {
        override fun invoke(img: Array<IntArray>): Array<IntArray> {
            val m = img.size
            val n = img[0].size

            val smoothImg = createSmoothImage(m, n)

            for (i in 0 until m) {
                for (j in 0 until n) {
                    smoothImg[i][j] = calculateSmoothValue(img, m, n, i, j)
                }
            }

            return smoothImg
        }

        private fun createSmoothImage(m: Int, n: Int): Array<IntArray> {
            return Array(m) { IntArray(n) }
        }

        private fun calculateSmoothValue(img: Array<IntArray>, m: Int, n: Int, i: Int, j: Int): Int {
            var sum = 0
            var count = 0

            for (x in i - 1..i + 1) {
                for (y in j - 1..j + 1) {
                    if (x in 0 until m && y in 0 until n) {
                        sum += img[x][y]
                        count += 1
                    }
                }
            }

            return sum / count
        }
    }

    data object ConstantSpaceSmoothenedImage : ImageSmoother, ImageSmootherStrategy {
        override fun invoke(img: Array<IntArray>): Array<IntArray> {
            val m: Int = img.size
            val n: Int = img[0].size

            val encodedImage = encodeSmoothedValues(img, m, n)
            val smoothedImage = extractSmoothedValues(encodedImage, m, n)

            return smoothedImage
        }

        private fun encodeSmoothedValues(img: Array<IntArray>, m: Int, n: Int): Array<IntArray> {
            val encodedImg = Array(m) { IntArray(n) }

            for (i in 0 until m) {
                for (j in 0 until n) {
                    val neighborhoodSum = calculateNeighborhoodSum(img, m, n, i, j)
                    val neighborhoodCount = calculateNeighborhoodCount(m, n, i, j)

                    encodedImg[i][j] = encodePixelValue(img[i][j], neighborhoodSum, neighborhoodCount)
                }
            }

            return encodedImg
        }

        private fun calculateNeighborhoodSum(img: Array<IntArray>, m: Int, n: Int, i: Int, j: Int): Int {
            var sum = 0

            for (x in i - 1..i + 1) {
                for (y in j - 1..j + 1) {
                    if (x in 0 until m && y in 0 until n) {
                        sum += img[x][y] % HALF_OF_BYTE
                    }
                }
            }

            return sum
        }

        private fun calculateNeighborhoodCount(m: Int, n: Int, i: Int, j: Int): Int {
            var count = 0

            for (x in i - 1..i + 1) {
                for (y in j - 1..j + 1) {
                    if (x in 0 until m && y in 0 until n) {
                        count++
                    }
                }
            }

            return count
        }

        private fun encodePixelValue(originalValue: Int, neighborhoodSum: Int, neighborhoodCount: Int): Int {
            return originalValue + (neighborhoodSum / neighborhoodCount) * HALF_OF_BYTE
        }

        private fun extractSmoothedValues(encodedImg: Array<IntArray>, m: Int, n: Int): Array<IntArray> {
            val smoothedImg = Array(m) { IntArray(n) }

            for (i in 0 until m) {
                for (j in 0 until n) {
                    smoothedImg[i][j] = encodedImg[i][j] / HALF_OF_BYTE
                }
            }

            return smoothedImg
        }
    }

    data object ImageSmootherBitwise : ImageSmoother, ImageSmootherStrategy {
        override fun invoke(img: Array<IntArray>): Array<IntArray> {
            val rows = img.size
            val cols = img[0].size

            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    val (sum, count) = calculateNeighborSumAndCount(img, i, j, rows, cols)
                    img[i][j] = encodeSmoothedValue(img[i][j], sum, count)
                }
            }

            decodeSmoothedValues(img)

            return img
        }

        private fun calculateNeighborSumAndCount(
            img: Array<IntArray>,
            i: Int,
            j: Int,
            rows: Int,
            cols: Int,
        ): Pair<Int, Int> {
            var sum = 0
            var count = 0

            for (x in i - 1..i + 1) {
                for (y in j - 1..j + 1) {
                    if (x in 0 until rows && y in 0 until cols) {
                        sum += img[x][y] and HALF_OF_BYTE - 1
                        count++
                    }
                }
            }

            return Pair(sum, count)
        }

        private fun encodeSmoothedValue(originalValue: Int, sum: Int, count: Int): Int {
            return originalValue or ((sum / count) shl OCTAL)
        }

        private fun decodeSmoothedValues(img: Array<IntArray>) {
            for (i in img.indices) {
                for (j in img[i].indices) {
                    img[i][j] = img[i][j] shr OCTAL
                }
            }
        }
    }
}
