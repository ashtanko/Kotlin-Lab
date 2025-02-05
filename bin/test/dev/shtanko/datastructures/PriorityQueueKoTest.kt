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

package dev.shtanko.datastructures

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PriorityQueueKoTest : StringSpec(
    {

        "Adding elements to the priority queue" {
            val queue = PriorityQueue<Int>(10)
            queue.add(5)
            queue.add(2)
            queue.add(8)
            queue.add(3)
            queue.add(1)

            queue.size shouldBe 5
        }

        "Peek should return the highest priority element without removal" {
            val queue = PriorityQueue<Int>(10)
            queue.add(5)
            queue.add(2)
            queue.add(8)
            queue.add(3)
            queue.add(1)

            queue.peek() shouldBe 1
            queue.size shouldBe 5
        }

        "Poll should return and remove the highest priority element" {
            val queue = PriorityQueue<Int>(10)
            queue.add(5)
            queue.add(2)
            queue.add(8)
            queue.add(3)
            queue.add(1)

            queue.poll() shouldBe 1
            queue.size shouldBe 4

            queue.poll() shouldBe 2
            queue.size shouldBe 3
        }

        "Contains should check if an element exists in the queue" {
            val queue = PriorityQueue<Int>(10)
            queue.add(5)
            queue.add(2)
            queue.add(8)
            queue.add(3)
            queue.add(1)

            queue.contains(5) shouldBe true
            queue.contains(9) shouldBe false
        }

        "ContainsAll should check if all elements exist in the queue" {
            val queue = PriorityQueue<Int>(10)
            queue.add(5)
            queue.add(2)
            queue.add(8)
            queue.add(3)
            queue.add(1)

            queue.containsAll(listOf(5, 3, 1)) shouldBe true
            queue.containsAll(listOf(2, 7)) shouldBe false
        }

        "isEmpty should correctly report if the queue is empty" {
            val emptyQueue = PriorityQueue<Int>(10)
            val nonEmptyQueue = PriorityQueue<Int>(10)
            nonEmptyQueue.add(5)

            emptyQueue.isEmpty() shouldBe true
            nonEmptyQueue.isEmpty() shouldBe false
        }
    },
)
