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
 * Trim Binary Search Tree
 */
fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {
    if (root == null) return null
    if (root.value > high) return trimBST(root.left, low, high)
    if (root.value < low) return trimBST(root.right, low, high)

    root.left = trimBST(root.left, low, high)
    root.right = trimBST(root.right, low, high)
    return root
}
