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

@file:Suppress("MagicNumber")

package dev.shtanko.benchmark

import dev.shtanko.benchmark.jobs.ArithmeticJob
import dev.shtanko.benchmark.jobs.BenchmarkJob
import dev.shtanko.benchmark.jobs.ConcurrentJob
import dev.shtanko.benchmark.jobs.DatabaseQuerySimulationJob
import dev.shtanko.benchmark.jobs.FileCompressionJob
import dev.shtanko.benchmark.jobs.FileIOManipulationsJob
import dev.shtanko.benchmark.jobs.HeapSortJob
import dev.shtanko.benchmark.jobs.HeavyCPUJob
import dev.shtanko.benchmark.jobs.JSONJob
import dev.shtanko.benchmark.jobs.ListManipulationsJob
import dev.shtanko.benchmark.jobs.ListOperationsJob
import dev.shtanko.benchmark.jobs.MatrixMultiplicationJob
import dev.shtanko.benchmark.jobs.PrimeNumberComputationJob
import dev.shtanko.benchmark.jobs.SimpleJob
import dev.shtanko.benchmark.jobs.SortingLargeArrayJob
import dev.shtanko.benchmark.jobs.StorageJob
import dev.shtanko.benchmark.jobs.StringManipulationJob
import kotlinx.coroutines.runBlocking

data class BenchmarkConfig(
    val iterations: Int = 10, // Number of iterations for time measurement
    val memorySamples: Int = 5, // Number of samples to take for memory measurement
    val warmUpIterations: Int = 5, // Number of warm-up iterations to run before measurement
)

@Suppress("TooGenericExceptionCaught")
class Benchmark(
    private val measureMemoryUsage: MemoryMeasurer = CoroutinesMemoryMeasurer(),
) {
    suspend operator fun invoke(
        label: String,
        block: suspend () -> Unit,
        config: BenchmarkConfig = BenchmarkConfig(),
    ): BenchmarkResult {
        return try {
            // Measure execution time using measureTimeNanos
            val executionTimeNanos = measureTimeNanos(block, config)

            // Measure memory usage with multiple samples
            val memoryUsage = measureMemoryUsage(block, config)

            println("$label:")
            println("Execution time: ${executionTimeNanos / 1_000_000} ms ($executionTimeNanos ns)")
            println("Memory usage: $memoryUsage bytes")
            println("---------------")

            return BenchmarkResult.Success(label, executionTimeNanos, memoryUsage)
        } catch (e: Exception) {
            return BenchmarkResult.Error(e.message ?: "Unknown error")
        }
    }

    private suspend fun measureTimeNanos(block: suspend () -> Unit, config: BenchmarkConfig): Long {
        // Warm up the JVM with a no-op
        repeat(config.warmUpIterations) { block() }

        var totalNanos = 0L
        repeat(config.iterations) {
            totalNanos += System.nanoTime().let { start ->
                block()
                System.nanoTime() - start
            }
        }

        return totalNanos / config.iterations
    }

    suspend fun runBenchmarks(
        benchmarks: List<Pair<String, BenchmarkJob>>,
        config: BenchmarkConfig = BenchmarkConfig(),
    ): List<BenchmarkResult> {
        return benchmarks.map { (label, block) -> invoke(label, block::invoke, config) }
    }
}

fun interface MemoryMeasurer {
    operator fun invoke(block: suspend () -> Unit, config: BenchmarkConfig): Long
}

class CoroutinesMemoryMeasurer : MemoryMeasurer {
    private val runtime = Runtime.getRuntime()

    override fun invoke(block: suspend () -> Unit, config: BenchmarkConfig): Long {
        val memorySamples = mutableListOf<Long>()
        repeat(config.memorySamples) { // Measure over multiple samples
            System.gc() // Trigger GC
            val startMemory = runtime.totalMemory() - runtime.freeMemory()

            runBlocking { block() }

            System.gc() // Trigger GC again
            val endMemory = runtime.totalMemory() - runtime.freeMemory()
            memorySamples.add(endMemory - startMemory)
        }

        return memorySamples.average().toLong()
    }
}

fun interface BenchmarkRunner {
    operator fun invoke(
        benchmarks: List<Pair<String, BenchmarkJob>>,
        config: BenchmarkConfig,
    )
}

class SimpleBenchmarkRunner : BenchmarkRunner {
    override fun invoke(
        benchmarks: List<Pair<String, BenchmarkJob>>,
        config: BenchmarkConfig,
    ) {
        TODO("Not yet implemented")
    }
}

sealed class BenchmarkResult {
    data class Success(
        val label: String,
        val executionTimeNanos: Long,
        val memoryUsage: Long,
    ) : BenchmarkResult()

    data class Error(val message: String) : BenchmarkResult()
}

suspend fun main() {
    val benchmark = Benchmark()

    val jobs = listOf<Pair<String, BenchmarkJob>>(
        "Single Task" to SimpleJob(),
        "Concurrent Task" to ConcurrentJob(),
        "String Manipulation" to StringManipulationJob(),
        "Sorting Large Array" to SortingLargeArrayJob(),
        "List Operations" to ListOperationsJob(),
        "File I/O Operation" to FileIOManipulationsJob(),
        "Database Query Simulation" to DatabaseQuerySimulationJob(),
        "Heavy CPU Task" to HeavyCPUJob(),
        "Prime Number Computation" to PrimeNumberComputationJob(),
        "Matrix Multiplication" to MatrixMultiplicationJob(),
        "JSON Serialization/Deserialization" to JSONJob(),
        "Heap Sort" to HeapSortJob(),
        "List Manipulation" to ListManipulationsJob(),
        "File Compression" to FileCompressionJob(),
        "Storage Job" to StorageJob(),
        "Arithmetic Job" to ArithmeticJob(),
    )

    val results = benchmark.runBenchmarks(
        jobs,
        config = BenchmarkConfig(
            iterations = 10,
            memorySamples = 5,
            warmUpIterations = 5,
        ),
    )
    results.forEach { result ->
        when (result) {
            is BenchmarkResult.Error -> {
                println("Error: ${result.message}")
                println("---------------")
            }

            is BenchmarkResult.Success -> {
                println("Label: ${result.label}")
                println("Execution Time: ${result.executionTimeNanos / 1_000_000} ms (${result.executionTimeNanos} ns)")
                println("Memory Usage: ${result.memoryUsage} bytes")
                println("---------------")
            }
        }
    }
}
