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

package dev.shtanko.algorithms.twopointers

/**
 * Container With Most Water:
 * Given an array of non-negative integers representing the heights of vertical lines,
 * find two lines that together with the x-axis forms a container that can hold the most water.
 */
fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var maxArea = 0

    while (left < right) {
        val currentHeight = minOf(height[left], height[right])
        val width = right - left
        val currentArea = currentHeight * width

        maxArea = maxOf(maxArea, currentArea)

        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }

    return maxArea
}
