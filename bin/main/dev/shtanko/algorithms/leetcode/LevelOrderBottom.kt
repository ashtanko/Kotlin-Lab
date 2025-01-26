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

import java.util.LinkedList

fun TreeNode?.levelOrderBottom(): List<List<Int>> {
    val wrapList: MutableList<MutableList<Int>> = LinkedList()
    levelMaker(wrapList, this, 0)
    return wrapList
}

private fun levelMaker(list: MutableList<MutableList<Int>>, root: TreeNode?, level: Int) {
    if (root == null) return
    if (level >= list.size) {
        list.add(0, LinkedList())
    }
    levelMaker(list, root.left, level + 1)
    levelMaker(list, root.right, level + 1)
    list[list.size - level - 1].add(root.value)
}
