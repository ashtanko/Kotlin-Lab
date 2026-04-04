@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.yield

class HashComputationTask(
    private val iterations: Int = 1_000_000,
) : BaseTask<String>(
    name = "Hash Computation",
    description = "Computing $iterations hash iterations",
) {
    override suspend fun execute(): String {
        var hash = "initial".hashCode()

        for (i in 0 until iterations) {
            if (i % 10_000 == 0) {
                yield()
                updateProgress(i.toFloat() / iterations)
            }

            hash = computeHash(hash, i)
        }

        return hash.toString(16).uppercase()
    }

    private fun computeHash(seed: Int, iteration: Int): Int {
        var result = seed
        result = result xor iteration
        result = result * 31 + iteration
        result = result.rotateLeft(7)
        result = result xor (result shr 16)
        return result
    }
}
