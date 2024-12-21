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

import java.util.Stack

class NAryNode(var value: Int) {
    var children: List<NAryNode?> = listOf()
}

fun interface NAryTreePreorderTraversalStrategy {
    fun preorder(root: NAryNode?): List<Int>
}

class NAryTreePreorderTraversalIterative : NAryTreePreorderTraversalStrategy {
    override fun preorder(root: NAryNode?): List<Int> {
        var r = root
        val list: MutableList<Int> = ArrayList()
        if (r == null) return list

        val stack: Stack<NAryNode> = Stack()
        stack.add(r)

        while (!stack.empty()) {
            r = stack.pop()
            list.add(r.value)
            for (i in r.children.size - 1 downTo 0) stack.add(r.children[i])
        }

        return list
    }
}

class NAryTreePreorderTraversalRecursive : NAryTreePreorderTraversalStrategy {

    var list: MutableList<Int> = ArrayList()

    override fun preorder(root: NAryNode?): List<Int> {
        if (root == null) return list
        list.add(root.value)
        for (node in root.children) preorder(node)
        return list
    }
}
