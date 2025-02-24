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
 * The Iterative algorithm is a computational approach where the solution to a problem is obtained by repeatedly
 * applying the same process or steps, often through loops, until a certain condition is met or an optimal solution is
 * reached.
 * It is commonly used in problems that can be broken down into repeated steps, such as searching, sorting, and dynamic
 * programming.
 *
 * # How It Works
 *
 * 1: Repetition of Steps:
 * In an iterative approach, a set of instructions or steps is executed repeatedly, usually within a loop, until
 * a specific termination condition is met. This method allows for breaking down complex problems into simpler
 * subproblems that can be solved step by step.
 *
 * 2: Common Patterns:
 * Iterative algorithms often use structures such as `for` loops, `while` loops, or other control flow mechanisms that
 * allow the algorithm to repeatedly process elements or conditions.
 *
 * Example (Iterative Factorial Calculation):
 * In the iterative approach to calculating the factorial of a number, we multiply the current result by each integer
 * from 1 to the given number. The loop continues until the factorial is computed.
 *
 * ```kotlin
 * fun factorial(n: Int): Int {
 *     var result = 1
 *     for (i in 1..n) {
 *         result *= i
 *     }
 *     return result
 * }
 * ```
 *
 * 3: Termination Condition:
 * The iterative process continues until a certain condition is met (e.g., reaching the end of an array, achieving a
 * desired result, etc.).
 * Once this condition is satisfied, the algorithm terminates and returns the result.
 *
 * # Advantages:
 * * Simple to Implement: Iterative algorithms are straightforward to implement and often require less memory than
 * recursive solutions.
 * * Efficient in Time: For many problems, iterative solutions can be more efficient than recursive ones, as they avoid
 * the overhead of function calls.
 * * Control Over Looping: Iterative algorithms give developers more control over the number of iterations, making them
 * well-suited for problems with a clear stopping criterion.
 *
 * # Considerations:
 * * Complexity of Loops: For problems that require multiple nested loops, the time complexity can become high, which
 * may reduce the efficiency of the algorithm.
 * * Limited Applicability: Some problems, particularly those with recursive structures (such as tree traversal),
 * may not be easily adapted into an iterative form.
 *
 * Iterative approaches are often used in sorting algorithms (like bubble sort, insertion sort), searching algorithms
 * (like binary search), and dynamic programming (such as the bottom-up approach for the Fibonacci sequence).
 *
 * @property info An optional string that provides additional information about the Iterative algorithm implementation
 * or usage.
 * @constructor Creates a new Iterative annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Iterative(val info: String = "")
