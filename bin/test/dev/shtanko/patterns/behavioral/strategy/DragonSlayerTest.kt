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

package dev.shtanko.patterns.behavioral.strategy

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class DragonSlayerTest {

    @Test
    fun `go to battle test`() {
        val strategy = mock(DragonSlayingStrategy::class.java)
        val dragonSlayer = DragonSlayer(strategy)

        dragonSlayer.goToBattle()

        verify(strategy).execute()
        verifyNoMoreInteractions(strategy)
    }

    @Test
    fun `change strategy test`() {
        val initialStrategy = mock(DragonSlayingStrategy::class.java)
        val dragonSlayer = DragonSlayer(initialStrategy)

        dragonSlayer.goToBattle()
        verify(initialStrategy).execute()

        val newStrategy = mock(DragonSlayingStrategy::class.java)
        dragonSlayer.changeStrategy(newStrategy)

        dragonSlayer.goToBattle()
        verify(newStrategy).execute()
        verifyNoMoreInteractions(initialStrategy, newStrategy)
    }
}
