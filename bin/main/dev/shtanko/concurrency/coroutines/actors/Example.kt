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

package dev.shtanko.concurrency.coroutines.actors

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private sealed interface CounterMessage
private object IncrementCounter : CounterMessage // one-way message to increment counter
private object DecrementCounter : CounterMessage // one-way message to increment counter
private class GetCounter(val response: CompletableDeferred<Int>) : CounterMessage // a request with reply

private fun CoroutineScope.customCounterStateMachine(channel: ReceiveChannel<CounterMessage>) = launch {
    var counter = 0 // actor state
    channel.consumeEach { message ->
        ensureActive()
        when (message) {
            is IncrementCounter -> counter++
            is GetCounter -> message.response.complete(counter)
            is DecrementCounter -> counter--
        }
    }
}

private fun main(): Unit = runBlocking {
    val channel = Channel<CounterMessage>()
    // val counterStateMachine = customCounterStateMachine(channel) // create the counterStateMachine actor
    withContext(Dispatchers.Default) {
        massiveRun {
            channel.send(IncrementCounter)
        }
    }
    // send a message to get the counter value from the counterStateMachine actor
    val response = CompletableDeferred<Int>()
    channel.send(GetCounter(response))
    val count = response.await()
    println("Counter = $count")
    channel.close() // shutdown the counterStateMachine actor
}

private const val COROUTINES = 100 // number of coroutines to launch
private const val EXECUTION_TIMES = 1000 // times an action is repeated by each coroutine

// helper function to simulate a massive concurrent input of messages
private suspend fun massiveRun(action: suspend () -> Unit) {
    val n = COROUTINES
    val k = EXECUTION_TIMES
    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}
