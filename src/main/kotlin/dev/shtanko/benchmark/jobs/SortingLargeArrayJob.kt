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
 * A job that sorts a large array of random integers.
 * The array has 100,000 elements.
 * The array is sorted using the built-in sorting algorithm.
 * The result is not used to avoid dead code elimination.
 * This job is used to measure the overhead of the benchmarking framework.
 * The job is executed in a coroutine.
 */
@Suppress("MagicNumber")
class SortingLargeArrayJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val arr = IntArray(100_000) { (0..100).random() }
        arr.sort()
    }
}
