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

import org.slf4j.LoggerFactory

internal class BinaryTree {
    var root: TreeNode? = null

    fun printLevelOrder() {
        val h = height(root)
        for (i in 1 until h) {
            printGivenLevel(root, i)
        }
    }

    private fun printGivenLevel(root: TreeNode?, level: Int) {
        if (root == null) {
            return
        }
        if (level == 1) {
            LOGGER.info("${root.value}")
        } else if (level > 1) {
            printGivenLevel(root.left, level - 1)
            printGivenLevel(root.right, level - 1)
        }
    }

    private fun height(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else {
            val lHeight = height(root.left)
            val rHeight = height(root.right)

            if (lHeight > rHeight) {
                lHeight + 1
            } else {
                rHeight + 1
            }
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BinaryTree::class.java)
    }
}
