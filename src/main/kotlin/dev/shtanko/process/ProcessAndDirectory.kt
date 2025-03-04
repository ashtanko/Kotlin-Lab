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

import java.io.File

fun main() {
    val projectPath = System.getProperty("user.dir")
    val process = ProcessBuilder("ls", "-l")
        .directory(File("$projectPath/src"))
        .inheritIO() // Redirects the subprocess's standard input, output, and error streams to the current Java process
        .start()
    val exitCode = process.waitFor() // Waits for the process to terminate and returns its exit code.
    println("Process exited with code: $exitCode")
}
