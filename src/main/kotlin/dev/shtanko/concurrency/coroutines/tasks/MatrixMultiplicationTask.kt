package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.yield

class MatrixMultiplicationTask(
    private val size: Int = 300,
) : BaseTask<Int>(
    name = "Matrix Multiplication",
    description = "${size}x$size matrix multiplication",
) {
    override suspend fun execute(): Int {
        val matrixA = Array(size) { IntArray(size) { Random.nextInt(1, 10) } }
        val matrixB = Array(size) { IntArray(size) { Random.nextInt(1, 10) } }
        val result = Array(size) { IntArray(size) }

        val totalOperations = size * size
        var completed = 0

        for (i in 0 until size) {
            yield()

            for (j in 0 until size) {
                for (k in 0 until size) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j]
                }
                completed++
                if (completed % 100 == 0) {
                    updateProgress(completed.toFloat() / totalOperations)
                }
            }
        }

        // Return sum of diagonal elements as result
        return (0 until size).sumOf { result[it][it] }
    }
}
