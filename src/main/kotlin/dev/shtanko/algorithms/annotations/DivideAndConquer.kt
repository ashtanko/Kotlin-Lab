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
 * The Divide and Conquer approach is a problem-solving technique that involves breaking a problem down into smaller,
 * more manageable subproblems,
 * solving these subproblems recursively, and then combining the results to solve the original problem.
 * This approach is particularly useful for problems that exhibit recursive structure.
 *
 * # How It Works
 *
 * 1: Divide the Problem:
 * The problem is divided into smaller subproblems. These subproblems should be smaller instances of the original
 * problem.
 *
 * 2: Conquer the Subproblems:
 * Each subproblem is solved recursively. If the subproblem is small enough, it is solved directly (base case).
 *
 * 3: Combine the Solutions:
 * After solving the subproblems, the results are combined to form the solution to the original problem.
 *
 * # Example:
 * Merge Sort is a classic example of a Divide and Conquer algorithm:
 * 1. Divide the array into two halves.
 * 2. Recursively sort each half.
 * 3. Merge the two sorted halves.
 *
 * # Advantages:
 * * Efficiency: Divide and conquer algorithms often have a time complexity of O(n log n), making them highly efficient
 * for large inputs.
 * * Parallelism: The independent nature of subproblems makes divide and conquer algorithms suitable for
 * parallelization.
 *
 * # Common Applications:
 * - Sorting (e.g., Merge Sort, Quick Sort)
 * - Searching (e.g., Binary Search)
 * - Matrix multiplication (e.g., Strassen's algorithm)
 *
 * @property info An optional string that provides additional information about the Divide and Conquer algorithm
 * implementation or usage.
 * @constructor Creates a new DivideAndConquer annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class DivideAndConquer(val info: String = "")
