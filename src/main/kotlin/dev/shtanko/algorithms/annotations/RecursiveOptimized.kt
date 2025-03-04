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
 * The RecursiveOptimized annotation is used to indicate that a class or function implements an optimized recursive
 * algorithm.
 * Optimized recursion typically involves improving the efficiency of a naive recursive solution through techniques such
 * as memoization, tail recursion, or other methods that reduce redundant calculations or improve the performance of
 * recursive calls.
 *
 * # How It Works
 *
 * 1: Optimized Recursive Techniques:
 * Recursive algorithms can often be inefficient due to repeated calculations of the same subproblems or deep
 * recursion causing performance issues. Optimized recursive solutions aim to address these inefficiencies:
 *
 * - **Memoization**: Storing the results of expensive recursive calls to avoid redundant calculations.
 * - **Tail Recursion**: Transforming recursive functions into tail-recursive ones, which can be optimized by the
 * compiler to reduce the risk of stack overflow and unnecessary memory usage.
 *
 * 2: Example (Recursive Optimized: Fibonacci with Memoization):
 * The following example computes the Fibonacci number using memoization to optimize the recursion and avoid redundant
 * calculations.
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
 * 3: Tail Recursion Optimization:
 * Some recursive functions can be transformed into tail-recursive functions, which can be optimized by the compiler
 * to prevent stack overflow and improve performance.
 *
 * Example (Tail Recursion: Factorial Calculation):
 * ```kotlin
 * tailrec fun factorial(n: Int, accumulator: Int = 1): Int {
 *     return if (n == 0) accumulator else factorial(n - 1, n * accumulator)
 * }
 * ```
 *
 * # Advantages:
 * * **Improved Efficiency**: Optimized recursion techniques like memoization and tail recursion can significantly
 * reduce the time complexity and space complexity of recursive algorithms.
 * * **Reduced Redundancy**: By avoiding redundant calculations or calls, optimized recursion can drastically improve
 * performance.
 * * **Stack Safety**: Tail recursion ensures that deep recursive calls do not cause stack overflow issues, as the
 * compiler can optimize them into a loop.
 *
 * # Considerations:
 * * **Space Complexity**: Memoization introduces additional memory usage to store intermediate results, so it may not
 * be suitable for problems with extremely large inputs.
 * * **Complexity of Optimization**: Implementing optimized recursion may add complexity to the code, as techniques
 * like memoization and tail recursion require extra logic to manage state.
 *
 * Optimized recursion is an essential technique for improving the performance of recursive algorithms, especially when
 * handling problems with overlapping subproblems or deep recursion.
 *
 * @property info An optional string that provides additional information about the optimized recursive algorithm or
 * its usage.
 * @constructor Creates a new RecursiveOptimized annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class RecursiveOptimized(val info: String = "")
