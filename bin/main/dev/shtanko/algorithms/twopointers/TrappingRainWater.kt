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
 * Trapping Rain Water:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
 * water it can trap after raining.
 */
fun trap(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var leftMax = 0
    var rightMax = 0
    var waterTrapped = 0

    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] > leftMax) {
                leftMax = height[left]
            } else {
                waterTrapped += leftMax - height[left]
            }
            left++
        } else {
            if (height[right] > rightMax) {
                rightMax = height[right]
            } else {
                waterTrapped += rightMax - height[right]
            }
            right--
        }
    }

    return waterTrapped
}
