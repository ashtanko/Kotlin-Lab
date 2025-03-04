/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.concurrency

import java.util.concurrent.Executors
import kotlin.collections.indices
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun interface MatrixMultiplyStrategy {
    operator fun invoke(a: List<List<Int>>, b: List<List<Int>>): List<List<Int>>
}

class MatrixMultiplyBruteForce : MatrixMultiplyStrategy {
    override fun invoke(
        a: List<List<Int>>,
        b: List<List<Int>>,
    ): List<List<Int>> {
        val m = a.size // Number of rows in matrix `a`
        val n = b.size // Number of columns in `a` (or rows in `b`)
        val p = b[0].size // Number of columns in matrix `b`
        // Initialize the result matrix with zeros
        val result = MutableList(m) { MutableList(p) { 0 } }

        // Perform the standard matrix multiplication
        for (i in 0 until m) {
            for (j in 0 until p) {
                for (k in 0 until n) {
                    result[i][j] += a[i][k] * b[k][j]
                }
            }
        }
        return result
    }
}

class MatrixMultiplyTiles : MatrixMultiplyStrategy {

    companion object {
        // Constant defining the tile size for matrix tiling
        const val TILE_SIZE = 500
    }

    override fun invoke(
        a: List<List<Int>>,
        b: List<List<Int>>,
    ): List<List<Int>> {
        val m = a.size // Number of rows in matrix `a`
        val n = b.size // Number of rows in matrix `b` (or columns in `a`)
        val p = b[0].size // Number of columns in matrix `b`
        val executor = Executors.newCachedThreadPool() // Thread pool for parallelism

        // List to store the deferred results of each tile multiplication
        val deferredResults = mutableListOf<Deferred<List<List<Int>>>>()

        // Loop through the matrix in tile-sized steps
        for (i in 0 until m step TILE_SIZE) {
            for (j in 0 until p step TILE_SIZE) {
                // Launch a coroutine to multiply each tile asynchronously
                deferredResults.add(
                    GlobalScope.async(Dispatchers.Default) {
                        multiplyMatrixTiles(a, b, m, n, p, i, j)
                    },
                )
            }
        }

        print("deferredResults: ${deferredResults.size}\n")

        // Initialize the result matrix filled with zeros
        val result = MutableList(m) { MutableList(p) { 0 } }

        // Run blocking operation to wait for all coroutines to complete
        runBlocking {
            deferredResults.forEachIndexed { index, deferred ->
                // Await the result of the current tile multiplication
                val partialResult = deferred.await()
                // Calculate the position of the tile in the result matrix
                val tileRow = index / ((m + TILE_SIZE - 1) / TILE_SIZE)
                val tileCol = index % ((p + TILE_SIZE - 1) / TILE_SIZE)

                // Copy the partial results into the appropriate location in the result matrix
                for (i in partialResult.indices) {
                    for (j in partialResult[i].indices) {
                        if (tileRow * TILE_SIZE + i < m && tileCol * TILE_SIZE + j < p) {
                            result[tileRow * TILE_SIZE + i][tileCol * TILE_SIZE + j] = partialResult[i][j]
                        }
                    }
                }
            }
        }

        executor.shutdown() // Shut down the thread pool
        return result
    }

    /**
     * Multiplies tiles of two matrices.
     * @param a The first matrix (List of Lists of Int).
     * @param b The second matrix (List of Lists of Int).
     * @param m The number of rows in matrix `a`.
     * @param n The number of columns in matrix `a` (also the number of rows in matrix `b`).
     * @param p The number of columns in matrix `b`.
     * @param iStart The starting row index for tiling in matrix `a`.
     * @param jStart The starting column index for tiling in matrix `b`.
     * @return The output matrix tile after multiplication.
     */
    private fun multiplyMatrixTiles(
        a: List<List<Int>>,
        b: List<List<Int>>,
        m: Int,
        n: Int,
        p: Int,
        iStart: Int,
        jStart: Int,
    ): List<List<Int>> {
        // Initialize an output matrix tile of size TILE_SIZE x TILE_SIZE filled with zeros
        val outputMatrix = MutableList(TILE_SIZE) { MutableList(TILE_SIZE) { 0 } }

        // Iterate over the tile's rows and columns
        for (i in 0 until TILE_SIZE) {
            for (j in 0 until TILE_SIZE) {
                // Check if the current index is within the bounds of matrix `a` and `b`
                if (iStart + i < m && jStart + j < p) {
                    // Multiply and accumulate the values for the current cell
                    for (k in 0 until n) {
                        outputMatrix[i][j] += a[iStart + i][k] * b[k][jStart + j]
                    }
                }
            }
        }
        return outputMatrix
    }
}

class MatrixMultiplyParallelism : MatrixMultiplyStrategy {
    override fun invoke(
        a: List<List<Int>>,
        b: List<List<Int>>,
    ): List<List<Int>> {
        val m = a.size // Number of rows in matrix `a`
        val n = b.size // Number of columns in `a` (or rows in `b`)
        val p = b[0].size // Number of columns in matrix `b`

        return runBlocking {
            // Launch coroutines for each row of the result matrix
            (0 until m).map { i ->
                async(Dispatchers.Default) {
                    // Compute each element in the row in parallel
                    List(p) { j ->
                        (0 until n).sumOf { k -> a[i][k] * b[k][j] }
                    }
                }
            }.map { it.await() } // Wait for all computations to complete and collect results
        }
    }
}

fun transpose(matrix: List<List<Int>>): List<List<Int>> {
    val rows = matrix.size
    val cols = matrix[0].size
    // Create a matrix with swapped dimensions
    val matrixTranspose = MutableList(cols) { MutableList(rows) { 0 } }

    // Iterate over each cell and assign the transposed value
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            matrixTranspose[col][row] = matrix[row][col]
        }
    }

    return matrixTranspose
}

private const val MATRIX_COUNT = 1000

fun main() {
    val rowsA = MATRIX_COUNT
    val colsA = MATRIX_COUNT
    val rowsB = MATRIX_COUNT
    val colsB = MATRIX_COUNT

    val a = List(rowsA) { List(colsA) { Random.nextInt(1, 100) } }
    val b = List(rowsB) { List(colsB) { Random.nextInt(1, 100) } }

    val sequentialTime = measureTimeMillis {
        val c = MatrixMultiplyBruteForce()(a, b)
        println("Sequential completed")
    }
    println("Time duration for sequential: $sequentialTime ms")

    val noBufferTime = measureTimeMillis {
        val cUnbuffered = MatrixMultiplyBruteForce()(a, b)
        println("No buffer completed")
    }
    println("Time duration for no buffer: $noBufferTime ms")

    val transposeTime = measureTimeMillis {
        val bTranspose = transpose(b)
        val cTranspose = MatrixMultiplyBruteForce()(a, bTranspose)
        println("Transpose completed")
    }
    println("Time duration for transpose: $transposeTime ms")

    val parallelTime = measureTimeMillis {
        val cParallel = MatrixMultiplyParallelism()(a, b)
        println("Parallel completed")
    }
    println("Time duration for parallel: $parallelTime ms")

    val tiledTime = measureTimeMillis {
        val cTiled = MatrixMultiplyTiles()(a, b)
        println("Tiled completed")
    }
    println("Time duration for tiled: $tiledTime ms")
}
