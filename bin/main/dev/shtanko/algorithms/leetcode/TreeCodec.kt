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

package dev.shtanko.algorithms.leetcode

import java.util.Deque
import java.util.LinkedList

object TreeCodec {
    private const val splitter = ","
    private const val NN = "X"

    // Encodes a tree to a single string.
    fun serialize(root: TreeNode?): String {
        val sb = StringBuilder()
        buildString(root, sb)
        return sb.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val nodes: Deque<String> = LinkedList()
        val s = data.split(splitter.toRegex()).dropLastWhile { it.isEmpty() }.toList()
        nodes.addAll(s)
        return buildTree(nodes)
    }

    private fun buildString(node: TreeNode?, sb: StringBuilder) {
        if (node == null) {
            sb.append(NN).append(splitter)
        } else {
            sb.append(node.value).append(splitter)
            buildString(node.left, sb)
            buildString(node.right, sb)
        }
    }

    private fun buildTree(nodes: Deque<String>): TreeNode? {
        val value = nodes.remove()
        return if (value == NN) {
            null
        } else {
            val node = TreeNode(Integer.valueOf(value))
            node.left = buildTree(nodes)
            node.right = buildTree(nodes)
            node
        }
    }
}
