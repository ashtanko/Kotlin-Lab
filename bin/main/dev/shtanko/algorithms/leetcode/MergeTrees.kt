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

/**
 * Merge Two Binary Trees
 */
fun Pair<TreeNode?, TreeNode?>.mergeTrees(): TreeNode? {
    // If both nodes are null, return null
    if (first == null && second == null) {
        return null
    }

    // If one of the nodes is null, return the non-null node
    if (second == null) {
        return first
    }

    if (first == null) {
        return second
    }

    // If both nodes have values, merge them
    val mergedValue = first!!.value + second!!.value
    val mergedNode = TreeNode(mergedValue)

    // Merge left subtrees
    mergedNode.left = (first!!.left to second!!.left).mergeTrees()

    // Merge right subtrees
    mergedNode.right = (first!!.right to second!!.right).mergeTrees()

    return mergedNode
}
