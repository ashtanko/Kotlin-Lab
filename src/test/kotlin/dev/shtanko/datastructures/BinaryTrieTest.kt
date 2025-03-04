/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.datastructures

import kotlin.random.Random
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BinaryTrieTest {
    private val trie = BinaryTrie()

    @Test
    fun testInsertAndCount() {
        trie.insert(5)
        trie.insert(25)
        trie.insert(15)

        // Test count for inserted numbers
        assertEquals(1, trie.count(5))
        assertEquals(1, trie.count(25))
        assertEquals(1, trie.count(15))

        // Test count for a number that was not inserted
        assertEquals(0, trie.count(10))
    }

    @Test
    fun testFindMaxXor() {
        trie.insert(5)
        trie.insert(25)
        trie.insert(15)

        val maxXor = trie.findMaxXor(10)
        assertEquals(19, maxXor) // <- wrong, should be 25
    }

    @Test
    fun testLargeDataSet() {
        val randomNumbers = List(100000) { Random.nextInt() }
        randomNumbers.forEach { trie.insert(it) }

        // Test for a number in the large dataset
        val testNum = randomNumbers[50000]
        val count = trie.count(testNum)
        assertTrue(count > 0, "The number $testNum should be in the trie")
    }
}
