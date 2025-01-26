/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.templates

private class TrieNode {
    // you can store data at nodes if you wish
    var data = 0
    var children: MutableMap<Char, TrieNode> = HashMap()
}

private fun buildTrie(words: Array<String>): TrieNode {
    val root = TrieNode()
    for (word in words) {
        var curr: TrieNode? = root
        for (c in word.toCharArray()) {
            if (curr?.children?.containsKey(c) == false) {
                curr.children[c] = TrieNode()
            }
            curr = curr?.children?.get(c)
        }

        // at this point, you have a full word at curr
        // you can perform more logic here to give curr an attribute if you want
    }
    return root
}
