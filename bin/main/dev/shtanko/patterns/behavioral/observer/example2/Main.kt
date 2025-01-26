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

package dev.shtanko.patterns.behavioral.observer.example2

import dev.shtanko.patterns.behavioral.observer.example2.editor.Editor
import dev.shtanko.patterns.behavioral.observer.example2.listeners.EmailNotificationListener
import dev.shtanko.patterns.behavioral.observer.example2.listeners.LogOpenListener
import java.io.IOException

fun main() {
    val editor = Editor()
    editor.events.subscribe("open", LogOpenListener("/path/to/log/file.txt"))
    editor.events.subscribe("save", EmailNotificationListener("admin@example.com"))

    try {
        editor.openFile("test.txt")
        editor.saveFile()
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        editor.events.unsubscribe("open") { eventType, file -> println("Unsubscribed $eventType $file") }
        editor.events.unsubscribe("save") { eventType, file -> println("Unsubscribed $eventType $file") }
    }
}
