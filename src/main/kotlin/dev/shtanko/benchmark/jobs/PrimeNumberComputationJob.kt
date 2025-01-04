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

package dev.shtanko.benchmark.jobs

import kotlin.math.sqrt

/**
 * A job that computes prime numbers up to a given limit using the Sieve of Eratosthenes algorithm.
 * The limit is set to 10,000,000.
 * The algorithm is implemented using a boolean array to store the sieve.
 */
@Suppress("MagicNumber")
class PrimeNumberComputationJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val limit = 10_000_000
        val sieve = BooleanArray(limit + 1) { true }
        sieve[0] = false
        sieve[1] = false
        for (i in 2..sqrt(limit.toDouble()).toInt()) {
            if (sieve[i]) {
                for (j in i * i..limit step i) {
                    sieve[j] = false
                }
            }
        }
        // Collect primes from the sieve
        val primes = mutableListOf<Int>()
        for (i in 2..limit) {
            if (sieve[i]) {
                primes.add(i)
            }
        }
    }
}
