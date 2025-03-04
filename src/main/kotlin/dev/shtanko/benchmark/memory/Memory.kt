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

package dev.shtanko.benchmark.memory

import dev.shtanko.algorithms.BYTE
import dev.shtanko.benchmark.jobs.HeapSortJob
import dev.shtanko.benchmark.jobs.HeavyCPUJob
import dev.shtanko.benchmark.jobs.PrimeNumberComputationJob
import dev.shtanko.benchmark.jobs.StringManipulationJob
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking

@Suppress("TooGenericExceptionCaught")
fun recordMemoryUsage(runnable: Runnable, runTimeSecs: Int) {
    try {
        val mainProcessFuture = CompletableFuture.runAsync(runnable)
        val memUsageFuture = CompletableFuture.runAsync {
            var mem = 0L
            for (cnt in 0 until runTimeSecs) {
                val memUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
                mem = maxOf(memUsed, mem)
                try {
                    TimeUnit.SECONDS.sleep(1)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            println("Max memory used (bytes): ${mem / BYTE / BYTE}")
        }

        val allOf = CompletableFuture.allOf(mainProcessFuture, memUsageFuture)
        allOf.get()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main() {
    val jobs = listOf(
        "Prime Number Computation" to PrimeNumberComputationJob(),
        "Heap Sort" to HeapSortJob(),
        "Heavy CPU" to HeavyCPUJob(),
        "String Manipulation" to StringManipulationJob(),
    )

    jobs.forEach { (label, job) ->
        recordMemoryUsage(
            kotlinx.coroutines.Runnable {
                runBlocking {
                    println("Starting the $label task...")
                    job()
                    println("Task completed.")
                }
            },
            10,
        )
    }
}
