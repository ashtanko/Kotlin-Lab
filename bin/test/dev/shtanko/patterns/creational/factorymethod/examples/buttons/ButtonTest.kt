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

package dev.shtanko.patterns.creational.factorymethod.examples.buttons

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ButtonTest {

    private lateinit var windowsButton: WindowsButton
    private lateinit var htmlButton: HtmlButton
    private lateinit var outContent: ByteArrayOutputStream
    private lateinit var originalOut: PrintStream

    @BeforeEach
    fun setUp() {
        windowsButton = WindowsButton()
        htmlButton = HtmlButton()
        outContent = ByteArrayOutputStream()
        originalOut = System.out
        System.setOut(PrintStream(outContent))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
    }

    @Test
    fun testWindowsButtonRender() {
        windowsButton.render() // Calls println
        val output = outContent.toString().trim() // Get the console output
        assertEquals("Windows Button", output, "Render output should be 'Windows Button'")
    }

    @Test
    fun testWindowsButtonOnClick() {
        windowsButton.onClick() // Calls println
        val output = outContent.toString().trim() // Get the console output
        assertEquals("On Click", output, "OnClick output should be 'On Click'")
    }

    @Test
    fun `html button test`() {
        val btn: Button = HtmlButton()
        assertThat(btn.render()).isEqualTo("<button>Test Button</button>")
    }

    @Test
    fun testHtmlButtonOnClick() {
        htmlButton.onClick() // Calls println
        val output = outContent.toString().trim() // Get the console output
        assertEquals("Click! Button says - 'Hello World!'", output)
    }

    @Test
    fun `windows button test`() {
        val btn: Button = WindowsButton()
        assertThat(btn.render()).isEqualTo("Windows Button")
    }
}
