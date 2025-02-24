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

/**
 * 874. Walking Robot Simulation
 * @see <a href="https://leetcode.com/problems/walking-robot-simulation/">Walking Robot Simulation</a>
 */
fun interface WalkingRobotSimulation {
    operator fun invoke(commands: IntArray, obstacles: Array<IntArray>): Int
}

data object WalkingRobotSimulationImpl : WalkingRobotSimulation {
    private const val HASH_MULTIPLIER = 60013 // Slightly larger than 2 * max coordinate value

    override fun invoke(commands: IntArray, obstacles: Array<IntArray>): Int {
        // Store obstacles in a HashSet for efficient lookup
        val obstacleSet = mutableSetOf<Int>()
        for (obstacle in obstacles) {
            obstacleSet.add(hashCoordinates(obstacle[0], obstacle[1]))
        }

        // Define direction vectors: North, East, South, West
        val directions = arrayOf(
            intArrayOf(0, 1), // North
            intArrayOf(1, 0), // East
            intArrayOf(0, -1), // South
            intArrayOf(-1, 0), // West
        )

        var currentPosition = intArrayOf(0, 0)
        var maxDistanceSquared = 0
        var currentDirection = 0 // 0: North, 1: East, 2: South, 3: West

        for (command in commands) {
            when (command) {
                -1 -> {
                    // Turn right
                    currentDirection = (currentDirection + 1) % 4
                }

                -2 -> {
                    // Turn left
                    currentDirection = (currentDirection + 3) % 4
                }

                else -> {
                    // Move forward
                    val direction = directions[currentDirection]
                    for (step in 0 until command) {
                        val nextX = currentPosition[0] + direction[0]
                        val nextY = currentPosition[1] + direction[1]
                        if (obstacleSet.contains(hashCoordinates(nextX, nextY))) {
                            break
                        }
                        currentPosition[0] = nextX
                        currentPosition[1] = nextY
                    }

                    maxDistanceSquared = maxOf(
                        maxDistanceSquared,
                        currentPosition[0] * currentPosition[0] + currentPosition[1] * currentPosition[1],
                    )
                }
            }
        }

        return maxDistanceSquared
    }

    // Hash function to convert (x, y) coordinates to a unique integer value
    private fun hashCoordinates(x: Int, y: Int): Int {
        return x + HASH_MULTIPLIER * y
    }
}
