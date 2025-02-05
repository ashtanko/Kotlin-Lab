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

package dev.shtanko.grasp.hc.example1

class Message(val sender: String, val receiver: String, val content: String)

class MessageStorage {
    private val messages = mutableListOf<Message>()

    fun storeMessage(message: Message) {
        messages.add(message)
    }

    fun retrieveMessages(): List<Message> {
        return messages.toList()
    }
}

class MessageStatistics(private val messageStorage: MessageStorage) {
    fun getMessageCount(): Int {
        val messages = messageStorage.retrieveMessages()
        return messages.size
    }

    fun getAverageMessageLength(): Double {
        val messages = messageStorage.retrieveMessages()
        if (messages.isEmpty()) return 0.0

        val totalLength = messages.sumOf { it.content.length }
        return totalLength.toDouble() / messages.size
    }
}

fun main() {
    val messageStorage = MessageStorage()
    val messageStatistics = MessageStatistics(messageStorage)

    val message1 = Message("Alice", "Bob", "Hello!")
    val message2 = Message("Bob", "Alice", "Hi there!")

    messageStorage.storeMessage(message1)
    messageStorage.storeMessage(message2)

    val messageCount = messageStatistics.getMessageCount()
    val averageLength = messageStatistics.getAverageMessageLength()

    println("Message Count: $messageCount")
    println("Average Message Length: $averageLength")
}
