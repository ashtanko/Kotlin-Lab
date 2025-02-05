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

package dev.shtanko.algorithms.dp

// https://stackoverflow.com/questions/45630776/how-to-achieve-a-pure-function-with-dynamic-programming-in-kotlin
fun fibonacciAt(n: Int) = run {
    tailrec fun fibonacciAcc(n: Int, a: Long, b: Long): Long {
        return when (n == 0) {
            true -> b
            false -> fibonacciAcc(n - 1, a + b, a)
        }
    }
    fibonacciAcc(n, 1, 0)
}

fun fibonacci(): Sequence<Int> {
    // fibonacci terms
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, ...
    return generateSequence(Pair(0, 1)) { it.second to it.first + it.second }.map { it.first }
}
