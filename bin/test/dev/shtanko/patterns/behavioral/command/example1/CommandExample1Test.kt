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

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CommandExample1Test {
    @Test
    fun testLightOnCommand() {
        val light = Light()
        val lightOnCommand = LightOnCommand(light)

        lightOnCommand.execute()

        Assertions.assertTrue(light.isOn)
        Assertions.assertFalse(light.isOff)
    }

    @Test
    fun testLightOffCommand() {
        val light = Light()
        val lightOffCommand = LightOffCommand(light)

        lightOffCommand.execute()

        Assertions.assertTrue(light.isOff)
        Assertions.assertFalse(light.isOn)
    }

    @Test
    fun testRemoteControl() {
        val light = Light()
        val lightOnCommand = LightOnCommand(light)
        val lightOffCommand = LightOffCommand(light)

        val remoteControl = RemoteControl()

        remoteControl.setCommand(lightOnCommand)
        remoteControl.pressButton()

        Assertions.assertTrue(light.isOn)

        remoteControl.setCommand(lightOffCommand)
        remoteControl.pressButton()

        Assertions.assertTrue(light.isOff)
    }
}
