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

package dev.shtanko.algorithms.tree.traversals

import dev.shtanko.algorithms.leetcode.TreeNode

/**
 * TreeTraversalStrategy is a functional interface that represents a tree traversal operation.
 * It defines a single method `invoke` that takes a root of a binary tree and returns a list of integers.
 * The list of integers represents the order of node visitation in the traversal.
 *
 * @param root The root node of the binary tree to be traversed.
 * @return A list of integers representing the order of node visitation in the traversal.
 */
fun interface TreeTraversalStrategy {
    operator fun invoke(root: TreeNode): List<Int>
}
