/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.api.samples

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object ChannelsSample {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        val channel = Channel<String>()
        launch {
            channel.send("A1")
            channel.send("A2")
            println("A done")
        }
        launch {
            channel.send("B1")
            println("B done")
        }
        launch {
            repeat(3) {
                val x = channel.receive()
                println(x)
            }
        }
    }
}
