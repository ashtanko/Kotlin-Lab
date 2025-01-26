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

fun interface MessageSender {
    fun sendMessage(message: String)
}

class EmailSender : MessageSender {
    override fun sendMessage(message: String) {
        println("Sending email: $message")
    }
}

class SMSMessageSender : MessageSender {
    override fun sendMessage(message: String) {
        println("Sending SMS: $message")
    }
}

class UserNotification(private val messageSender: MessageSender) {
    fun notifyUser(message: String) {
        messageSender.sendMessage(message)
    }
}

fun main() {
    val emailSender = EmailSender()
    val smsSender = SMSMessageSender()

    val emailNotification = UserNotification(emailSender)
    val smsNotification = UserNotification(smsSender)

    emailNotification.notifyUser("Your email message has been delivered.")
    smsNotification.notifyUser("Your SMS has been sent.")
}
