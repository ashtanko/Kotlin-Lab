package dev.shtanko.concurrency.collections

import java.util.concurrent.CopyOnWriteArrayList

fun interface EventListener {
    fun onEvent(event: String)
}

class EventDispatcher {
    private val listeners = CopyOnWriteArrayList<EventListener>()

    fun addListener(listener: EventListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: EventListener) {
        listeners.remove(listener)
    }

    fun dispatchEvent(event: String) {
        // Safe iteration even if listeners are added/removed during dispatch
        for (listener in listeners) {
            listener.onEvent(event)
        }
    }
}

fun main() {
    val dispatcher = EventDispatcher()

    val listenerA = EventListener { println("Listener A received: $it") }
    val listenerB = EventListener { println("Listener B received: $it") }

    dispatcher.addListener(listenerA)
    dispatcher.addListener(listenerB)

    dispatcher.dispatchEvent("Event #1")

    dispatcher.removeListener(listenerA)

    dispatcher.dispatchEvent("Event #2")
}
