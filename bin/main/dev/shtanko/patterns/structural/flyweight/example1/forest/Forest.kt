/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.flyweight.example1.forest

import dev.shtanko.patterns.structural.flyweight.example1.trees.Tree
import dev.shtanko.patterns.structural.flyweight.example1.trees.TreeFactory.getTreeType
import dev.shtanko.patterns.structural.flyweight.example1.trees.TreeType
import java.awt.Color
import java.awt.Graphics
import javax.swing.JFrame

class Forest : JFrame() {
    val trees: MutableList<Tree> = ArrayList()
    val treeList: List<Tree> = trees

    fun plantTree(x: Int, y: Int, name: String, color: Color, otherTreeData: String) {
        val type: TreeType = getTreeType(name, color, otherTreeData)
        val tree = Tree(x, y, type)
        trees.add(tree)
    }

    override fun paint(graphics: Graphics) {
        for (tree in trees) {
            tree.draw(graphics)
        }
    }
}
