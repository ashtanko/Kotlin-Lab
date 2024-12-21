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

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class MessageStatisticsTest {
    @Test
    fun testGetMessageCount() {
        val messageStorage = MessageStorage()
        val messageStatistics = MessageStatistics(messageStorage)

        val message1 = Message("Alice", "Bob", "Hello!")
        val message2 = Message("Bob", "Alice", "Hi there!")

        messageStorage.storeMessage(message1)
        messageStorage.storeMessage(message2)

        val messageCount = messageStatistics.getMessageCount()
        assertEquals(2, messageCount)
    }

    @Test
    fun testGetAverageMessageLength() {
        val messageStorage = MessageStorage()
        val messageStatistics = MessageStatistics(messageStorage)

        val message1 = Message("Alice", "Bob", "Hello!")
        val message2 = Message("Bob", "Alice", "Hi there!")

        messageStorage.storeMessage(message1)
        messageStorage.storeMessage(message2)

        val averageLength = messageStatistics.getAverageMessageLength()
        assertEquals(7.5, averageLength, 0.01) // Allowing a small delta for floating-point precision
    }
}
