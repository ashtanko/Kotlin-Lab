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

package dev.shtanko.patterns.structural.decorator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

internal class ClubbedTrollTest {

    @Test
    internal fun `clubbed troll test`() {
        val simpleTroll = spy(SimpleTroll())

        val clubbed = ClubbedTroll(simpleTroll)
        assertEquals(20, clubbed.getAttackPower())

        verify(simpleTroll, times(1)).getAttackPower()

        // Check if the clubbed troll actions are delegated to the decorated troll
        clubbed.attack()
        verify(simpleTroll, times(1)).attack()

        clubbed.fleeBattle()
        verify(simpleTroll, times(1)).fleeBattle()
        verifyNoMoreInteractions(simpleTroll)
    }
}
