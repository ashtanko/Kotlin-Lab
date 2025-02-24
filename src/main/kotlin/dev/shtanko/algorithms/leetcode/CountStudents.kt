/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Easy
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

/**
 * 1700. Number of Students Unable to Eat Lunch
 * @see <a href="https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/">Source</a>
 */
@Easy("https://leetcode.com/problems/number-of-students-unable-to-eat-lunch")
fun interface CountStudents {
    operator fun invoke(students: IntArray, sandwiches: IntArray): Int
}

class CountStudentsStack : CountStudents {
    override fun invoke(students: IntArray, sandwiches: IntArray): Int {
        val len: Int = students.size // Sandwiches will be the same length
        val studentQueue: Queue<Int> = LinkedList()
        val sandwichStack: Stack<Int> = Stack()

        // Add students and sandwiches to the queue and stack
        for (i in 0 until len) {
            sandwichStack.push(sandwiches[len - i - 1])
            studentQueue.offer(students[i])
        }

        // Simulate the lunch process by serving sandwiches
        // or sending students to the back of the queue.
        var lastServed = 0
        while (studentQueue.isNotEmpty() && lastServed < studentQueue.size) {
            if (sandwichStack.peek() == studentQueue.peek()) {
                sandwichStack.pop() // Serve sandwich
                studentQueue.poll() // Student leaves queue
                lastServed = 0
            } else {
                // Student moves to back of queue
                studentQueue.offer(studentQueue.poll())
                lastServed++
            }
        }

        // Remaining students in queue are unversed students
        return studentQueue.size
    }
}

class CountStudentsCounting : CountStudents {
    override fun invoke(students: IntArray, sandwiches: IntArray): Int {
        var circleStudentCount = 0
        var squareStudentCount = 0

        // Count the number of students who want each type of sandwich
        for (student in students) {
            if (student == 0) {
                circleStudentCount++
            } else {
                squareStudentCount++
            }
        }

        // Serve sandwiches to students
        for (sandwich in sandwiches) {
            // No student wants the circle sandwich on top of the stack
            if (sandwich == 0 && circleStudentCount == 0) {
                return squareStudentCount
            }
            // No student wants the square sandwich on top of the stack
            if (sandwich == 1 && squareStudentCount == 0) {
                return circleStudentCount
            }
            // Decrement the count of the served sandwich type
            if (sandwich == 0) {
                circleStudentCount--
            } else {
                squareStudentCount--
            }
        }

        // Every student received a sandwich
        return 0
    }
}

class CountStudentsArray : CountStudents {
    override fun invoke(students: IntArray, sandwiches: IntArray): Int {
        val array = intArrayOf(0, 0)
        for (element in students) array[element] += 1
        var k = 0
        while (k < sandwiches.size) {
            if (array[sandwiches[k]] > 0) array[sandwiches[k]] -= 1 else break
            k += 1
        }
        return sandwiches.size - k
    }
}
