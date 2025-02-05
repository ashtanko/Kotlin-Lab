/*
 * Copyright 2025 Oleksii Shtanko
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

@file:Suppress("MagicNumber")

package dev.shtanko.concurrency

import java.util.LinkedList
import java.util.Queue
import java.util.concurrent.Exchanger

object ExchangerSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val exchanger: Exchanger<Message> = Exchanger()
        val t1 = ExchangerJob(exchanger)
        val t2 = ExchangerJob(exchanger)
        t1.start()
        t2.start()
    }
}

private data class Message(
    val header: String,
    val body: String,
)

private class ExchangerJob(
    val exchanger: Exchanger<Message> = Exchanger(),
) : Thread() {
    private val queue: Queue<Message> = LinkedList()

    override fun run() {
        repeat(100) {
            val header = "$name # $it encode skdhfgjhsdgfjsdhf"
            val body = "ksjdhfksdhfksdjfh"
            queue.offer(Message(header, body))
            val m1 = exchanger.exchange(queue.poll())
            println("$name exchange message: ${m1.header}")

            val m2 = exchanger.exchange(queue.poll())
            println("$name exchange message: ${m2.header}")
        }
    }
}
