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

package dev.shtanko.patterns.structural.bridge.examples.example1.remotes

import dev.shtanko.patterns.structural.bridge.examples.example1.devices.Device

open class BasicRemote(private var device: Device) : Remote {

    override fun power() {
        println("Remote: power toggle")
        if (device.isEnabled) {
            device.disable()
        } else {
            device.enable()
        }
    }

    override fun volumeDown() {
        println("Remote: volume down")
        device.volume = device.volume - 10
    }

    override fun volumeUp() {
        println("Remote: volume up")
        device.volume = device.volume + 10
    }

    override fun channelDown() {
        println("Remote: channel down")
        device.channel = device.channel - 1
    }

    override fun channelUp() {
        println("Remote: channel up")
        device.channel = device.channel + 1
    }
}
