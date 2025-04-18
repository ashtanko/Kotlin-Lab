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

package dev.shtanko.process

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val process = ProcessBuilder("ls", "-l")
        .start()

    val reader = BufferedReader(InputStreamReader(process.inputStream))
    var line: String? = null
    while (run {
            line = reader.readLine()
            line
        } != null
    ) {
        println(line)
    }

    val exitCode = process.waitFor()
    println("Process exited with code: $exitCode")
}
