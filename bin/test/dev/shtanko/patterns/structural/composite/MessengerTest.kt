/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.composite

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MessengerTest {

    private var stdOutBuffer = ByteArrayOutputStream()
    private val realStdOut: PrintStream = System.out

    @BeforeEach
    fun setUp() {
        this.stdOutBuffer = ByteArrayOutputStream()
        System.setOut(PrintStream(stdOutBuffer))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(realStdOut)
    }

    @Test
    internal fun `message from Orcs test`() {
        val messenger = Messenger()
        testMessage(
            messenger.messageFromOrcs(),
            "Where there is a whip there is a way.",
        )
    }

    @Test
    internal fun `message from Elves test`() {
        val messenger = Messenger()
        testMessage(
            messenger.messageFromElves(),
            "Much wind pours from your mouth.",
        )
    }

    private fun testMessage(composedMessage: LetterComposite, message: String) {
        val words = message.split(" ".toRegex())
        assertEquals(words.size, composedMessage.count())
        composedMessage.print()
        assertEquals(message, String(this.stdOutBuffer.toByteArray()).trim())
    }
}
