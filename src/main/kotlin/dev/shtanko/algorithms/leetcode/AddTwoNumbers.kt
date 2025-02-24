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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.annotations.Math
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2. Add Two Numbers
 * @see <a href="https://leetcode.com/problems/add-two-numbers">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/add-two-numbers")
fun interface AddTwoNumbers {
    operator fun invoke(list1: ListNode?, list2: ListNode?): ListNode?
}

@Math
class AddTwoNumbersMath : AddTwoNumbers {
    override fun invoke(list1: ListNode?, list2: ListNode?): ListNode? {
        val dummyNode = ListNode(0) // creating a dummy list
        var currentNode: ListNode? = dummyNode // initializing a pointer
        var carryOver = 0 // initializing our carry with 0 initially
        // while loop will run until list1 OR list2 reaches null OR if they both reach null.
        // But if our carry has some value in it, we will add that as well into our list
        var node1 = list1
        var node2 = list2
        while (node1 != null || node2 != null || carryOver == 1) {
            var sum = 0 // initializing our sum
            if (node1 != null) { // adding list1 to our sum & moving list1
                sum += node1.value
                node1 = node1.next
            }
            if (node2 != null) { // adding list2 to our sum & moving list2
                sum += node2.value
                node2 = node2.next
            }
            sum += carryOver // if we have carry, then add it into our sum
            carryOver = sum / DECIMAL // if we get carry, then divide it by 10 to get the carry
            val newNode = ListNode(sum % DECIMAL) // the value we'll get by modulating it,
            // will become a new node, so add it to our list
            currentNode?.next = newNode // currentNode will point to that new node if we get
            currentNode = currentNode?.next // update the current node every time
        }
        // return dummyNode.next because we don't want the value we have considered in it initially
        return dummyNode.next
    }
}
