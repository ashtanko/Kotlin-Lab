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

package dev.shtanko.utils

import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import kotlin.test.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ResourceTest {
    @Test
    fun `readJsonFromResource should return null for non-existent file`() {
        val result = "non_existent_file.json".readJsonFromResource()
        assertNull(result)
    }

    @Test
    fun `readJsonFromResource should return content for existing file`() {
        val fileName = "test_file.json"
        val fileContent = """{"key": "value"}"""
        createTestResourceFile(fileName, fileContent)

        val result = fileName.readJsonFromResource()
        assertNotNull(result)
        assertEquals(fileContent, result)

        deleteTestResourceFile(fileName)
    }

    @Test
    fun `readImageBytes should load file correctly`() {
        val path: ResourceFilePath = "images/bd.jpg"
        val bytes = path.readImageBytes()

        assertNotNull(bytes)
        assertTrue(bytes.isNotEmpty(), "Image byte array should not be empty")

        // Optional: write a copy to verify output manually
        val tempFile = Files.createTempFile("copy-", ".png")
        Files.write(tempFile, bytes)

        assertTrue(Files.exists(tempFile), "Copied image file should exist")
        assertTrue(Files.size(tempFile) > 0, "Copied image file should not be empty")
    }


    @Test
    fun `readImageBytes should throw error if file does not exist`() {
        val invalidPath: ResourceFilePath = "images/nonexistent.png"

        val exception = assertThrows<IllegalStateException> {
            invalidPath.readImageBytes()
        }

        assertEquals("Image not found", exception.message)
    }

    private fun createTestResourceFile(fileName: String, content: String) {
        val file = File(javaClass.getResource("/").path + fileName)
        FileWriter(file).use { it.write(content) }
    }

    private fun deleteTestResourceFile(fileName: String) {
        File(javaClass.getResource("/").path + fileName).delete()
    }
}
