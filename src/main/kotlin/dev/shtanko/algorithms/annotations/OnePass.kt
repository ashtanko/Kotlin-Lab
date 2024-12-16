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
 * The OnePass annotation is used to indicate that a class or function implements an algorithm or solution that
 * processes data in a single pass, i.e., it performs the necessary operations or computations in a single iteration
 * over the data.
 *
 * # How It Works
 *
 * 1: Single Pass Computation:
 * The OnePass technique is typically applied in scenarios where the problem can be solved in a single traversal of the
 * input data without requiring multiple iterations or reprocessing of the data.
 * For example, a solution might involve finding the maximum value, sum, or other aggregates in a single pass through
 * an array.
 *
 * 2: Efficiency:
 * OnePass algorithms are often highly efficient because they minimize the amount of work done by processing the
 * data only once.
 * They are particularly useful in cases where both time and space complexity need to be optimized.
 *
 * Example (OnePass: Finding the Maximum in an Array):
 * The following function finds the maximum element in an array in one pass.
 *
 * ```kotlin
 * fun findMax(arr: IntArray): Int {
 *     var max = arr[0]
 *     for (num in arr) {
 *         if (num > max) {
 *             max = num
 *         }
 *     }
 *     return max
 * }
 * ```
 *
 * 3: Application in Real-World Problems:
 * Many real-world problems can benefit from OnePass algorithms, such as processing large datasets, stream processing,
 * or analyzing data where multiple passes would be too slow or require too much memory.
 *
 * # Advantages:
 * * Time Efficiency: OnePass algorithms are usually faster because they process the data in a single traversal.
 * * Space Efficiency: Since they avoid multiple iterations or the need for extra data structures, OnePass solutions are
 * often space-efficient.
 * * Simplicity: Often, OnePass solutions are simpler to implement, as they reduce the need for nested loops or
 * complicated data structures.
 *
 * # Considerations:
 * * Limited Applicability: The OnePass approach is effective only when the problem can be solved in a single iteration.
 * In cases where multiple passes over the data are required, it may not be applicable.
 * * Complexity: Although a single pass is often optimal, designing a OnePass solution may require careful thought,
 * especially when dealing with complex data transformations.
 *
 * OnePass algorithms are a powerful tool for optimizing performance when dealing with large datasets, providing both
 * time and space efficiency in situations where a single pass can solve the problem.
 *
 * @property info An optional string that provides additional information about the OnePass algorithm or its usage.
 * @constructor Creates a new OnePass annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnePass(val info: String = "")
