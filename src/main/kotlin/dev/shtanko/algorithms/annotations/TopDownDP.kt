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
 * The TopDownDP annotation is used to indicate that a function or class implements the top-down dynamic programming
 * (DP) approach.
 * This approach is commonly used to solve problems by breaking them down into smaller subproblems, which are solved
 * recursively and cached to avoid redundant calculations.
 *
 * # How It Works
 *
 * The top-down dynamic programming approach typically uses recursion to break down a problem into smaller subproblems.
 * As each subproblem is solved, its result is stored in a memoization table (often implemented as a map or array).
 * When the same subproblem
 * is encountered again, its result is retrieved from the table instead of being recomputed.
 *
 * The key characteristics of the top-down approach are:
 * 1. **Recursion**: The problem is recursively broken down into smaller subproblems.
 * 2. **Memoization**: The results of solved subproblems are stored to avoid redundant calculations.
 * 3. **Overlapping Subproblems**: The same subproblems are encountered multiple times, which is where memoization
 * provides efficiency.
 *
 * An example of a top-down DP approach to solving the Fibonacci sequence problem:
 *
 * ```kotlin
 * fun fib(n: Int, memo: MutableMap<Int, Int> = mutableMapOf()): Int {
 *     if (n <= 1) return n
 *     if (memo.containsKey(n)) return memo[n]!!
 *     memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
 *     return memo[n]!!
 * }
 * ```
 *
 * In this example, the Fibonacci function uses recursion to compute the nth Fibonacci number. If the result for
 * a subproblem (i.e., a specific Fibonacci number) has been computed before, it is retrieved from the `memo` map rather
 * than being recomputed.
 *
 * # Advantages:
 * * **Avoids Redundant Work**: Memoization ensures that each subproblem is solved only once, improving efficiency.
 * * **Simple to Implement**: The top-down approach is often more intuitive because it uses recursion, making it easier
 * to implement in many cases.
 * * **Effective for Overlapping Subproblems**: The approach is particularly useful when a problem has many overlapping
 * subproblems.
 *
 * # Disadvantages:
 * * **Overhead of Recursion**: Recursive calls can result in a higher overhead compared to the bottom-up approach,
 * particularly for deep recursions.
 * * **Memory Usage**: Memoization requires additional memory to store the intermediate results, which can be
 * problematic for very large problems.
 *
 * The top-down dynamic programming approach is often the natural way to solve many problems, especially when
 * the problem involves recursive
 * subproblems with overlapping subproblems and when recursion is easier to express than iteration.
 *
 * @property info An optional string that provides additional information about the top-down dynamic programming
 * implementation or its usage.
 * @constructor Creates a new TopDownDP annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class TopDownDP(val info: String = "")
