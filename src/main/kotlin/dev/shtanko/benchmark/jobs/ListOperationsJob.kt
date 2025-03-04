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
 * Benchmark job that performs list operations.
 * The job creates a list of 100,000 elements, removes the first element, and checks if the list contains an element.
 * The job is used to compare the performance of different implementations of the list data structure.
 * The job is executed multiple times to get an average execution time.
 * The job is executed in a coroutine.
 */
@Suppress("MagicNumber")
class ListOperationsJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val list = mutableListOf<Int>()
        repeat(100_000) {
            list.add(it)
        }
        list.removeAt(0)
        list.contains(5000)
    }
}
