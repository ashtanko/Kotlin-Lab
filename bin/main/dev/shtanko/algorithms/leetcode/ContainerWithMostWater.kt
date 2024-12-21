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

fun interface ContainerWithMostWaterStrategy {
    fun maxArea(height: IntArray): Int
}

class ContainerWithMostWaterBruteForce : ContainerWithMostWaterStrategy {
    override fun maxArea(height: IntArray): Int {
        var maxArea = 0
        for (i in height.indices) {
            for (j in i + 1 until height.size) {
                maxArea = maxArea.coerceAtLeast(height[i].coerceAtMost(height[j]) * (j - i))
            }
        }
        return maxArea
    }
}

class ContainerWithMostWaterTwoPointer : ContainerWithMostWaterStrategy {
    override fun maxArea(height: IntArray): Int {
        var maxArea = 0
        var l = 0
        var r: Int = height.size - 1
        while (l < r) {
            maxArea = maxArea.coerceAtLeast(height[l].coerceAtMost(height[r]) * (r - l))
            if (height[l] < height[r]) l++ else r--
        }
        return maxArea
    }
}
