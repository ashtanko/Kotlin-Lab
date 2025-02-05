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

data class TreeNode<T : Comparable<T>>(var value: T, var left: TreeNode<T>? = null, var right: TreeNode<T>? = null) {

    fun insert(valToInsert: T) {
        val parent: TreeNode<T> = getParentNodeOfValueToBeInserted(valToInsert)
        parent.insertNewChild(valToInsert)
    }

    private fun getParentNodeOfValueToBeInserted(valToInsert: T): TreeNode<T> {
        var parent: TreeNode<T> = this
        var curr: TreeNode<T>? = this
        while (curr != null) {
            parent = curr
            curr = curr.traverseOneLevelDown(valToInsert)
        }
        return parent
    }

    private fun traverseOneLevelDown(value: T): TreeNode<T>? {
        return if (isGreaterThan(value)) {
            left
        } else {
            right
        }
    }

    private fun insertNewChild(valToInsert: T) {
        if (isLessThanOrEqualTo(valToInsert)) {
            this.right = TreeNode(valToInsert)
        } else {
            this.left = TreeNode(valToInsert)
        }
    }

    private fun isGreaterThan(value: T): Boolean {
        return this.value > value
    }

    private fun isLessThanOrEqualTo(value: T): Boolean {
        return this.value.compareTo(value) < 1
    }
}
