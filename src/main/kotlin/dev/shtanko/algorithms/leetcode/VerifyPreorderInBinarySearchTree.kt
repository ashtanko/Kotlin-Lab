package dev.shtanko.algorithms.leetcode

import java.util.Stack

// Verify Preorder Sequence in Binary Search Tree
interface VerifyPreorderInBinarySearchTreeStrategy {
    fun perform(preorder: IntArray): Boolean
}

class VerifyPreorderInBinarySearchTreeBF : VerifyPreorderInBinarySearchTreeStrategy {
    override fun perform(preorder: IntArray): Boolean {
        var low = Int.MIN_VALUE
        var i = -1
        for (p in preorder) {
            if (p < low) return false
            while (i >= 0 && p > preorder[i]) low = preorder[i--]
            preorder[++i] = p
        }
        return true
    }
}

class VerifyPreorderInBinarySearchTreeStack : VerifyPreorderInBinarySearchTreeStrategy {
    override fun perform(preorder: IntArray): Boolean {
        var low = Int.MIN_VALUE
        val path: Stack<Int> = Stack()
        for (p in preorder) {
            if (p < low) return false
            while (!path.empty() && p > path.peek()) low = path.pop()
            path.push(p)
        }
        return true
    }
}

class VerifyPreorderInBinarySearchTreeRecursion : VerifyPreorderInBinarySearchTreeStrategy {

    private var i = 1

    override fun perform(preorder: IntArray): Boolean {
        return preorder.isEmpty() || check(preorder, Integer.MIN_VALUE, preorder.first()) && check(
            preorder,
            preorder.first(),
            Integer.MAX_VALUE
        )
    }

    private fun check(a: IntArray, left: Int, right: Int): Boolean {
        if (i == a.size || a[i] > right) return true
        val mid = a[i++]
        return mid > left && check(a, left, mid) && check(a, mid, right)
    }
}