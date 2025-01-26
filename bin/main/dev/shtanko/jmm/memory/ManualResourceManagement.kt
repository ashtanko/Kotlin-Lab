/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.jmm.memory

import java.io.FileInputStream

/**
 * Kotlin provides the use function for managing resources like streams, which automatically closes the resource
 * when it's no longer needed.
 */
@Suppress("unused", "UNUSED_VARIABLE")
fun readFromFile(filePath: String) {
    FileInputStream(filePath).use { stream ->
        val data = stream.readBytes()
        // 'stream' is automatically closed when the block is exited
    }
}
