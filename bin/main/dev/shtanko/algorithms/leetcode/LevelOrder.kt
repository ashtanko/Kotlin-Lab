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
import java.util.Queue

/**
 * This function performs a level order traversal on a binary tree.
 * It takes a TreeNode as input and returns a list of lists of integers, where each list represents a level in the tree.
 * The function uses a queue to keep track of the nodes to visit next, and a list of lists to store the values of the
 * nodes at each level.
 * The function starts by adding the root node to the queue. Then, while the queue is not empty, it removes nodes from
 * the queue and adds their children to the queue.
 * For each node that it removes from the queue, the function adds its value to the current level's list.
 * When it has visited all the nodes at the current level, the function adds the current level's list to the list
 * of levels and starts a new level.
 * The function continues this process until it has visited all the nodes in the tree.
 *
 * @return A list of lists of integers, where each list represents a level in the tree.
 */
fun TreeNode?.levelOrder(): List<List<Int>> {
    val queue: Queue<TreeNode> = LinkedList()
    val wrapList: MutableList<MutableList<Int>> = ArrayList()
    if (this == null) return wrapList
    queue.offer(this)
    while (queue.isNotEmpty()) {
        val levelNum = queue.size
        val subList: MutableList<Int> = LinkedList()
        for (i in 0 until levelNum) {
            if (queue.peek().left != null) {
                queue.offer(queue.peek().left)
            }
            if (queue.peek().right != null) {
                queue.offer(queue.peek().right)
            }
            subList.add(queue.poll().value)
        }
        wrapList.add(subList)
    }
    return wrapList
}
