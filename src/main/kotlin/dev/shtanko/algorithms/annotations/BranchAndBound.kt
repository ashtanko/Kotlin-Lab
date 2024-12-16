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
 * The Branch and Bound (B&B) algorithm is a general algorithmic technique for solving optimization problems,
 * particularly useful for solving combinatorial problems such as the traveling salesman problem, integer programming,
 * and knapsack problems. It systematically explores the solution space by partitioning it into smaller subproblems
 * (branches) and evaluating their feasibility (bounds) to discard suboptimal solutions.
 *
 * # How It Works
 *
 * 1: Branching:
 * The problem is divided into smaller subproblems (branches) by making decisions or splitting the solution space
 * into manageable parts. Each subproblem represents a partial solution to the overall problem.
 *
 * 2: Bounding:
 * For each branch, a bound is calculated to estimate the best possible solution that could be obtained from that
 * branch.
 * This bound is used to determine if the current branch should be explored further or pruned if the bound is worse
 * than the best solution found so far.
 *
 * 3: Pruning:
 * If a branch's bound indicates that it cannot lead to a better solution than the current best solution, the branch is
 * discarded (pruned), thus reducing the search space.
 *
 * 4: Exploration:
 * The algorithm explores the remaining branches, continuing to divide the problem and prune unpromising subproblems
 * until an optimal solution is found.
 *
 * # Example:
 * In the traveling salesman problem, the algorithm explores different routes between cities, calculating bounds on the
 * total travel distance and pruning routes that cannot lead to an optimal solution. It continues branching until the
 * minimum possible distance is found.
 *
 * # Advantages:
 * * Guarantees Optimality: Branch and Bound ensures the discovery of the best possible solution.
 * * Systematic Exploration: It explores all potential solutions but discards suboptimal ones, leading to efficient
 * problem solving.
 *
 * # Disadvantages:
 * * Time Complexity: The algorithm can suffer from exponential time complexity in the worst case, especially with large
 * search spaces.
 *
 * # Common Applications:
 * - Solving combinatorial optimization problems such as the Traveling Salesman Problem (TSP)
 * - Integer programming and knapsack problems
 * - Scheduling and resource allocation problems
 *
 * @property info An optional string that provides additional information about the Branch and Bound algorithm
 * implementation or usage.
 * @constructor Creates a new BranchAndBound annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BranchAndBound(val info: String = "")
