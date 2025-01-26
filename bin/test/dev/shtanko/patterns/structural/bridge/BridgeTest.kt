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

package dev.shtanko.patterns.structural.bridge

import dev.shtanko.patterns.structural.bridge.example.EmailMessage
import dev.shtanko.patterns.structural.bridge.example.EmailMessageSender
import dev.shtanko.patterns.structural.bridge.example.TextMessage
import dev.shtanko.patterns.structural.bridge.example.TextMessageSender
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BridgeTest {
    @Test
    fun `simple test`() {
        val textMessageSender = TextMessageSender()
        val textMessage = TextMessage(textMessageSender)
        assertEquals("text", textMessage.send())

        val emailMessageSender = EmailMessageSender()
        val emailMessage = EmailMessage(emailMessageSender)
        assertEquals("email", emailMessage.send())
    }
}
