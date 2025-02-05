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

package dev.shtanko.grasp.lc.example1

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class UserNotificationTest {
    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @Test
    fun testNotifyUserWithEmailSender() {
        System.setOut(PrintStream(outputStreamCaptor))

        val emailSender = EmailSender()
        val userNotification = UserNotification(emailSender)
        userNotification.notifyUser("Test email notification")

        val expectedOutput = "Sending email: Test email notification\n"
        val actualOutput = outputStreamCaptor.toString().replace("\r\n", "\n")
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testNotifyUserWithSMSMessageSender() {
        System.setOut(PrintStream(outputStreamCaptor))

        val smsSender = SMSMessageSender()
        val userNotification = UserNotification(smsSender)
        userNotification.notifyUser("Test SMS notification")

        val expectedOutput = "Sending SMS: Test SMS notification\n"
        val actualOutput = outputStreamCaptor.toString().replace("\r\n", "\n")
        assertEquals(expectedOutput, actualOutput)
    }
}
