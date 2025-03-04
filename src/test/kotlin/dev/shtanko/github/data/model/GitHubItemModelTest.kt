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

package dev.shtanko.github.data.model

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class GitHubItemModelTest {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val sampleJson = """
        {
            "id": 123456,
            "node_id": "MDEwOlJlcG9zaXRvcnkxMjM0NTY=",
            "name": "example-repo",
            "full_name": "example/example-repo",
            "private": true,
            "html_url": "https://github.com/example/example-repo",
            "description": "An example repository.",
            "fork": false,
            "url": "https://api.github.com/repos/example/example-repo",
            "created_at": "2023-01-01T00:00:00Z",
            "updated_at": "2023-01-02T00:00:00Z",
            "pushed_at": "2023-01-03T00:00:00Z",
            "stargazers_count": 42,
            "language": "Kotlin",
            "visibility": "public"
        }
    """.trimIndent()

    @Test
    fun `deserialize JSON to GitHubItemModel`() {
        val item = json.decodeFromString<GitHubItemModel>(sampleJson)

        assertNotNull(item)
        assertEquals(123456, item.id)
        assertEquals("MDEwOlJlcG9zaXRvcnkxMjM0NTY=", item.nodeId)
        assertEquals("example-repo", item.name)
        assertEquals("example/example-repo", item.fullName)
        assertEquals(true, item.private)
        assertEquals("https://github.com/example/example-repo", item.htmlUrl)
        assertEquals("An example repository.", item.description)
        assertEquals(false, item.fork)
        assertEquals("https://api.github.com/repos/example/example-repo", item.url)
        assertEquals("2023-01-01T00:00:00Z", item.createdAt)
        assertEquals("2023-01-02T00:00:00Z", item.updatedAt)
        assertEquals("2023-01-03T00:00:00Z", item.pushedAt)
        assertEquals(42, item.stargazersCount)
        assertEquals("Kotlin", item.language)
        assertEquals("public", item.visibility)
    }

    @Test
    fun `serialize GitHubItemModel to JSON`() {
        val item = GitHubItemModel(
            id = 123456,
            nodeId = "MDEwOlJlcG9zaXRvcnkxMjM0NTY=",
            name = "example-repo",
            fullName = "example/example-repo",
            private = true,
            htmlUrl = "https://github.com/example/example-repo",
            description = "An example repository.",
            fork = false,
            url = "https://api.github.com/repos/example/example-repo",
            createdAt = "2023-01-01T00:00:00Z",
            updatedAt = "2023-01-02T00:00:00Z",
            pushedAt = "2023-01-03T00:00:00Z",
            stargazersCount = 42,
            language = "Kotlin",
            visibility = "public",
        )

        val serializedJson = json.encodeToString<GitHubItemModel>(GitHubItemModel.serializer(), item)

        assertNotNull(serializedJson)
        assert(serializedJson.contains("\"id\":123456"))
        assert(serializedJson.contains("\"node_id\":\"MDEwOlJlcG9zaXRvcnkxMjM0NTY=\""))
        assert(serializedJson.contains("\"name\":\"example-repo\""))
        assert(serializedJson.contains("\"full_name\":\"example/example-repo\""))
        assert(serializedJson.contains("\"private\":true"))
        assert(serializedJson.contains("\"stargazers_count\":42"))
    }

    @Test
    fun `test GitHubItemModel default values`() {
        val item = GitHubItemModel()

        assertNull(item.id)
        assertNull(item.nodeId)
        assertNull(item.name)
        assertNull(item.fullName)
        assertNull(item.private)
        assertNull(item.htmlUrl)
        assertNull(item.description)
        assertNull(item.fork)
        assertNull(item.url)
        assertNull(item.createdAt)
        assertNull(item.updatedAt)
        assertNull(item.pushedAt)
        assertNull(item.language)
        assertNull(item.visibility)
    }
}
