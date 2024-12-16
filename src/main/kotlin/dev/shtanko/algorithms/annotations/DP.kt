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
 * The Dynamic Programming (DP) approach is a powerful optimization technique used to solve problems by breaking them
 * down into smaller subproblems and solving each subproblem only once, storing the solutions to avoid redundant
 * calculations.
 *
 * # How It Works
 *
 * 1: Break Down the Problem:
 * Dynamic programming solves a problem by dividing it into simpler subproblems. Each subproblem is solved only once,
 * and its solution is stored for future use (memoization or tabulation).
 *
 * 2: Use of Overlapping Subproblems:
 * Dynamic programming is particularly useful when the problem has overlapping subproblems, meaning that the same
 * subproblems are solved multiple times in a naive recursive approach. DP ensures that each subproblem is solved only
 * once.
 *
 * 3: Build Solutions Using Previously Computed Results:
 * In DP, the solution to a problem is built up from the solutions to its subproblems. These subproblems can be solved
 * using either a bottom-up (tabulation) or top-down (memoization) approach.
 *
 * Example (Fibonacci Sequence using DP - Memoization):
 * ```kotlin
 * fun fib(n: Int, memo: MutableMap<Int, Int> = mutableMapOf()): Int {
 *     if (n <= 1) return n
 *     if (n !in memo) {
 *         memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
 *     }
 *     return memo[n] ?: 0
 * }
 * ```
 *
 * Example (Fibonacci Sequence using DP - Tabulation):
 * ```kotlin
 * fun fib(n: Int): Int {
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
 * 4: Optimizing Space and Time:
 * DP can be further optimized by reducing the amount of space used, such as when only a few previous states are needed
 * to calculate the current state.
 *
 * Example (Optimized Space Complexity for Fibonacci Sequence):
 * ```kotlin
 * fun fib(n: Int): Int {
 *     if (n <= 1) return n
 *     var a = 0
 *     var b = 1
 *     for (i in 2..n) {
 *         val temp = a + b
 *         a = b
 *         b = temp
 *     }
 *     return b
 * }
 * ```
 *
 * # Advantages:
 * * Avoids Redundant Computations: By storing the results of subproblems, dynamic programming avoids recalculating the
 * same solutions multiple times.
 * * Efficient Solutions: DP transforms inefficient recursive solutions into efficient ones with time complexity reduced
 * to linear or polynomial time.
 * * Versatility: Dynamic programming can be applied to a wide range of problems, including optimization, counting,
 * sequence alignment, and more.
 *
 * # Considerations:
 * * Space Complexity: While DP saves time, it may use considerable memory to store the solutions of subproblems.
 * * Requires Problem Structure: DP is not suitable for all problems; it requires problems with overlapping subproblems
 * and optimal substructure.
 * * Complexity of Implementation: Writing dynamic programming solutions can be tricky, particularly when transitioning
 * from recursive solutions to dynamic programming ones.
 *
 * Dynamic programming is most effective in problems such as knapsack, shortest path problems
 * (like Floyd-Warshall or Bellman-Ford),
 * and sequence alignment, where solutions to subproblems can be reused to build the final solution.
 *
 * @property info An optional string that provides additional information about the Dynamic Programming algorithm
 * implementation or usage.
 * @constructor Creates a new DP annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class DP(val info: String = "")
