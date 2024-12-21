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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ApplicationTest {

    @Test
    fun `MacOSFactory test`() {
        val app = Application(MacOSFactory())
        assertThat(app.paint()).contains("MacOSButton", "MacOSCheckbox")
        assertThat(app.paint()).doesNotContain("WindowsButton", "WindowsCheckbox")
    }

    @Test
    fun `WindowsFactory test`() {
        val app = Application(WindowsFactory())
        assertThat(app.paint()).contains("WindowsButton", "WindowsCheckbox")
        assertThat(app.paint()).doesNotContain("MacOSButton", "MacOSCheckbox")
    }

    @Test
    fun `configure application for mac test`() {
        val app = configureApplication("mac")
        assertThat(app.paint()).contains("MacOSButton", "MacOSCheckbox")
    }

    @Test
    fun `configure application for windows test`() {
        val app = configureApplication("windows")
        assertThat(app.paint()).contains("WindowsButton", "WindowsCheckbox")
    }

    @Test
    fun `configure application for other test`() {
        val app = configureApplication("other")
        assertThat(app.paint()).contains("WindowsButton", "WindowsCheckbox")
    }

    @Test
    fun `configure application for empty test`() {
        val app = configureApplication("")
        assertThat(app.paint()).contains("WindowsButton", "WindowsCheckbox")
    }
}
