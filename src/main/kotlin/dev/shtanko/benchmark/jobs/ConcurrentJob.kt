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

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Concurrent job.
 * This job launches 100 coroutines, each of which performs 10,000 iterations.
 * The job is expected to complete in a reasonable amount of time.
 * The job is used to test the performance of the benchmarking framework.
 */
@Suppress("MagicNumber")
class ConcurrentJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) = coroutineScope {
        repeat(100) {
            launch {
                repeat(10_000) { it * it }
            }
        }
    }
}
