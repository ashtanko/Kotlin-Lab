/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.algorithms.gfg

/**
 * Number that are not divisible
 */
fun interface NotDivisibleNumber {
    operator fun invoke(num: Long): Long
}

class NotDivisibleNumberBruteForce : NotDivisibleNumber {
    override fun invoke(num: Long): Long {
        val primes = prime.sumOf { num / it }
        val a = numbersA.sumOf { num / it }
        val b = numbersB.sumOf { num / it } - num / LAST
        return num - (primes - a + b)
    }

    companion object {
        private val prime = listOf(2, 3, 5, 7)
        private val numbersA = listOf(6, 10, 14, 15, 21, 35)
        private val numbersB = listOf(30, 105, 70, 42)
        private const val LAST = 210L
    }
}
