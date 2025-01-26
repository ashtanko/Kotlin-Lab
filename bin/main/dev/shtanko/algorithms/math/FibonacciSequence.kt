/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.math

fun Int.toFibonacciSequence(): Int {
    if (this <= 1) {
        return this
    }
    return (this - 1).toFibonacciSequence() + (this - 2).toFibonacciSequence()
}

enum class Fibonacci {
    ITERATIVE {
        override fun invoke(n: Long) = if (n < 2) {
            n
        } else {
            var n1 = 0L
            var n2 = 1L
            var i = n
            do {
                val sum = n1 + n2
                n1 = n2
                n2 = sum
            } while (i-- > 1)
            n1
        }
    },
    RECURSIVE {
        override fun invoke(n: Long): Long = if (n < 2) n else this(n - 1) + this(n - 2)
    },
    ;

    abstract operator fun invoke(n: Long): Long
}
