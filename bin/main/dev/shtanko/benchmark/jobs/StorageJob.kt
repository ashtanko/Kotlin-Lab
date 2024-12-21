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

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.LockSupport

@Suppress("MagicNumber")
class StorageJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val seed = 100 // todo
        val timeInMicroseconds = 500 + 10 * seed.toLong()
        LockSupport.parkNanos(TimeUnit.MICROSECONDS.toNanos(timeInMicroseconds))
    }
}
