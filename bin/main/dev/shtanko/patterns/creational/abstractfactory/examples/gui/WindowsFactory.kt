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

import dev.shtanko.patterns.creational.abstractfactory.examples.gui.buttons.Button
import dev.shtanko.patterns.creational.abstractfactory.examples.gui.buttons.WindowsButton
import dev.shtanko.patterns.creational.abstractfactory.examples.gui.checkboxes.Checkbox
import dev.shtanko.patterns.creational.abstractfactory.examples.gui.checkboxes.WindowsCheckbox

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
class WindowsFactory : GUIFactory {
    override fun createButton(): Button = WindowsButton()

    override fun createCheckbox(): Checkbox = WindowsCheckbox()
}
