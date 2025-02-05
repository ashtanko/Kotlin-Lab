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

package dev.shtanko.patterns.behavioral.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CommandTest {

    companion object {
        private const val GOBLIN = "Goblin"
    }

    @Test
    internal fun `command test`() {
        val wizard = Wizard()
        val goblin = Goblin()

        wizard.castSpell(ShrinkSpell(), goblin)
        verifyGoblin(goblin, GOBLIN, Size.SMALL, Visibility.VISIBLE)

        wizard.castSpell(InvisibilitySpell(), goblin)
        verifyGoblin(goblin, GOBLIN, Size.SMALL, Visibility.INVISIBLE)

        wizard.undoLastSpell()
        verifyGoblin(goblin, GOBLIN, Size.SMALL, Visibility.VISIBLE)

        wizard.undoLastSpell()
        verifyGoblin(goblin, GOBLIN, Size.NORMAL, Visibility.VISIBLE)

        wizard.redoLastSpell()
        verifyGoblin(goblin, GOBLIN, Size.SMALL, Visibility.VISIBLE)

        wizard.redoLastSpell()
        verifyGoblin(goblin, GOBLIN, Size.SMALL, Visibility.INVISIBLE)
    }

    private fun verifyGoblin(goblin: Goblin, expectedName: String, expectedSize: Size, expectedVisibility: Visibility) {
        assertEquals(expectedName, goblin.toString(), "Goblin's name must be same as expectedName")
        assertEquals(expectedSize, goblin.size, "Goblin's size must be same as expectedSize")
        assertEquals(
            expectedVisibility,
            goblin.visibility,
            "Goblin's visibility must be same as expectedVisibility",
        )
    }
}
