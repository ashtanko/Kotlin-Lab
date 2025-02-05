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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DisjointIntervalsTest {

    @Test
    fun `summary ranges test`() {
        val disjointIntervals = DisjointIntervals()
        disjointIntervals.addNum(1)
        assertThat(disjointIntervals.getIntervals()).isEqualTo(arrayOf(intArrayOf(1, 1)))
        disjointIntervals.addNum(3)
        assertThat(disjointIntervals.getIntervals()).isEqualTo(arrayOf(intArrayOf(1, 1), intArrayOf(3, 3)))
        disjointIntervals.addNum(7)
        assertThat(disjointIntervals.getIntervals()).isEqualTo(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(3, 3),
                intArrayOf(7, 7),
            ),
        )
        disjointIntervals.addNum(2)
        assertThat(disjointIntervals.getIntervals()).isEqualTo(arrayOf(intArrayOf(1, 3), intArrayOf(7, 7)))
        disjointIntervals.addNum(6)
        assertThat(disjointIntervals.getIntervals()).isEqualTo(arrayOf(intArrayOf(1, 3), intArrayOf(6, 7)))
    }
}
