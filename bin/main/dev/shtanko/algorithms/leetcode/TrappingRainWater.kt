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

import dev.shtanko.datastructures.Stack

fun interface RainWaterStrategy {
    operator fun invoke(arr: IntArray): Int
}

class RainWaterStraightForward : RainWaterStrategy {
    override operator fun invoke(arr: IntArray): Int {
        return arr.trapRainWater()
    }

    private fun IntArray.trapRainWater(): Int {
        if (this.isEmpty()) return 0

        var low = 0
        var high = size - 1
        var maxLeft = 0
        var maxRight = 0
        var ans = 0

        while (low <= high) {
            if (this[low] <= this[high]) {
                if (this[low] >= maxLeft) {
                    maxLeft = this[low]
                } else {
                    ans += maxLeft - this[low]
                }
                low++
            } else {
                if (this[high] >= maxRight) maxRight = this[high]
                ans += maxRight - this[high]
                high--
            }
        }

        return ans
    }
}

class RainWaterStack : RainWaterStrategy {
    override operator fun invoke(arr: IntArray): Int {
        return arr.trapRainWaterUsingStack()
    }

    private fun IntArray.trapRainWaterUsingStack(): Int {
        var ans = 0
        var current = 0
        val stack = Stack<Int>()
        while (current < this.size) {
            while (stack.isNotEmpty() && this[current] > this[stack.peek()]) {
                val top = stack.peek()
                stack.poll()
                if (stack.isEmpty()) break
                val distance = current - stack.peek() - 1
                val boundedHeight = this[current].coerceAtMost(this[stack.peek()] - this[top])
                ans += distance * boundedHeight
            }
            stack.push(current++)
        }
        return ans
    }
}
