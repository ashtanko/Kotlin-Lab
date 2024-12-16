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
 * Matrix Exponentiation is a technique used to efficiently compute powers of a matrix. It is particularly useful for
 * solving problems that involve recurring patterns or linear recursions, such as calculating Fibonacci numbers,
 * solving linear recurrences, or finding the number of ways to reach a specific state in certain types of dynamic
 * programming problems.
 *
 * # How It Works
 *
 * 1: Concept:
 * Matrix exponentiation leverages the properties of matrices to speed up the computation of high powers of a matrix.
 * The core idea is to compute powers of a matrix in logarithmic time by recursively squaring the matrix (similar to
 * fast exponentiation).
 *
 * Given a matrix `A`, the goal is to compute `A^k`, where `k` is a non-negative integer. The basic idea is:
 * - If `k` is even, we can use the identity `A^k = (A^(k/2)) * (A^(k/2))`.
 * - If `k` is odd, we use the identity `A^k = A * (A^(k-1))`.
 *
 * This approach allows computing `A^k` in O(log k) time, which is a significant improvement over the naive approach,
 * which would take O(k) time.
 *
 * 2: Applications:
 * Matrix Exponentiation is especially useful in problems involving linear recurrence relations, such as:
 * - Fibonacci numbers
 * - Path counting in directed graphs
 * - Solving systems of linear recursions
 * - Computing powers of matrices in algorithms like PageRank and Google's search algorithms.
 *
 * 3: Efficiency:
 * The time complexity of matrix exponentiation using fast exponentiation is O(log k) for exponentiation,
 * along with the time to multiply matrices (O(n^3) for naive matrix multiplication, or O(n^2) for optimized methods).
 *
 * # Example:
 * Consider the Fibonacci sequence, which can be represented by a matrix multiplication:
 * ```
 * [F(n)    ] = [1 1] * [F(n-1) ]
 * [F(n-1)  ]   [1 0]   [F(n-2) ]
 * ```
 * Using matrix exponentiation, we can compute `F(n)` efficiently in O(log n) time instead of O(n) time using recursion.
 *
 * # Advantages:
 * * Efficient Computation: Reduces the time complexity of problems that involve matrix powers, making previously slow
 * algorithms faster.
 * * Versatile: Can be applied to a wide range of problems, from Fibonacci number computation to complex recurrence
 * relations.
 *
 * # Disadvantages:
 * * Complexity: The implementation of matrix exponentiation may be more complex than simpler iterative or recursive
 * methods.
 * * Matrix Multiplication Overhead: For large matrices, the multiplication step may still have a significant overhead,
 * especially for naive multiplication methods.
 *
 * # Common Applications:
 * - Fibonacci number calculation
 * - Path counting in graphs
 * - Linear recurrence relations
 * - Algorithmic problems in dynamic programming and computational mathematics
 *
 * @property info An optional string that provides additional information about the matrix exponentiation algorithm
 * implementation or usage.
 * @constructor Creates a new MatrixExponentiation annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class MatrixExponentiation(val info: String = "")
