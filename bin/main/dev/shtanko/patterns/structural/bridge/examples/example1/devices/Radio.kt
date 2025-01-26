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

class Radio : Device {
    override var isEnabled = false
        private set

    override var channel = 1
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

    override fun enable() {
        isEnabled = true
    }

    override fun disable() {
        isEnabled = false
    }

    override fun printStatus() {
        println("------------------------------------")
        println("| I'm radio.")
        println("| I'm " + if (isEnabled) "enabled" else "disabled")
        println("| Current volume is $volume%")
        println("| Current channel is $channel")
        println("------------------------------------\n")
    }
}
