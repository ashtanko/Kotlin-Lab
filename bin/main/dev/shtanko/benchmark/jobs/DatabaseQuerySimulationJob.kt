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
class DatabaseQuerySimulationJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val data = (1..100_000).map { it * 2 }
        data.filter { it % 3 == 0 }
    }
}
