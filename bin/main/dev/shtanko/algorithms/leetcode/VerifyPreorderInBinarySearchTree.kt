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

// Verify Preorder Sequence in Binary Search Tree
fun interface VerifyPreorderInBinarySearchTreeStrategy {
    operator fun invoke(preorder: IntArray): Boolean
}

class VerifyPreorderInBinarySearchTreeBF : VerifyPreorderInBinarySearchTreeStrategy {
    override operator fun invoke(preorder: IntArray): Boolean {
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
    override operator fun invoke(preorder: IntArray): Boolean {
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

    override operator fun invoke(preorder: IntArray): Boolean {
        return preorder.isEmpty() || check(preorder, Integer.MIN_VALUE, preorder.first()) && check(
            preorder,
            preorder.first(),
            Integer.MAX_VALUE,
        )
    }

    private fun check(a: IntArray, left: Int, right: Int): Boolean {
        if (i == a.size || a[i] > right) return true
        val mid = a[i++]
        return mid > left && check(a, left, mid) && check(a, mid, right)
    }
}
