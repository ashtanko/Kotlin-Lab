/*
 * Copyright 2024 Oleksii Shtanko
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

class RandomizedSetTest {

    @Test
    fun `randomized set test`() {
        val randomizedSet = RandomizedSet()
        assertThat(randomizedSet.insert(1)).isTrue()
        assertThat(randomizedSet.remove(2)).isFalse()
        assertThat(randomizedSet.insert(2)).isTrue()
        assertThat(randomizedSet.get()).contains(randomizedSet.getRandom())
        assertThat(randomizedSet.remove(1)).isTrue()
        assertThat(randomizedSet.insert(2)).isFalse()
        assertThat(randomizedSet.getRandom()).isEqualTo(2)
    }
}
