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

package dev.shtanko.patterns.creational.abstractfactory.examples.gui

import java.util.Locale

internal fun configureApplication(osName: String): Application {
    val app: Application
    val factory: GUIFactory = if (osName.contains("mac")) {
        MacOSFactory()
    } else {
        WindowsFactory()
    }
    app = Application(factory)
    return app
}

object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
        val app = configureApplication(osName)
        app.paint()
    }
}
