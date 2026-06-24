@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.yield

class BinaryTreeTask(
    private val nodeCount: Int = 50_000,
) : BaseTask<String>(
    name = "Binary Tree Operations",
    description = "Building and traversing $nodeCount nodes",
) {
    class TreeNode(val value: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    override suspend fun execute(): String {
        var root: TreeNode? = null
        val values = List(nodeCount) { Random.nextInt(1000000) }

        // Build tree
        for ((index, value) in values.withIndex()) {
            if (index % 500 == 0) {
                yield()
                updateProgress(index.toFloat() / (nodeCount * 2))
            }
            root = insert(root, value)
        }

        // Calculate tree properties
        val height = calculateHeight(root)
        val nodeSum = sumNodes(root)

        return "Height: $height, Sum: $nodeSum"
    }

    private fun insert(root: TreeNode?, value: Int): TreeNode {
        if (root == null) return TreeNode(value)

        if (value < root.value) {
            root.left = insert(root.left, value)
        } else {
            root.right = insert(root.right, value)
        }
        return root
    }

    private suspend fun calculateHeight(node: TreeNode?): Int {
        if (node == null) return 0
        return 1 + maxOf(calculateHeight(node.left), calculateHeight(node.right))
    }

    private suspend fun sumNodes(node: TreeNode?): Long {
        if (node == null) return 0
        return node.value + sumNodes(node.left) + sumNodes(node.right)
    }
}
