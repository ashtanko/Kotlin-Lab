/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.util.LinkedList
import java.util.Queue

fun interface EvenOddTreeStrategy {
    operator fun invoke(tree: TreeNode?): Boolean
}

class EvenOddTreeBSF : EvenOddTreeStrategy {
    override operator fun invoke(tree: TreeNode?): Boolean {
        var root: TreeNode? = tree ?: return true
        val q: Queue<TreeNode> = LinkedList()
        q.add(root)
        var even = true
        while (q.isNotEmpty()) {
            var size: Int = q.size
            var prevVal = if (even) Int.MIN_VALUE else Int.MAX_VALUE // init preVal based on level even or odd
            while (size-- > 0) { // level by level
                root = q.poll()
                if (even && (root.value % 2 == 0 || root.value <= prevVal)) return false // invalid case on even level
                if (!even && (root.value % 2 == 1 || root.value >= prevVal)) return false // invalid case on odd level
                prevVal = root.value // update the prev value
                if (root.left != null) q.add(root.left) // add left child if exist
                if (root.right != null) q.add(root.right) // add right child if exist
            }
            even = !even // alter the levels
        }

        return true
    }
}
