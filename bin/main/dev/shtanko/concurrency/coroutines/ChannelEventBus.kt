/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Define event types
sealed class Event {
    data class Message(val content: String) : Event()
    data object Signal : Event()
}

// Singleton event bus
object EventBus {
    private val eventChannel = Channel<Event>()

    // Function to send events to the event bus
    suspend fun sendEvent(event: Event) {
        eventChannel.send(event)
    }

    // Function to subscribe to events of a specific type
    fun subscribeToMessages(scope: CoroutineScope, handler: suspend (Event.Message) -> Unit): Job {
        return scope.launch {
            for (event in eventChannel) {
                if (event is Event.Message) {
                    handler(event)
                }
            }
        }
    }

    // Function to subscribe to all events
    fun subscribeToAll(scope: CoroutineScope, handler: suspend (Event) -> Unit): Job {
        return scope.launch {
            for (event in eventChannel) {
                handler(event)
            }
        }
    }
}

// Example usage
@Suppress("MagicNumber")
fun main() = runBlocking<Unit> {
    // Subscribe to message events
    val messageSubscriber = EventBus.subscribeToMessages(this) { message ->
        println("Message received: ${message.content}")
    }

    // Subscribe to all events
    val allEventsSubscriber = EventBus.subscribeToAll(this) { event ->
        when (event) {
            is Event.Message -> println("All events - Message received: ${event.content}")
            is Event.Signal -> println("All events - Signal received")
        }
    }

    // Simulate sending events
    repeat(5) {
        delay(500)
        EventBus.sendEvent(Event.Message("Event $it"))
    }
    delay(1000)
    EventBus.sendEvent(Event.Signal)

    // Delay for a while to see the events being processed
    delay(3000)

    // Cancel subscribers to clean up
    messageSubscriber.cancel()
    allEventsSubscriber.cancel()
}
