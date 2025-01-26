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

/**
 * A job that manipulates a string.
 * The job appends the string "Test" to a StringBuilder 100,000 times.
 * The result is not used to avoid dead code elimination.
 * This job is used to measure the overhead of the benchmarking framework.
 */
@Suppress("MagicNumber")
class StringManipulationJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val str = StringBuilder()
        repeat(100_000) {
            str.append("Test")
        }
    }
}
