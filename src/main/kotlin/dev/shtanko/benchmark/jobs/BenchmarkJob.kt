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

package dev.shtanko.benchmark.jobs

/**
 * Benchmark job.
 *
 * This interface is used to define a benchmark job.
 * A benchmark job is a function that performs a specific task.
 */
fun interface BenchmarkJob {

    /**
     * Perform the benchmark job.
     *
     * @param args The arguments to pass to the job.
     */
    suspend operator fun invoke(vararg args: Any?)
}
