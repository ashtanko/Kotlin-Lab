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

package dev.shtanko.truth

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class TruthExampleTest {
    @Test
    fun `should return correct result`() {
        val result = 2 + 2
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun testStrings() {
        val message = "Hello, Truth!"
        assertThat(message).contains("Truth")
    }
}

class CollectionTruthTest {
    @Test
    fun testListContents() {
        val fruits = listOf("apple", "banana", "orange")
        assertThat(fruits).containsExactly("banana", "orange", "apple")
    }

    @Test
    fun testMapContents() {
        val map = mapOf("kotlin" to 2.2, "java" to 21)
        assertThat(map).containsEntry("kotlin", 2.2)
        assertThat(map).doesNotContainKey("scala")
    }
}

class NullableTruthTest {
    @Test
    fun testNullValues() {
        val name: String? = null
        assertThat(name).isNull()
    }

    @Test
    fun testNonNull() {
        val language: String = "Kotlin"
        assertThat(language).isNotNull()
    }
}
