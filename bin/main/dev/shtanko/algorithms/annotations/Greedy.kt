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

package dev.shtanko.algorithms.annotations

/**
 * The Greedy algorithm is a problem-solving approach that makes a series of choices by selecting the best option
 * available at each step, with the hope that these local optimum choices will lead to a globally optimal solution.
 * It is commonly used in optimization problems where an overall solution can be constructed through a sequence of
 * locally optimal choices.
 *
 * # How It Works
 *
 * 1: Problem Breakdown:
 * The greedy algorithm solves a problem by breaking it down into smaller subproblems and making a sequence of choices.
 * At each step, the algorithm chooses the best option (according to some heuristic) without reconsidering previous
 * choices.
 * This is known as the "greedy choice" property.
 *
 * 2: Making Greedy Choices:
 * In each step, the algorithm picks the option that seems the best at the moment based on the current state of
 * the problem.
 * This choice is made without considering the broader problem context and without looking ahead to future decisions.
 *
 * 3: Global Solution:
 * While greedy algorithms may not always produce the optimal solution, they are effective for problems where local
 * optimal choices lead to a globally optimal solution.
 *
 * Example (Greedy Algorithm - Activity Selection Problem):
 * In the activity selection problem, the goal is to select the maximum number of activities that do not overlap in
 * time.
 * The greedy choice is to always select the next activity that finishes the earliest, as it leaves the most time for
 * the remaining activities.
 *
 * ```kotlin
 * fun selectActivities(start: IntArray, finish: IntArray): List<Int> {
 *     val n = start.size
 *     val selectedActivities = mutableListOf<Int>()
 *
 *     // Select the first activity
 *     selectedActivities.add(0)
 *     var lastFinishTime = finish[0]
 *
 *     // Check subsequent activities
 *     for (i in 1 until n) {
 *         if (start[i] >= lastFinishTime) {
 *             selectedActivities.add(i)
 *             lastFinishTime = finish[i]
 *         }
 *     }
 *
 *     return selectedActivities
 * }
 * ```
 *
 * 4: Greedy Algorithms Characteristics:
 * * Greedy choice property: The local optimal choice leads to the global optimal solution.
 * * Optimal substructure: The problem can be broken down into smaller subproblems that can be solved independently and
 * combined.
 * * No backtracking: Once a decision is made, it is never revisited.
 *
 * # Advantages:
 * * Simplicity: Greedy algorithms are usually easy to design and implement.
 * * Efficiency: Greedy algorithms often have lower time and space complexity compared to other optimization algorithms,
 * especially when compared to dynamic programming approaches.
 * * Fast Execution: Since greedy algorithms work by making immediate choices, they can be faster than other methods.
 *
 * # Considerations:
 * * Not Always Optimal: Greedy algorithms do not always guarantee an optimal solution. They work well only for problems
 * that have the greedy choice property and optimal substructure.
 * * Problem Dependent: The algorithm's success depends on the problem being solved. For some problems, a greedy
 * approach might fail to produce the correct result.
 *
 * Greedy algorithms are widely used in problems such as Huffman coding, interval scheduling, and the knapsack problem
 * (under certain conditions), where making a series of local optimal choices leads to a globally optimal solution.
 *
 * @property info An optional string that provides additional information about the Greedy algorithm implementation or
 * usage.
 * @constructor Creates a new Greedy annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Greedy(val info: String = "")
