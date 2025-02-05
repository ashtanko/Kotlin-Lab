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
 * The Brute Force Optimized approach is a refined version of the traditional brute force method.
 * While still focusing on evaluating all potential solutions, it incorporates strategies to reduce
 * unnecessary computations, improving efficiency without abandoning the exhaustive search methodology.
 *
 * # How It Works
 *
 * 1: Improved Search Space:
 * Instead of blindly exploring all possibilities, Brute Force Optimized attempts to eliminate redundant checks
 * or subproblems that don't contribute to the solution.
 * This often involves applying heuristics, early termination, or intelligent pruning of the search space.
 *
 * Example:
 * ```kotlin
 * // Optimized Brute Force for finding the maximum subarray sum
 * fun maxSubArraySum(arr: IntArray): Int {
 *     var maxSum = Int.MIN_VALUE
 *     for (i in arr.indices) {
 *         var sum = 0
 *         for (j in i until arr.size) {
 *             sum += arr[j]
 *             maxSum = maxOf(maxSum, sum)
 *         }
 *     }
 *     return maxSum
 * }
 * ```
 *
 * 2: Pruning and Memoization:
 * Optimized Brute Force algorithms may use pruning techniques to avoid checking solutions that cannot possibly
 * lead to a better result. Memoization can also help by storing already computed values to prevent redundant
 * calculations.
 *
 * 3: Intelligent Decision Making:
 * Brute Force Optimized techniques often incorporate smart decisions on which part of the search space to explore
 * first based on heuristics or precomputed data.
 *
 * Example:
 * ```kotlin
 * // Optimized Brute Force for solving the knapsack problem using pruning
 * fun knapsack(weights: IntArray, values: IntArray, capacity: Int): Int {
 *     val n = weights.size
 *     var maxVal = 0
 *     for (i in 0 until (1 shl n)) { // Generate all subsets
 *         var totalWeight = 0
 *         var totalValue = 0
 *         for (j in 0 until n) {
 *             if ((i and (1 shl j)) != 0) {
 *                 totalWeight += weights[j]
 *                 totalValue += values[j]
 *             }
 *         }
 *         if (totalWeight <= capacity) {
 *             maxVal = maxOf(maxVal, totalValue)
 *         }
 *     }
 *     return maxVal
 * }
 * ```
 *
 * # Advantages:
 * * Increased Efficiency: Brute Force Optimized reduces unnecessary operations and prunes irrelevant search spaces,
 *   making it more efficient than pure Brute Force.
 * * Guarantees Correctness: Like brute force, it still guarantees finding the correct solution by exhaustively
 *   checking all possibilities within a refined scope.
 *
 * # Considerations:
 * * Complexity: While more efficient, Brute Force Optimized still involves exploring a large search space, and may
 *   still be unsuitable for very large datasets.
 * * Trade-offs: Optimizing brute force can make the algorithm more complex to implement, requiring additional
 *   considerations for pruning, memoization, and heuristics.
 *
 * Brute Force Optimized is ideal for scenarios where a pure brute force approach is too slow, but where
 * a more complex optimization algorithm (such as dynamic programming or greedy methods) may not be necessary or
 * feasible.
 *
 * @property info An optional string that provides additional information about the Brute Force Optimized algorithm
 * implementation or usage.
 * @constructor Creates a new BruteForceOptimized annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BruteForceOptimized(val info: String = "")
