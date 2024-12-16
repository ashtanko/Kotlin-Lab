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
 * The StraightForward annotation is used to indicate that a class or function implements a straightforward or naive
 * solution to a problem, without the use of advanced optimizations or complex algorithms. This type of approach is
 * typically simple to understand and implement, but may not be the most efficient for large or complex problems.
 *
 * # How It Works
 *
 * A straightforward solution to a problem typically involves directly applying the most basic or intuitive method
 * to solve it, often without consideration for optimizing performance or reducing time complexity. These solutions are
 * often easy to implement, but may not scale well for larger inputs or more complex scenarios.
 *
 * For example, solving a problem by brute-force, where every possible solution is checked, is often considered a
 * straightforward approach. Although this method may work for small inputs, it may be inefficient for larger datasets
 * due to its high time complexity.
 *
 * # Example:
 * A simple implementation of the sum of all elements in an array using a straightforward approach:
 *
 * ```kotlin
 * fun sumOfElements(arr: IntArray): Int {
 *     var sum = 0
 *     for (element in arr) {
 *         sum += element
 *     }
 *     return sum
 * }
 * ```
 *
 * The above code uses a basic loop to sum all elements of an array, and it represents a straightforward solution to
 * the problem.
 * However, if more complex constraints were added (e.g., querying sums over multiple subarrays), a more efficient
 * method like the Prefix Sum approach would be considered instead.
 *
 * # Advantages:
 * * **Simple to Implement**: The code for a straightforward approach is typically easy to understand and implement,
 * even for beginners.
 * * **Easy to Understand**: The logic behind the solution is clear and can be easily explained.
 *
 * # Disadvantages:
 * * **Inefficient for Large Inputs**: Straightforward approaches may not be optimized for larger or more complex
 * datasets, resulting in higher time and space complexity.
 * * **Lack of Scalability**: As the problem size grows, straightforward solutions may become impractical or too slow.
 *
 * In many cases, the straightforward approach is a good starting point, especially when the problem is small, simple,
 * or the goal is to prototype a solution quickly. However, for larger problems, more efficient algorithms and
 * optimizations are often necessary.
 *
 * @property info An optional string that provides additional information about the straightforward solution or its
 * usage.
 * @constructor Creates a new StraightForward annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class StraightForward(val info: String = "")
