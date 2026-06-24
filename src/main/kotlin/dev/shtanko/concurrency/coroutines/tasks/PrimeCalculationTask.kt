@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.yield

class PrimeCalculationTask(
    private val limit: Int = 100_000,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : BaseTask<List<Int>>(
    name = "Prime Calculator",
    description = "Finding prime numbers up to $limit",
    dispatcher = dispatcher,
) {
    override suspend fun execute(): List<Int> {
        val primes = mutableListOf<Int>()

        for (num in 2..limit) {
            if (num % 1000 == 0) {
                yield()
                updateProgress(num.toFloat() / limit)
            }

            if (isPrime(num)) {
                primes.add(num)
            }
        }

        return primes
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        if (n <= 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false

        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }
}
