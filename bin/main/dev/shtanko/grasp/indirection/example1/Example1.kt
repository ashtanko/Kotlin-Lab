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

class User(val name: String)

class Booking(val user: User, val date: String)

open class EmailService {
    open fun sendEmail(to: String, subject: String, content: String) {
        println("Sending email to: $to, Subject: $subject, Content: $content")
    }
}

class NotificationManager(private val emailService: EmailService) {
    fun notifyUser(user: User, message: String) {
        val userEmail = "${user.name}@example.com"
        emailService.sendEmail(userEmail, "Booking Notification", message)
    }
}

fun main() {
    val user = User("Alice")
    val booking = Booking(user, "2023-08-10")

    val emailService = EmailService()
    val notificationManager = NotificationManager(emailService)

    val notificationMessage = "Your booking on ${booking.date} is confirmed."
    notificationManager.notifyUser(user, notificationMessage)
}
