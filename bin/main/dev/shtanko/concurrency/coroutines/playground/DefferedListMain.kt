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

package dev.shtanko.concurrency.coroutines.playground

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private const val DELAY_MILLIS = 1000L

object DefferedListMain {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val deferreds: List<Deferred<Int>> = (1..3).map {
            async {
                delay(DELAY_MILLIS * it)
                println("Loading $it")
                it
            }
        }
        val sum = deferreds.awaitAll().sum()
        println("$sum")
    }
}
