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

package dev.shtanko.algorithms.interview

import java.util.Random

@Suppress("UnnecessaryParentheses")
class BinaryTreeNode(private val parent: BinaryTreeNode? = null) {
    val left: WeightedTree = WeightedTree()
    val right: WeightedTree = WeightedTree()

    fun getRandom(getBetween: (range: IntRange) -> Int = { range -> range.random() }): BinaryTreeNode {
        val n = right.weight + left.weight
        val local = left.weight + right.weight + 1
        return when (getBetween(0 until local)) {
            in (0 until left.weight) -> left.child?.getRandom(getBetween)
                ?: throw IllegalStateException("BinaryTreeNode is null")

            in (left.weight until n) -> right.child?.getRandom(getBetween)
                ?: throw IllegalStateException("BinaryTreeNode is null")

            else -> this
        }
    }

    fun addLeftChild(newLeftChild: BinaryTreeNode) {
        require(newLeftChild.parent == this)
        left.addChild(newLeftChild)
        updateParents()
    }

    fun addRightChild(newRightChild: BinaryTreeNode) {
        require(newRightChild.parent == this)
        right.addChild(newRightChild)
        updateParents()
    }

    private fun updateParents() {
        if (parent == null) {
            return
        }

        when {
            this == parent.right.child -> parent.right.weight++
            this == parent.left.child -> parent.left.weight++
        }

        return parent.updateParents()
    }
}

class WeightedTree {
    var weight: Int = 0

    var child: BinaryTreeNode? = null
        private set

    fun addChild(binaryTreeNode: BinaryTreeNode) {
        child = binaryTreeNode
        weight++
    }
}

fun IntRange.random() =
    Random().nextInt(endInclusive.plus(1).minus(start)) + start
