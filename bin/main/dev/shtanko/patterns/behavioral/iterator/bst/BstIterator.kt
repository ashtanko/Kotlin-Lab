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

package dev.shtanko.patterns.behavioral.iterator.bst

import dev.shtanko.patterns.behavioral.iterator.CustomIterator
import java.util.ArrayDeque
import java.util.Deque

class BstIterator<T : Comparable<T>>(root: TreeNode<T>?) : CustomIterator<TreeNode<T>> {

    private val pathStack: Deque<TreeNode<T>> = ArrayDeque()

    init {
        pushPathToNextSmallest(root)
    }

    override fun hasNext(): Boolean {
        return pathStack.isNotEmpty()
    }

    @Throws(NoSuchElementException::class)
    override fun next(): TreeNode<T>? {
        if (pathStack.isEmpty()) {
            throw NoSuchElementException()
        }
        val next = pathStack.pop()
        pushPathToNextSmallest(next.right)
        return next
    }

    private fun pushPathToNextSmallest(node: TreeNode<T>?) {
        var treeNode = node
        while (treeNode != null) {
            pathStack.push(treeNode)
            treeNode = treeNode.left
        }
    }
}
