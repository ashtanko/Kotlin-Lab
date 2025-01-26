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

import dev.shtanko.patterns.structural.flyweight.example1.trees.TreeFactory
import java.awt.Color
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TreeFactoryTest {

    @Test
    fun `getTreeType returns existing tree type if present`() {
        val treeType = TreeFactory.getTreeType("Oak", Color.GREEN, "Oak data")
        val sameTreeType = TreeFactory.getTreeType("Oak", Color.GREEN, "Oak data")

        assertEquals(treeType, sameTreeType)
    }

    @Test
    fun `getTreeType creates new tree type if not present`() {
        val treeType = TreeFactory.getTreeType("Oak", Color.GREEN, "Oak data")
        val differentTreeType = TreeFactory.getTreeType("Pine", Color.GREEN, "Pine data")

        assertEquals(false, treeType == differentTreeType)
    }
}
