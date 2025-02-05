/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.concurrency.jvm.deadlock

import kotlin.concurrent.thread

object DeadLockSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val res1 = "Resource1"
        val res2 = "Resource2"

        thread {
            synchronized(res1) {
                println("Lock res 1 in thread: ${Thread.currentThread().name}")

                synchronized(res2) {
                    println("Lock res 2 in thread: ${Thread.currentThread().name}")
                }
            }
        }

        thread {
            synchronized(res2) {
                println("Lock res 2 in thread: ${Thread.currentThread().name}")
                synchronized(res1) {
                    println("Lock res 2 in thread: ${Thread.currentThread().name}")
                }
            }
        }
    }
}
