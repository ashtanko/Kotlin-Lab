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
import org.junit.jupiter.api.Test

class GitHubLicenseModelTest {

    private val json = Json {
        prettyPrint = true
    }

    @Test
    fun `serialize GitHubLicenseModel to JSON`() {
        val license = GitHubLicenseModel(
            key = "mit",
            name = "MIT License",
            spdxId = "MIT",
            url = "https://opensource.org/licenses/MIT",
            nodeId = "MDc6TGljZW5zZW1pdA==",
        )

        val serialized = json.encodeToString(GitHubLicenseModel.serializer(), license)

        assertNotNull(serialized)
        println(serialized)

        val expectedJson = """
            {
                "key": "mit",
                "name": "MIT License",
                "spdx_id": "MIT",
                "url": "https://opensource.org/licenses/MIT",
                "node_id": "MDc6TGljZW5zZW1pdA=="
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))
    }

    @Test
    fun `deserialize JSON to GitHubLicenseModel`() {
        val jsonInput = """
            {
                "key": "apache-2.0",
                "name": "Apache License 2.0",
                "spdx_id": "Apache-2.0",
                "url": "https://opensource.org/licenses/Apache-2.0",
                "node_id": "MDc6TGljZW5zZWFwYWNoZQ=="
            }
        """.trimIndent()

        val license = json.decodeFromString<GitHubLicenseModel>(jsonInput)

        assertNotNull(license)
        assertEquals("apache-2.0", license.key)
        assertEquals("Apache License 2.0", license.name)
        assertEquals("Apache-2.0", license.spdxId)
        assertEquals("https://opensource.org/licenses/Apache-2.0", license.url)
        assertEquals("MDc6TGljZW5zZWFwYWNoZQ==", license.nodeId)
    }

    @Test
    fun `handle null values during serialization`() {
        val license = GitHubLicenseModel(
            key = null,
            name = null,
            spdxId = null,
            url = null,
            nodeId = null,
        )

        val serialized = json.encodeToString(GitHubLicenseModel.serializer(), license)

        val expectedJson = """
            {
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))
    }
}
