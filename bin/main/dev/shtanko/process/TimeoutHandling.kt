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

package dev.shtanko.process

import java.util.concurrent.TimeUnit

@Suppress("MagicNumber")
fun main() {
    val process = ProcessBuilder("find . -type f -name '*.log' -printf x | wc -c")
        .start()

    val timeoutMillis = 60000 // 60 seconds
    val completed = process.waitFor(timeoutMillis.toLong(), TimeUnit.MILLISECONDS)
    if (completed) {
        println("Process completed within timeout period.")
    } else {
        println("Process did not complete within timeout period.")
        process.destroy()
    }
}
