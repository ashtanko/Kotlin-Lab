/*
 * Copyright 2022 Oleksii Shtanko
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

class StockSpannerTest {

    @Test
    fun `stock spanner next test`() {
        val spanner = StockSpanner()
        assertThat(spanner.next(100)).isEqualTo(1)
        assertThat(spanner.next(80)).isEqualTo(1)
        assertThat(spanner.next(60)).isEqualTo(1)
        assertThat(spanner.next(70)).isEqualTo(2)
        assertThat(spanner.next(60)).isEqualTo(1)
        assertThat(spanner.next(75)).isEqualTo(4)
        assertThat(spanner.next(85)).isEqualTo(6)
    }
}
