/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.utils

import dev.shtanko.algorithms.sorts.AbstractSortStrategy
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toTimeUnit

fun measureTime(strategy: AbstractSortStrategy, array: IntArray, task: () -> Unit) {
    val elapsed = measureTime(DurationUnit.MILLISECONDS, task)
    println(
        String.format(
            Locale.getDefault(),
            "Arrays of length %d Strategy %s Consumed time: %d ms",
            array.size,
            strategy::class.java.simpleName,
            elapsed,
        ),
    )
}

fun measureTime(taskName: String, task: () -> Unit) {
    val elapsed = measureTime(DurationUnit.MILLISECONDS, task)
    println(String.format(Locale.getDefault(), "Task %s Consumed time: %d ms", taskName, elapsed))
}

@OptIn(ExperimentalTime::class)
fun measureTime(unit: DurationUnit, task: () -> Unit): Long {
    return unit.toTimeUnit().convert(measureNanoTime(task), TimeUnit.NANOSECONDS)
}
