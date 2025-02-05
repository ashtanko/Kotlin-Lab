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

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class QueueKoTest : DescribeSpec(
    {

        describe("A Queue") {

            it("should be empty when created") {
                val queue = Queue<Int>()
                queue.isEmpty() shouldBe true
            }

            it("should have size 1 after adding an element") {
                val queue = Queue<Int>()
                queue.add(42)
                queue.size shouldBe 1
            }

            it("should contain the added element") {
                val queue = Queue<Int>()
                queue.add(42)
                queue.contains(42) shouldBe true
            }

            it("should not contain removed element") {
                val queue = Queue<Int>()
                queue.add(42)
                queue.poll()
                queue.contains(42) shouldBe false
            }

            it("should have size 0 after removing element") {
                val queue = Queue<Int>()
                queue.add(42)
                queue.poll()
                queue.size shouldBe 0
            }

            it("should return elements in the correct order") {
                val queue = Queue<Int>()
                queue.add(1)
                queue.add(2)
                queue.add(3)
                val result = mutableListOf<Int>()
                while (!queue.isEmpty()) {
                    result.add(queue.poll())
                }
                result shouldBe listOf(1, 2, 3)
            }

            it("should throw NoSuchElementException when peeking an empty queue") {
                val queue = Queue<Int>()
                shouldThrow<NoSuchElementException> {
                    queue.peek()
                }
            }

            it("should throw NoSuchElementException when polling an empty queue") {
                val queue = Queue<Int>()
                shouldThrow<NoSuchElementException> {
                    queue.poll()
                }
            }
        }
    },
)
