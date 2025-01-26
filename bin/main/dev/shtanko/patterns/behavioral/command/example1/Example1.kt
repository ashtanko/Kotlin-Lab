/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.patterns.behavioral.command.example1

// Receiver
class Light {
    var isOn: Boolean = false

    fun turnOn() {
        isOn = true
        println("Light is on")
    }

    fun turnOff() {
        isOn = false
        println("Light is off")
    }

    val isOff: Boolean
        get() = !isOn
}

// Command interface
fun interface Command {
    fun execute()
}

// Concrete command for turning on the light
class LightOnCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOn()
    }
}

// Concrete command for turning off the light
class LightOffCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOff()
    }
}

// Invoker
class RemoteControl {
    private val commands = mutableListOf<Command>()

    fun setCommand(command: Command) {
        commands.add(command)
    }

    fun pressButton() {
        commands.forEach { it.execute() }
        commands.clear()
    }
}
