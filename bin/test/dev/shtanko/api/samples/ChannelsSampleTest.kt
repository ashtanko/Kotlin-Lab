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

package dev.shtanko.api.samples

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChannelsSampleTest {
    @Test
    fun testChannelCommunication(): Unit = runTest {
        val channel = Channel<String>()
        launch {
            channel.send("A1")
            delay(100) // Adding delay to make sure A1 is sent first
            channel.send("A2")
            println("A done")
        }
        launch {
            delay(50) // Adding delay to make sure A1 is sent before B1
            channel.send("B1")
            println("B done")
        }
        launch {
            val receivedMessages = mutableListOf<String>()
            repeat(3) {
                val x = channel.receive()
                receivedMessages.add(x)
            }
            assertEquals(listOf("A1", "B1", "A2"), receivedMessages)
        }
    }
}
