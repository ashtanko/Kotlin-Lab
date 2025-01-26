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

package dev.shtanko.mockito

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class KotlinMockitoExampleTest {
    @Test
    fun `should do something with mocked dependency`() {
        // Arrange
        val mockedDependency = mock<ExampleDependency>()
        whenever(mockedDependency.someMethod("arg1")).thenReturn("result")

        val myService = ExampleService(mockedDependency)

        // Act
        val result = myService.doSomething("arg1")

        // Assert
        assertEquals("result", result)
        verify(mockedDependency).someMethod("arg1")
    }
}

private interface ExampleDependency {
    fun someMethod(arg: String): String
}

private class ExampleService(private val dependency: ExampleDependency) {
    fun doSomething(arg: String): String {
        return dependency.someMethod(arg)
    }
}
