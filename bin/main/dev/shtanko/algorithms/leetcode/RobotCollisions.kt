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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.Sort
import java.util.Stack

/**
 * 2751. Robot Collisions
 * @see <a href="https://leetcode.com/problems/robot-collisions">Source</a>
 */
fun interface RobotCollisions {
    operator fun invoke(positions: IntArray, healths: IntArray, directions: String): List<Int>
}

@Sort
class RobotCollisionsSort : RobotCollisions {
    override fun invoke(
        positions: IntArray,
        healths: IntArray,
        directions: String,
    ): List<Int> {
        val n = positions.size
        val indices = (positions.indices).sortedBy { positions[it] }.toList()
        val result = mutableListOf<Int>()
        val stack = Stack<Int>()

        for (currentIndex in indices) {
            if (directions[currentIndex] == 'R') {
                stack.push(currentIndex)
            } else {
                while (stack.isNotEmpty() && healths[currentIndex] > 0) {
                    val topIndex = stack.pop()

                    when {
                        healths[topIndex] > healths[currentIndex] -> {
                            healths[topIndex] -= 1
                            healths[currentIndex] = 0
                            stack.push(topIndex)
                        }

                        healths[topIndex] < healths[currentIndex] -> {
                            healths[currentIndex] -= 1
                            healths[topIndex] = 0
                        }

                        else -> {
                            healths[currentIndex] = 0
                            healths[topIndex] = 0
                        }
                    }
                }
            }
        }

        for (index in 0 until n) {
            if (healths[index] > 0) {
                result.add(healths[index])
            }
        }

        return result
    }
}
