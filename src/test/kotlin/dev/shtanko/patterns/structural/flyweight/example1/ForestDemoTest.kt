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

import dev.shtanko.patterns.structural.flyweight.example1.forest.Forest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ForestDemoTest {
    @Test
    fun `createForest plants correct number of trees`() {
        val forest = ForestDemo.createForest()

        assertEquals(ForestDemo.TREES_TO_DRAW, forest.trees.size)
    }

    @Test
    fun `plantTrees adds two types of trees to forest`() {
        val forest = Forest()
        ForestDemo.plantTrees(forest)

        val treeTypeNames = forest.trees.map { it.type.name }.toSet()

        assertEquals(setOf("Summer Oak", "Autumn Oak"), treeTypeNames)
    }

    @Test
    fun `random generates number within range`() {
        val min = 0
        val max = 10
        val randomValue = ForestDemo.random(min, max)

        assert(randomValue in min..max)
    }
}
