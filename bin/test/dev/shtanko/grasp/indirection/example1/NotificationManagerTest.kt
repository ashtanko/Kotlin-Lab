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

package dev.shtanko.grasp.indirection.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class NotificationManagerTest {
    private var emailSent = false

    private val mockEmailService = object : EmailService() {
        override fun sendEmail(to: String, subject: String, content: String) {
            emailSent = true
            println("Mocked: Sending email to: $to, Subject: $subject, Content: $content")
        }
    }

    @Test
    fun testNotifyUser() {
        val user = User("Alice")
        val notificationMessage = "Your booking is confirmed."

        val notificationManager = NotificationManager(mockEmailService)
        notificationManager.notifyUser(user, notificationMessage)

        assertEquals(true, emailSent)
    }
}
