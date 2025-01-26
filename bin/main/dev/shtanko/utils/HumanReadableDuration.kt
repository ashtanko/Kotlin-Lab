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

fun Long.toHumanReadableDuration(): String {
    val millis = TimeUnit.MILLISECONDS.convert(this, TimeUnit.NANOSECONDS)
    val sec = TimeUnit.SECONDS.convert(this, TimeUnit.NANOSECONDS)
    val sb = if (sec != 0L) {
        val diff = millis - MILLISECOND * sec
        val sbDiff = if (diff != 0L) {
            "$diff ms"
        } else {
            ""
        }
        "$sec,$sbDiff"
    } else {
        "$millis ms"
    }
    return "Runtime: $sb"
}
