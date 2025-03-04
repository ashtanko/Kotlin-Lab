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

package dev.shtanko.utils

import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

typealias ResourceFileName = String

/**
 * Reads the content of a JSON file from the resources directory.
 *
 * @return The content of the JSON file as a string, or null if the file is not found.
 */
fun ResourceFileName.readJsonFromResource(): String? {
    val resourceStream: InputStream? = object {}.javaClass.getResourceAsStream("/$this")
    return if (resourceStream != null) {
        InputStreamReader(resourceStream, StandardCharsets.UTF_8).use { reader ->
            reader.readText()
        }
    } else {
        null
    }
}
