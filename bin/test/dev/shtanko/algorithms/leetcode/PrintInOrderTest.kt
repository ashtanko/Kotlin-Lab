/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import org.junit.jupiter.api.Test

class PrintInOrderTest {

    private val printInOrder = PrintInOrder()

    @Test
    @Throws(InterruptedException::class)
    fun `test print in order with concurrency test`() {
        val service = Executors.newFixedThreadPool(3)
        val latch = CountDownLatch(3)

        service.execute {
            printInOrder.first {
                print("first")
            }
            latch.countDown()
        }

        service.execute {
            printInOrder.second {
                print("second")
            }
            latch.countDown()
        }

        service.execute {
            printInOrder.third {
                print("third")
            }
            latch.countDown()
        }

        latch.await()
    }

    @Test
    @Throws(InterruptedException::class)
    fun `test print in order with concurrency 2 test`() {
        val service = Executors.newFixedThreadPool(3)
        val latch = CountDownLatch(3)

        service.execute {
            printInOrder.first {
                print("first")
            }
            latch.countDown()
        }

        service.execute {
            printInOrder.third {
                print("third")
            }
            latch.countDown()
        }

        service.execute {
            printInOrder.second {
                print("second")
            }
            latch.countDown()
        }
        latch.await()
    }
}
