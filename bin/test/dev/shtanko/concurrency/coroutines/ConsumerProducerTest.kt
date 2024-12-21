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

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ConsumerProducerTest {
    @Test
    fun testConsumerProducer() = runTest {
        val channel = Channel<Int>(Channel.UNLIMITED)
        val done = CompletableDeferred<Boolean>()

        val producer = Producer(channel, done)
        val consumer = Consumer(channel)

        val produceJob = launch {
            producer.produce(5)
        }

        val consumeJob = launch {
            consumer.consume()
        }

        produceJob.join()
        channel.close() // Close the channel to finish the consumer

        consumeJob.join()

        assertTrue(done.await()) // Ensure the producer signals completion
    }
}
