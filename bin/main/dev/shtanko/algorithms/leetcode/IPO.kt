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

import java.util.Collections
import java.util.PriorityQueue

/**
 * 502. IPO
 * @see <a href="https://leetcode.com/problems/ipo/">Source</a>
 */
fun interface IPO {
    operator fun invoke(maxProjects: Int, initialCapital: Int, profits: IntArray, capital: IntArray): Int
}

class GreedyIPO : IPO {

    /**
     * This method finds the maximized capital after completing the maximum number of projects.
     * It uses a greedy approach, always choosing the project with the highest profit that can be completed.
     *
     * @param maxProjects The maximum number of projects that can be completed.
     * @param initialCapital The initial capital available.
     * @param profits An array representing the profit from each project.
     * @param capital An array representing the capital required for each project.
     * @return The maximized capital after completing the projects.
     */
    override fun invoke(
        maxProjects: Int,
        initialCapital: Int,
        profits: IntArray,
        capital: IntArray,
    ): Int {
        val numberOfProjects = profits.size
        val projects = Array(numberOfProjects) { Project() }
        for (i in 0 until numberOfProjects) {
            projects[i] = Project(capital[i], profits[i])
        }
        projects.sort()
        val profitPriorityQueue: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder())
        var projectIndex = 0
        var totalCapital = initialCapital
        for (i in 0 until maxProjects) {
            while (projectIndex < numberOfProjects && projects[projectIndex].requiredCapital <= totalCapital) {
                profitPriorityQueue.add(projects[projectIndex++].profit)
            }
            if (profitPriorityQueue.isEmpty()) {
                break
            }
            totalCapital += profitPriorityQueue.poll()
        }
        return totalCapital
    }

    /**
     * This data class represents a project with a required capital and profit.
     *
     * @property requiredCapital The capital required to complete the project.
     * @property profit The profit from completing the project.
     */
    private data class Project(var requiredCapital: Int = 0, var profit: Int = 0) : Comparable<Project> {
        /**
         * This method compares this project with another project based on the required capital.
         *
         * @param other The other project to compare with.
         * @return A negative integer, zero, or a positive integer as this project's required capital is less than,
         * equal to, or greater than the other project's required capital.
         */
        override fun compareTo(other: Project): Int {
            return requiredCapital - other.requiredCapital
        }
    }
}

/**
 * This class represents a priority queue approach to the IPO problem.
 * It implements the IPO interface.
 */
class PriorityQueueIPO : IPO {
    /**
     * This method finds the maximized capital after completing the maximum number of projects.
     * It uses a priority queue to always choose the project with the highest profit that can be completed.
     *
     * @param maxProjects The maximum number of projects that can be completed.
     * @param initialCapital The initial capital available.
     * @param profits An array representing the profit from each project.
     * @param capital An array representing the capital required for each project.
     * @return The maximized capital after completing the projects.
     */
    override fun invoke(maxProjects: Int, initialCapital: Int, profits: IntArray, capital: IntArray): Int {
        // Sorted list of project indices, sorted by their required capital in descending order
        val projectIndices = profits
            .indices
            .sortedWith { a, b -> capital[b] - capital[a] }
            .toMutableList()
        // Priority queue to hold the profits, with highest profit having the highest priority
        val profitQueue = PriorityQueue<Int> { a, b -> b - a }
        // Total capital available, initialized with the initial capital
        var totalCapital = initialCapital

        // Repeat for the maximum number of projects
        repeat(maxProjects) {
            // While there are projects that can be completed with the available capital
            while (projectIndices.isNotEmpty() && totalCapital >= capital[projectIndices.last()])
                profitQueue.offer(profits[projectIndices.removeAt(projectIndices.size - 1)])
            // Add the highest profit to the total capital
            totalCapital += profitQueue.poll() ?: 0
        }

        // Return the total capital after completing the projects
        return totalCapital
    }
}
