/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.patterns.behavioral.observer.example2.publisher

import dev.shtanko.patterns.behavioral.observer.example2.listeners.EventListener
import java.io.File

class EventManager(vararg operations: String) {
    var listeners: MutableMap<String, MutableList<EventListener>> = HashMap()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList()
        }
    }

    fun subscribe(eventType: String, listener: EventListener) {
        val users: MutableList<EventListener> = listeners[eventType] ?: mutableListOf()
        users.add(listener)
    }

    fun unsubscribe(eventType: String, listener: EventListener) {
        val users: MutableList<EventListener> = listeners[eventType] ?: mutableListOf()
        users.remove(listener)
    }

    fun notify(eventType: String, file: File) {
        val users: List<EventListener> = listeners[eventType] ?: emptyList()
        for (listener in users) {
            listener.update(eventType, file)
        }
    }
}
