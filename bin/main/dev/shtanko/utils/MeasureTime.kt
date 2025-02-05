/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.utils

import dev.shtanko.algorithms.MILLISECOND
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

inline fun <T> measureTime(action: () -> T, result: (result: Pair<Long, T>) -> Unit) {
    var a: T
    val time = measureTimeMillis {
        a = action()
    }
    result(time to a)
}

inline fun <T> measureTime(isPrint: Boolean = false, crossinline action: () -> T): Pair<String, T> {
    var result: T
    val nano = measureNanoTime {
        result = action.invoke()
    }
    val millis = TimeUnit.MILLISECONDS.convert(nano, TimeUnit.NANOSECONDS)
    val sec = TimeUnit.SECONDS.convert(nano, TimeUnit.NANOSECONDS)
    val sb = if (sec != 0L) {
        val diff = millis - MILLISECOND
        val sbDiff = if (diff != 0L) {
            "$diff ms"
        } else {
            ""
        }
        "$sec $sbDiff"
    } else {
        "$millis ms"
    }
    val finalStr = "Runtime: $sb"
    if (isPrint) {
        println(finalStr)
    }
    return finalStr to result
}
