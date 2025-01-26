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

package dev.shtanko.patterns.solid.dip.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class DependencyInversionPrincipleTest {
    private val emailSender = EmailSender()
    private val smsSender = SmsSender()

    @Test
    fun testEmailNotification() {
        val notificationService = NotificationService(emailSender)
        notificationService.sendNotification("Hello, this is an email notification.")
        val expectedOutput = "Email: Hello, this is an email notification.\n"
        assertEquals(expectedOutput, emailSender.getOutput())
    }

    @Test
    fun testSmsNotification() {
        val notificationService = NotificationService(smsSender)
        notificationService.sendNotification("Hello, this is an SMS notification.")
        val expectedOutput = "SMS: Hello, this is an SMS notification.\n"
        assertEquals(expectedOutput, smsSender.getOutput())
    }
}
