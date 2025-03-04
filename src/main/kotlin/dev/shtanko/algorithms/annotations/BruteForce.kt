/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.annotations

/**
 * The Brute Force approach is a straightforward method of solving problems by trying all possible solutions
 * and selecting the best one. It is often used as a baseline or reference for more optimized algorithms and
 * is easy to implement, but may not always be the most efficient.
 *
 * # How It Works
 *
 * 1: Exhaustive Search:
 * Brute force algorithms typically work by exhaustively exploring all potential solutions. In some cases, this means
 * trying every combination, permutation, or iteration of inputs until the correct solution is found.
 *
 * Example:
 * ```kotlin
 * // Finding the maximum subarray sum using Brute Force
 * fun maxSubArraySum(arr: IntArray): Int {
 *     var maxSum = Int.MIN_VALUE
 *     for (i in arr.indices) {
 *         for (j in i until arr.size) {
 *             var sum = 0
 *             for (k in i..j) {
 *                 sum += arr[k]
 *             }
 *             maxSum = maxOf(maxSum, sum)
 *         }
 *     }
 *     return maxSum
 * }
 * ```
 *
 * 2: Evaluate All Possibilities:
 * The key idea is that a brute force solution evaluates all possible options, which may involve iterating
 * over a large search space to find the correct result.
 *
 * 3: Return the Optimal Solution:
 * Once all possible solutions have been evaluated, the optimal or correct solution is returned.
 *
 * Example:
 * ```kotlin
 * // Finding the shortest path using Brute Force (checking all paths)
 * fun findShortestPath(graph: List<List<Int>>): Int {
 *     var minLength = Int.MAX_VALUE
 *     // Generate all possible paths and find the shortest
 *     for (path in generateAllPaths(graph)) {
 *         minLength = minOf(minLength, calculatePathLength(path))
 *     }
 *     return minLength
 * }
 * ```
 *
 * # Advantages:
 * * Simplicity: Brute Force algorithms are often the easiest to understand and implement.
 * * No Need for Optimization: Since all possible solutions are tested, the brute force approach guarantees finding
 *   the correct solution, though it may not be efficient.
 *
 * # Considerations:
 * * Inefficiency: Brute Force algorithms tend to have high time and space complexity, especially for large input sizes.
 *   They are often not scalable for problems with large data sets.
 * * Not Suitable for Large Data Sets: For large problems, Brute Force may be impractical, and more efficient algorithms
 *   (like dynamic programming, greedy algorithms, etc.) should be considered.
 *
 * Brute Force remains a useful technique for simple problems, small inputs, or as a benchmark for comparing
 * more efficient solutions. It is often used in cases where other algorithms are too complex to implement or not
 * necessary for the problem size.
 *
 * @property info An optional string that provides additional information about the Brute Force algorithm implementation
 * or usage.
 * @constructor Creates a new BruteForce annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BruteForce(val info: String = "")
