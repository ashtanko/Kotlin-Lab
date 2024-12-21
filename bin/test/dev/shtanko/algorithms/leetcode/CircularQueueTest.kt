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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CircularQueueTest {

    @Test
    fun `simple test`() {
        val circularQueue = CircularQueue(3)
        assertThat(circularQueue.enQueue(1)).isTrue
        assertThat(circularQueue.enQueue(2)).isTrue
        assertThat(circularQueue.enQueue(3)).isTrue
        assertThat(circularQueue.enQueue(4)).isFalse
        assertThat(circularQueue.rear()).isEqualTo(3)
        assertThat(circularQueue.isFull()).isTrue
        assertThat(circularQueue.deQueue()).isTrue
        assertThat(circularQueue.enQueue(4)).isTrue
        assertThat(circularQueue.rear()).isEqualTo(4)
    }
}
