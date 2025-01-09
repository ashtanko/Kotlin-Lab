/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.github.data.model

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class SearchResponseModelTest {

    private val json = Json { prettyPrint = true }

    @Test
    fun `serialize SearchResponseModel to JSON`() {
        val searchResponse = SearchResponseModel(
            totalCount = 42,
            incompleteResults = false,
            items = listOf(
                GitHubItemModel(id = 1, name = "Repo1"),
                GitHubItemModel(id = 2, name = "Repo2"),
            ),
        )

        val serialized = json.encodeToString(SearchResponseModel.serializer(), searchResponse)

        assertNotNull(serialized)
        println(serialized)

        val expectedJson = """
            {
                "total_count": 42,
                "incomplete_results": false,
                "items": [
                    {
                        "id": 1,
                        "name": "Repo1"
                    },
                    {
                        "id": 2,
                        "name": "Repo2"
                    }
                ]
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))
    }

    @Test
    fun `deserialize JSON to SearchResponseModel`() {
        val jsonInput = """
            {
                "total_count": 42,
                "incomplete_results": false,
                "items": [
                    {
                        "id": 1,
                        "name": "Repo1"
                    },
                    {
                        "id": 2,
                        "name": "Repo2"
                    }
                ]
            }
        """.trimIndent()

        val searchResponse = json.decodeFromString<SearchResponseModel>(jsonInput)

        assertNotNull(searchResponse)
        assertEquals(42, searchResponse.totalCount)
        assertEquals(false, searchResponse.incompleteResults)
        assertNotNull(searchResponse.items)
        assertEquals(2, searchResponse.items?.size)
        assertEquals(1, searchResponse.items?.get(0)?.id)
        assertEquals("Repo1", searchResponse.items?.get(0)?.name)
        assertEquals(2, searchResponse.items?.get(1)?.id)
        assertEquals("Repo2", searchResponse.items?.get(1)?.name)
    }

    @Test
    fun `serialize and deserialize with null values`() {
        val searchResponse = SearchResponseModel()

        val json = Json {
            prettyPrint = true
            explicitNulls = true
            encodeDefaults = true
        }

        val serialized = json.encodeToString(SearchResponseModel.serializer(), searchResponse)
        println(serialized)

        val expectedJson = """
            {
                "total_count": null,
                "incomplete_results": null,
                "items": null
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))

        val deserialized = json.decodeFromString<SearchResponseModel>(serialized)

        assertNotNull(deserialized)
        assertNull(deserialized.totalCount)
        assertNull(deserialized.incompleteResults)
        assertNull(deserialized.items)
    }

    @Test
    fun `serialize and deserialize without null values`() {
        val searchResponse = SearchResponseModel()

        val json = Json {
            prettyPrint = true
            explicitNulls = true
            encodeDefaults = false
        }

        val serialized = json.encodeToString(SearchResponseModel.serializer(), searchResponse)
        println(serialized)

        val expectedJson = """
            {
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))

        val deserialized = json.decodeFromString<SearchResponseModel>(serialized)

        assertNotNull(deserialized)
        assertNull(deserialized.totalCount)
        assertNull(deserialized.incompleteResults)
        assertNull(deserialized.items)
    }

    @Test
    fun `handle empty items list`() {
        val searchResponse = SearchResponseModel(
            totalCount = 0,
            incompleteResults = false,
            items = emptyList(),
        )

        val serialized = json.encodeToString(SearchResponseModel.serializer(), searchResponse)

        val expectedJson = """
            {
                "total_count": 0,
                "incomplete_results": false,
                "items": []
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))

        val deserialized = json.decodeFromString<SearchResponseModel>(serialized)

        assertNotNull(deserialized)
        assertEquals(0, deserialized.totalCount)
        assertEquals(false, deserialized.incompleteResults)
        assertNotNull(deserialized.items)
        assertEquals(0, deserialized.items?.size)
    }
}
