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

@Suppress("MagicNumber")
class MatrixMultiplicationJob : BenchmarkJob {
    override suspend fun invoke() {
        val size = 1000
        val matrixA = Array(size) { IntArray(size) { (0..100).random() } }
        val matrixB = Array(size) { IntArray(size) { (0..100).random() } }
        val result = Array(size) { IntArray(size) }

        // Multiply matrix A and matrix B
        for (i in 0 until size) {
            for (j in 0 until size) {
                for (k in 0 until size) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j]
                }
            }
        }
    }
}
