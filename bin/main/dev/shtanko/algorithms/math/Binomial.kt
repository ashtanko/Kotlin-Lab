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

/**
 * Binomial Coefficient
 * A binomial coefficient C(n, k) can be defined as the coefficient of X^k in the expansion of (1 + X)^n.
 * A binomial coefficient C(n, k) also gives the number of ways, disregarding order,
 * that k objects can be chosen from among n objects; more formally, the number of k-element subsets
 * (or k-combinations) of an n-element set.
 */
fun binomial(n: Int, k: Int): Long {
    var j = n - k + 1
    var binomial = 1L
    for (i in 1 until k + 1) {
        binomial = binomial * j / i
        j++
    }
    return binomial
}
