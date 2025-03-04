/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.benchmark.executor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// TODO refactor
class DirectInvokeTest {

    /**
     * Test that the `invoke` method correctly executes the action for each item in the list.
     */
    @Test
    fun `should execute action for each item in the list`() {
        // Arrange: Create a mutable list to store results of the action
        val results = mutableListOf<Int>()

        // Define a sample list of integers
        val data = listOf(1, 2, 3)

        // Act: Execute the action using DirectInvoke
        DirectInvoke.invoke(data) { results.add(it) }

        // Assert: Check that each item was processed correctly
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    fun `should not execute action for empty list`() {
        // Arrange: Create a mutable list to store results of the action
        val results = mutableListOf<Int>()

        // Define an empty list
        val data = emptyList<Int>()

        // Act: Execute the action using DirectInvoke
        DirectInvoke.invoke(data) { results.add(it) }

        // Assert: Check that no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }

    /**
     * Test that the `invoke` method correctly executes the action for a list with one item.
     */
    @Test
    fun `should execute action for single item in the list`() {
        // Arrange: Create a mutable list to store results of the action
        val results = mutableListOf<Int>()

        // Define a list with a single item
        val data = listOf(1)

        // Act: Execute the action using DirectInvoke
        DirectInvoke.invoke(data) { results.add(it) }

        // Assert: Check that the results contain only the one item
        assertEquals(listOf(1), results)
    }

    /**
     * Test that the `invoke` method does not modify the original list.
     */
    @Test
    fun `should not modify the original list`() {
        // Arrange: Define a list of integers
        val data = mutableListOf(1, 2, 3)

        // Act: Execute the action using DirectInvoke
        DirectInvoke.invoke(data) { /* No operation on list itself */ }

        // Assert: Verify that the original list is unchanged
        assertEquals(listOf(1, 2, 3), data)
    }
}
