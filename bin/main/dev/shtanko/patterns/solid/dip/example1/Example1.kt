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

import java.io.ByteArrayOutputStream

fun interface MessageSender {
    fun sendMessage(message: String)
}

class EmailSender : MessageSender {
    private val outputStream = ByteArrayOutputStream()

    override fun sendMessage(message: String) {
        outputStream.write("Email: $message\n".toByteArray())
    }

    fun getOutput(): String {
        return outputStream.toString()
    }
}

class SmsSender : MessageSender {
    private val outputStream = ByteArrayOutputStream()

    override fun sendMessage(message: String) {
        outputStream.write("SMS: $message\n".toByteArray())
    }

    fun getOutput(): String {
        return outputStream.toString()
    }
}

class NotificationService(private val sender: MessageSender) {
    fun sendNotification(message: String) {
        sender.sendMessage(message)
    }
}
