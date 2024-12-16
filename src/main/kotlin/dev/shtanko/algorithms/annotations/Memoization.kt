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
 * The Memoization annotation is used to indicate that a class, function, or property utilizes or implements
 * memoization, a technique used to optimize algorithms by storing the results of expensive function calls and
 * reusing the cached results when the same inputs occur again.
 *
 * # How It Works
 *
 * 1: Memoization Technique:
 * Memoization is typically applied in recursive functions where the same subproblems are solved multiple times.
 * By storing the results of these subproblems in a cache (usually a map or an array), the algorithm avoids
 * redundant calculations, significantly improving performance, particularly in problems with overlapping subproblems.
 *
 * 2: Cache Storage:
 * The results of computations are stored in a cache, and before performing any calculation, the function checks
 * if the result for the given input already exists in the cache. If the result is found, it is returned immediately,
 * avoiding redundant computation.
 *
 * Example (Memoization: Fibonacci Sequence):
 * The following function computes the nth Fibonacci number using memoization to avoid redundant calculations.
 *
 * ```kotlin
 * val memo = mutableMapOf<Int, Int>()
 *
 * fun fibonacci(n: Int): Int {
 *     if (n <= 1) return n
 *     if (memo.containsKey(n)) return memo[n]!!
 *     val result = fibonacci(n - 1) + fibonacci(n - 2)
 *     memo[n] = result
 *     return result
 * }
 * ```
 *
 * 3: Efficiency:
 * Memoization reduces the time complexity of algorithms with overlapping subproblems, such as dynamic programming
 * problems. For example, the naive recursive approach for Fibonacci has exponential time complexity,
 * but with memoization, it reduces to linear time complexity.
 *
 * # Advantages:
 * * Significant Speed Improvement: Memoization speeds up algorithms by avoiding redundant calculations, particularly in
 * recursive algorithms.
 * * Reduces Time Complexity: In many cases, memoization transforms an exponential-time algorithm into a polynomial-time
 * algorithm by solving subproblems just once.
 * * Simplicity: Memoization is simple to implement and often requires minimal code changes to the original algorithm.
 *
 * # Considerations:
 * * Space Complexity: Memoization introduces additional memory usage to store the results in the cache. This can be
 * an issue in problems with large input spaces or where the cache grows quickly.
 * * Not Always Applicable: Memoization is most effective when there are overlapping subproblems. If the problem is
 * purely sequential or non-recursive, memoization may not provide significant benefits.
 *
 * Memoization is particularly useful in dynamic programming problems, where the problem can be broken down into
 * overlapping subproblems. By caching results of these subproblems, memoization can optimize the algorithm and improve
 * performance.
 *
 * @property info An optional string that provides additional information about the memoization technique used in the
 * implementation or its usage.
 * @constructor Creates a new Memoization annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Memoization(val info: String = "")
