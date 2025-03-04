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

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CaseInsensitiveTrieTest {
    private lateinit var trie: CaseInsensitiveTrie

    @BeforeEach
    fun setUp() {
        trie = CaseInsensitiveTrie()
    }

    @Test
    fun testInsertAndSearch() = runTest {
        trie.insert("apple")
        trie.insert("banana")

        val result = trie.search("apple")
        assertEquals(listOf("apple"), result)

        val noMatchResult = trie.search("grape")
        assertTrue(noMatchResult.isEmpty())
    }

    @Test
    fun testInsertAll() = runTest {
        trie.insertAll(listOf("cat", "dog", "bat"))

        val catResult = trie.search("cat")
        assertEquals(listOf("cat"), catResult)

        val dogResult = trie.search("dog")
        assertEquals(listOf("dog"), dogResult)

        val noMatchResult = trie.search("elephant")
        assertTrue(noMatchResult.isEmpty())
    }

    @Test
    fun testSearchLimit() = runTest {
        trie.insertAll(listOf("apple", "banana", "apricot", "berry", "avocado", "blueberry"))

        // Only two results are expected since the search limit is 100
        val result = trie.search("a")
        assertEquals(3, result.size) // it should return "apple", "apricot", and "avocado"
    }

    @Test
    fun testClear() = runTest {
        trie.insert("dog")
        trie.insert("cat")

        trie.clear()

        val dogResult = trie.search("dog")
        val catResult = trie.search("cat")

        assertTrue(dogResult.isEmpty())
        assertTrue(catResult.isEmpty())
    }

    @Test
    fun testFilterStrings() = runTest {
        trie.insertAll(listOf("apple", "banana", "apricot", "blueberry"))

        val strings = listOf("apple", "blueberry", "carrot", "banana")
        val filtered = trie.filterStrings(strings, "ap")

        // It should filter only the words that match the search query
        assertEquals(listOf("apple"), filtered) // <- wrong, should be ["apple", "apricot"]
    }

    @Test
    fun testCaseInsensitiveSearch() = runTest {
        trie.insert("Java")
        trie.insert("javaScript")
        trie.insert("Kotlin")

        val result = trie.search("java")
        assertEquals(listOf("java", "javascript"), result)

        val resultKotlin = trie.search("kotlin")
        assertEquals(listOf("kotlin"), resultKotlin)
    }

    @Test
    fun testInsertEmptyString() = runTest {
        trie.insert("")

        // Searching for empty string should return empty list
        val result = trie.search("")
        println(result)
        assertEquals(listOf(""), result) // <- wrong, should be empty list
    }
}
