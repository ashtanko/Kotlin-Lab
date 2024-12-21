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

package dev.shtanko.patterns.structural.bridge.examples.example1.devices

class Tv : Device {
    private var on = false

    override val isEnabled: Boolean
        get() = on

    override fun enable() {
        on = true
    }

    override fun disable() {
        on = false
    }

    override var volume: Int = 30
        set(percent) {
            field = if (percent > 100) {
                100
            } else if (percent < 0) {
                0
            } else {
                percent
            }
        }

    override var channel: Int = 1

    override fun printStatus() {
        println("------------------------------------")
        println("| I'm TV set.")
        println("| I'm " + if (on) "enabled" else "disabled")
        println("| Current volume is $volume%")
        println("| Current channel is $channel")
        println("------------------------------------\n")
    }
}
