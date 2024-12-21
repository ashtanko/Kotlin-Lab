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

import dev.shtanko.patterns.structural.flyweight.example1.trees.Tree
import dev.shtanko.patterns.structural.flyweight.example1.trees.TreeType
import java.awt.Graphics
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TreeTest {

    @Test
    fun `draw invokes draw method of TreeType`() {
        val graphics = Mockito.mock(Graphics::class.java)
        val treeType = Mockito.mock(TreeType::class.java)
        val tree = Tree(5, 10, treeType)

        tree.draw(graphics)

        Mockito.verify(treeType).draw(graphics, 5, 10)
    }
}
