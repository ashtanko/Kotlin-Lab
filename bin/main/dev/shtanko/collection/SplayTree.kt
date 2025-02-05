/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.collection

typealias Predicate<T> = (T) -> Boolean

open class SplayTreeNode<K, Node : SplayTreeNode<K, Node>>(val key: K) {
    var left: Node? = null
    var right: Node? = null
}

open class SplayTreeSetNode<K>(key: K) : SplayTreeNode<K, SplayTreeSetNode<K>>(key)

open class SplayTreeMapNode<K, V>(key: K, var value: V) : SplayTreeNode<K, SplayTreeMapNode<K, V>>(key) {
    fun replaceValue(newValue: V): SplayTreeMapNode<K, V> {
        val newNode = SplayTreeMapNode(key, newValue)
        newNode.left = left
        newNode.right = right
        return newNode
    }
}
