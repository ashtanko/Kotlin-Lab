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
 * The Recursive approach is a fundamental programming technique used to solve problems by breaking them down into
 * smaller, similar sub-problems. This technique is particularly useful for solving problems that have a repetitive
 * structure or can be expressed in terms of smaller instances of the same problem.
 *
 * # How It Works
 *
 * 1: Base Case:
 * Every recursive function must have a base case, which defines the simplest instance of the problem that can
 * be solved directly. Without a base case, the recursion would continue indefinitely.
 * Example:
 * ```kotlin
 * fun factorial(n: Int): Int {
 *     if (n == 0) return 1 // Base case
 *     return n * factorial(n - 1) // Recursive call
 * }
 * ```
 *
 * 2: Recursive Case:
 * In the recursive case, the function calls itself with modified parameters, progressively reducing the problem
 * size until the base case is reached. Each recursive call solves a smaller part of the problem.
 * Example:
 * ```kotlin
 * fun fibonacci(n: Int): Int {
 *     if (n == 0) return 0 // Base case
 *     if (n == 1) return 1 // Base case
 *     return fibonacci(n - 1) + fibonacci(n - 2) // Recursive calls
 * }
 * ```
 *
 * # Advantages:
 * * Elegant Solutions: Recursive solutions are often more intuitive and easier to write for problems like tree
 * traversal, divide-and-conquer algorithms, and mathematical computations.
 * * Expressiveness: Many complex problems can be expressed in a concise way using recursion.
 *
 * # Considerations:
 * * Stack Usage: Recursive calls use the call stack, which can lead to stack overflow if the recursion depth is too
 *   large. Tail recursion or iterative solutions might be preferred in such cases.
 * * Performance: Recursive solutions can sometimes be less efficient than iterative ones, especially if sub-problems
 *   are recalculated multiple times. Techniques like memoization or dynamic programming can mitigate this issue.
 *
 * The Recursive approach is widely applicable in areas such as algorithms, data structures, and mathematical
 * computations.
 * Proper use of base cases and understanding recursion depth are essential to leveraging this technique effectively.
 *
 * @property info An optional string that provides additional information about the recursive approach implementation
 * or usage.
 * @constructor Creates a new Recursive annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Recursive(val info: String = "")
