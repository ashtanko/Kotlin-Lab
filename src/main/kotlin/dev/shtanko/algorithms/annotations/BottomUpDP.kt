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
 * The Bottom-Up Dynamic Programming (DP) approach is a technique used to solve optimization problems by solving
 * all possible subproblems iteratively and combining their solutions to address the original problem. It builds
 * the solution incrementally, starting from the smallest subproblems and progressing towards larger ones.
 *
 * # How It Works
 *
 * 1: Define the State:
 * Identify the subproblems and define a state representation. Typically, the state is stored in a table (array or
 * matrix) where each entry represents the solution to a specific subproblem.
 *
 * Example:
 * ```kotlin
 * // Fibonacci sequence using Bottom-Up DP
 * fun fibonacci(n: Int): Int {
 *     if (n <= 1) return n
 *     val dp = IntArray(n + 1)
 *     dp[0] = 0
 *     dp[1] = 1
 *     for (i in 2..n) {
 *         dp[i] = dp[i - 1] + dp[i - 2]
 *     }
 *     return dp[n]
 * }
 * ```
 *
 * 2: Transition Relation:
 * Establish a relation to transition from smaller subproblems to larger ones. This defines how the solution to a
 * subproblem is derived from the solutions of smaller subproblems.
 *
 * 3: Base Cases:
 * Define the base cases of the problem, which represent the simplest subproblems.
 *
 * 4: Iterative Computation:
 * Use loops to iteratively compute solutions for all subproblems, starting from the base cases and progressing
 * towards the final solution.
 *
 * Example:
 * ```kotlin
 * // Minimum cost to reach the last step
 * fun minCostClimbingStairs(cost: IntArray): Int {
 *     val n = cost.size
 *     val dp = IntArray(n + 1)
 *     dp[0] = 0
 *     dp[1] = 0
 *     for (i in 2..n) {
 *         dp[i] = minOf(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
 *     }
 *     return dp[n]
 * }
 * ```
 *
 * # Advantages:
 * * Efficiency: Bottom-Up DP avoids recursion and the overhead of function calls, making it more space-efficient
 *   than Top-Down DP with memoization.
 * * Clarity: Iterative solutions are often easier to debug and understand compared to recursive ones.
 *
 * # Considerations:
 * * Memory Usage: The size of the DP table can be large, especially for problems involving multiple dimensions.
 * * Problem Design: Not all problems are naturally suited for a Bottom-Up DP approach. Problem decomposition
 *   and state definition are critical.
 *
 * Bottom-Up DP is a powerful tool for solving optimization and combinatorial problems, offering a systematic
 * way to build solutions incrementally. It is widely used in fields like computer science, mathematics, and operations
 * research.
 *
 * @property info An optional string that provides additional information about the Bottom-Up DP algorithm
 * implementation or usage.
 * @constructor Creates a new BottomUpDP annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BottomUpDP(val info: String = "")
