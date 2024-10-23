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

package dev.shtanko.algorithms.leetcode

import kotlin.math.ceil
import kotlin.math.floor

/**
 * Represents a node in a binary tree.
 *
 * @property value The value stored in the node.
 * @property left The left child of the node.
 * @property right The right child of the node.
 */
data class TreeNode(var value: Int) : Comparable<TreeNode> {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun compareTo(other: TreeNode): Int {
        return value.compareTo(other.value)
    }
}

/**
 * Creates a deep copy of the current binary tree.
 *
 * @return The cloned binary tree.
 */
fun TreeNode?.clone(): TreeNode? {
    if (this == null) return null
    val node = TreeNode(value)
    node.left = left.clone()
    node.right = right.clone()
    return node
}

/**
 * Creates a deep copy of the current binary tree with each node's value increased by a specified offset.
 *
 * @param offset The value to add to each node's value.
 * @return The cloned binary tree with adjusted values.
 */
fun TreeNode?.clone(offset: Int): TreeNode? {
    if (this == null) {
        return null
    }
    val clonedNode = TreeNode(this.value + offset)
    clonedNode.left = this.left.clone(offset)
    clonedNode.right = this.right.clone(offset)
    return clonedNode
}

/**
 * Extension function to generate a pretty-printed representation of the binary tree.
 *
 * @return A string containing the pretty-printed binary tree.
 */
fun TreeNode.prettyPrint(): String {
    val treeLines: MutableList<List<String?>> = ArrayList()
    var currentLevel: MutableList<TreeNode?> = ArrayList()
    var nextLevel: MutableList<TreeNode?> = ArrayList()
    val output = StringBuffer()

    currentLevel.add(this)
    var nodeCount = 1
    var widestValue = 0
    while (nodeCount != 0) {
        val line: MutableList<String?> = ArrayList()
        nodeCount = 0
        for (node in currentLevel) {
            if (node == null) {
                line.add(null)
                nextLevel.add(null)
                nextLevel.add(null)
            } else {
                val nodeValue = "${node.value}"
                line.add(nodeValue)
                if (nodeValue.length > widestValue) widestValue = nodeValue.length
                nextLevel.add(node.left)
                nextLevel.add(node.right)
                if (node.left != null) nodeCount++
                if (node.right != null) nodeCount++
            }
        }
        if (widestValue % 2 == 1) widestValue++
        treeLines.add(line)
        val temp: MutableList<TreeNode?> = currentLevel
        currentLevel = nextLevel
        nextLevel = temp
        nextLevel.clear()
    }
    var charactersPerPiece = treeLines[treeLines.size - 1].size * (widestValue + 4)
    for (i in treeLines.indices) {
        val line = treeLines[i]
        val halfPieceWidth = floor((charactersPerPiece / 2f).toDouble()).toInt() - 1
        if (i > 0) {
            for (j in line.indices) {
                // split node
                var cornerChar = ' '
                if (j % 2 == 1) {
                    if (line[j - 1] != null) {
                        cornerChar = if (line[j] != null) '┴' else '┘'
                    } else {
                        if (line[j] != null) cornerChar = '└'
                    }
                }
                output.append(cornerChar)

                // lines and spaces
                if (line[j] == null) {
                    for (k in 0 until charactersPerPiece - 1) {
                        output.append(" ")
                    }
                } else {
                    for (k in 0 until halfPieceWidth) {
                        output.append(if (j % 2 == 0) " " else "─")
                    }
                    output.append(if (j % 2 == 0) "┌" else "┐")
                    for (k in 0 until halfPieceWidth) {
                        output.append(if (j % 2 == 0) "─" else " ")
                    }
                }
            }
            output.append("\n")
        }

        // print line of numbers
        for (j in line.indices) {
            var nodeValue = line[j]
            if (nodeValue == null) nodeValue = ""
            val gap1 = ceil((charactersPerPiece / 2f - nodeValue.length / 2f).toDouble()).toInt()
            val gap2 = floor((charactersPerPiece / 2f - nodeValue.length / 2f).toDouble()).toInt()

            // a number
            for (k in 0 until gap1) {
                output.append(" ")
            }
            output.append(nodeValue)
            for (k in 0 until gap2) {
                output.append(" ")
            }
        }
        output.append("\n")
        charactersPerPiece /= 2
    }
    return output.toString()
}

/**
 * Extension function to calculate the height of the binary tree.
 *
 * @return The height of the binary tree. Returns -1 if the tree is empty.
 */
fun TreeNode?.height(): Int {
    return if (this == null) -1 else 1 + this.left.height()
}
