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

package dev.shtanko.patterns.structural.bridge.examples.example1

import dev.shtanko.patterns.structural.bridge.examples.example1.devices.Radio
import dev.shtanko.patterns.structural.bridge.examples.example1.devices.Tv
import dev.shtanko.patterns.structural.bridge.examples.example1.remotes.AdvancedRemote
import dev.shtanko.patterns.structural.bridge.examples.example1.remotes.BasicRemote
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BridgePatternTest {
    @Test
    fun `basic tv remote test`() {
        val tv = Tv()
        val basicTvRemote = BasicRemote(tv)

        basicTvRemote.power()
        basicTvRemote.volumeUp()
        basicTvRemote.volumeUp()

        assertEquals(true, tv.isEnabled)
        assertEquals(50, tv.volume)
    }

    @Test
    fun `advanced radio remote test`() {
        val radio = Radio()
        val advancedRadioRemote = AdvancedRemote(radio)

        advancedRadioRemote.power()
        advancedRadioRemote.volumeDown()
        advancedRadioRemote.mute()

        assertEquals(true, radio.isEnabled)
        assertEquals(0, radio.volume)
    }
}
