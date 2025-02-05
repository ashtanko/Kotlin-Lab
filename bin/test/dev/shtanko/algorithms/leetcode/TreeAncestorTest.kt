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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TreeAncestorTest {

    private lateinit var treeAncestor: TreeAncestor

    @BeforeEach
    fun setup() {
        treeAncestor = TreeAncestor(7, intArrayOf(-1, 0, 0, 1, 1, 2, 2))
    }

    @Test
    @DisplayName("Returns correct ancestor when k is less than node depth")
    fun returnsCorrectAncestorWhenKIsLessThanNodeDepth() {
        assertEquals(1, treeAncestor.getKthAncestor(3, 1))
        assertEquals(0, treeAncestor.getKthAncestor(5, 2))
    }

    @Test
    @DisplayName("Returns -1 when k is greater than node depth")
    fun returnsMinusOneWhenKIsGreaterThanNodeDepth() {
        assertEquals(-1, treeAncestor.getKthAncestor(6, 3))
    }

    @Test
    @DisplayName("Returns correct ancestor when k is equal to node depth")
    fun returnsCorrectAncestorWhenKIsEqualToNodeDepth() {
        assertEquals(-1, treeAncestor.getKthAncestor(2, 2))
    }

    @Test
    fun `tree ancestor test`() {
        assertThat(treeAncestor.getKthAncestor(3, 1)).isEqualTo(1)
        assertThat(treeAncestor.getKthAncestor(5, 2)).isEqualTo(0)
        assertThat(treeAncestor.getKthAncestor(6, 3)).isEqualTo(-1)
    }
}
