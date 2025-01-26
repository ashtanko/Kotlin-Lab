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

package dev.shtanko.patterns.behavioral.observer.example2.editor

import dev.shtanko.patterns.behavioral.observer.example2.publisher.EventManager
import java.io.File
import java.io.IOException

class Editor {
    var events: EventManager = EventManager("open", "save")
    private lateinit var file: File

    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify("open", file)
    }

    @Throws(IOException::class)
    fun saveFile() {
        if (::file.isInitialized) {
            events.notify("save", file)
        } else {
            throw IOException("Please open a file first.")
        }
    }
}
