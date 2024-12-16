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
 * The TailRec annotation is used to indicate that a function is implemented using tail recursion, which is a specific
 * form of recursion where the recursive call is the last operation performed in the function. Tail recursion allows
 * for optimization by the compiler into a loop, preventing stack overflow errors and improving performance.
 *
 * # How It Works
 *
 * Tail recursion occurs when the recursive function call is the last statement in the function. In this case, the
 * compiler can optimize the function by reusing the current stack frame for the next recursive call, instead of
 * creating a new one. This optimization is known as tail call optimization (TCO) or tail call elimination, and it can
 * greatly reduce memory usage for deep recursion.
 *
 * Here's an example of a tail-recursive function that calculates the factorial of a number:
 *
 * ```kotlin
 * fun factorial(n: Int, accumulator: Int = 1): Int {
 *     return if (n == 0) accumulator else factorial(n - 1, n * accumulator)
 * }
 * ```
 *
 * In the above function, the recursive call `factorial(n - 1, n * accumulator)` is the last operation performed,
 * making it tail-recursive.
 * This allows the compiler to optimize it and avoid stack overflow even for large values of `n`.
 *
 * # Advantages:
 * * **Stack Safety**: Tail recursion avoids stack overflow by reusing stack frames, making it safe for deep recursion.
 * * **Optimized Performance**: Tail call optimization can significantly improve performance by reducing the overhead
 * of recursive calls.
 * * **Readable and Concise Code**: Tail-recursive functions can often be written in a simple and elegant manner
 * while maintaining efficiency.
 *
 * # Disadvantages:
 * * **Compiler Support Required**: Tail call optimization depends on the compiler, and not all compilers or
 * languages perform TCO.
 * * **Limited to Tail Calls**: Tail recursion only works when the recursive call is the last operation, so some
 * algorithms may not be suitable for tail recursion.
 *
 * Tail recursion is particularly useful in functional programming and other contexts where recursion is preferred
 * over iteration.
 *
 * @property info An optional string that provides additional information about the tail-recursive function
 * or its usage.
 * @constructor Creates a new TailRec annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class TailRec(val info: String = "")
