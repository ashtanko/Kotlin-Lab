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

import java.util.Locale

/**
 * Measures memory usage
 * @param task
 * @return consumed memory in bytes
 */
inline fun <T> measureMemWithResult(task: () -> T): Pair<Long, T> {
    val beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
    val res = task.invoke()
    val afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
    val consumed = afterUsedMem - beforeUsedMem
    return consumed to res
}

inline fun <T> measureMemFormatted(taskName: String = "", task: () -> T): Pair<String, T> {
    val (bytes, res) = measureMemWithResult(task)
    val consumed = bytes.toHumanReadableByteCountBin()
    val s = if (taskName.isEmpty()) {
        String.format(Locale.getDefault(), "Memory Usage: %s", consumed)
    } else {
        String.format(Locale.getDefault(), " Memory Usage: task: %s consumed %s", taskName, consumed)
    }
    return s to res
}
