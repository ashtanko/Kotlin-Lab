/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.concurrency.jvm.queue

import java.util.concurrent.ArrayBlockingQueue
import kotlin.concurrent.thread

object AlphabetSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val bq = ArrayBlockingQueue<Char>(2)

        val producer = thread(start = false) {
            for (ch in 'A' until 'Z') {
                bq.put(ch)
                println("$ch was produced")
            }
        }

        val consumer = thread(start = false) {
            var ch = '/'
            do {
                ch = bq.take()
                println("$ch was taken")
            } while (ch != 'Z')
        }

        producer.start()
        consumer.start()
    }
}
