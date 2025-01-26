/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.flyweight.example1

import dev.shtanko.patterns.structural.flyweight.example1.trees.TreeType
import java.awt.Color
import java.awt.Graphics
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TreeTypeTest {

    @Test
    fun `draw paints tree correctly`() {
        val graphics = Mockito.mock(Graphics::class.java)
        val treeType = TreeType("Oak", Color.GREEN, "Oak data")

        treeType.draw(graphics, 5, 10)

        Mockito.verify(graphics).color = Color.BLACK
        Mockito.verify(graphics).fillRect(4, 10, 3, 5)
        Mockito.verify(graphics).color = Color.GREEN
        Mockito.verify(graphics).fillOval(0, 0, 10, 10)
    }
}
