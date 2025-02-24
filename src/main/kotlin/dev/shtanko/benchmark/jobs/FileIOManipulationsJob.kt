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

import java.io.File

/**
 * This job benchmarks file I/O manipulations.
 * It writes a text to a file, reads it, and then deletes the file.
 * The benchmark measures the time it takes to perform these operations.
 */
class FileIOManipulationsJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val file = File("benchmark_test.txt")
        file.writeText("This is a test file for benchmarking.")
        file.readText()
        file.delete()
    }
}
