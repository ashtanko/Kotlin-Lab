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
 * The Math annotation is used to indicate that a class, function, or property implements or utilizes mathematical
 * concepts, algorithms, or operations. This annotation is often used to mark code related to mathematical computations,
 * algorithms, or optimizations in various domains such as algebra, geometry, number theory, statistics, and more.
 *
 * # How It Works
 *
 * 1: Mathematical Operations:
 * The Math annotation is applied to classes, functions, or properties that perform or define mathematical operations,
 * such as arithmetic calculations, solving equations, matrix operations, or other algorithmic solutions based on math
 * principles.
 *
 * 2: Mathematical Algorithms:
 * Functions annotated with `@Math` might include well-known mathematical algorithms such as sorting algorithms,
 * optimization techniques, finding the greatest common divisor (GCD), matrix multiplication, or calculating prime
 * numbers.
 *
 * Example (Math: Computing the Greatest Common Divisor - Euclidean Algorithm):
 * The following function computes the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
 *
 * ```kotlin
 * fun gcd(a: Int, b: Int): Int {
 *     var x = a
 *     var y = b
 *     while (y != 0) {
 *         val temp = y
 *         y = x % y
 *         x = temp
 *     }
 *     return x
 * }
 * ```
 *
 * 3: Application in Scientific Computations:
 * Mathematical operations are foundational for fields such as physics, engineering, computer science, economics,
 * cryptography, and machine learning. The `@Math` annotation helps highlight the code that deals with complex
 * mathematical models.
 *
 * # Advantages:
 * * Clarifies Mathematical Relevance: The `@Math` annotation explicitly marks code related to mathematical operations
 * or algorithms.
 * * Documentation: It can serve as documentation for developers to understand that the code involves mathematical
 * computation.
 * * Modular Design: By identifying mathematical functions or classes with this annotation, code related to math can
 * be organized for better maintainability and clarity.
 *
 * # Considerations:
 * * Overuse: Applying the `@Math` annotation too broadly may reduce its utility. It should be used for code that
 * directly deals with mathematical logic or computation.
 * * Complexity: The mathematical operations or algorithms implemented might introduce high time complexity or memory
 * usage.
 *
 * Mathematical operations and algorithms form the core of many technical fields. By using the `@Math` annotation,
 * developers can explicitly mark functions or classes that are designed for solving mathematical problems.
 *
 * @property info An optional string that provides additional information about the mathematical concepts or algorithms
 * implemented by the annotated code.
 * @constructor Creates a new Math annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Math(val info: String = "")
