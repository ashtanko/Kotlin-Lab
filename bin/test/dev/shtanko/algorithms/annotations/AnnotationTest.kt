/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.annotations

import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@Backtracking(info = "TestClassAnnotation")
class AnnotatedClass

class AnnotationTest {
    @Backtracking(info = "TestFunctionAnnotation")
    fun annotatedFunction() {
        // ignore empty function block
    }

    @Test
    fun `test class has Backtracking annotation`() {
        val annotation = AnnotatedClass::class.findAnnotation<Backtracking>()
        assertNotNull(annotation)
        assertEquals("TestClassAnnotation", annotation?.info)
    }

    @Test
    fun `test function has Backtracking annotation`() {
        val function = AnnotationTest::class.members.find { it.name == "annotatedFunction" }
        val annotation = function?.findAnnotation<Backtracking>()
        assertNotNull(annotation)
        assertEquals("TestFunctionAnnotation", annotation?.info)
    }

    @Test
    fun `test class without annotation`() {
        class NonAnnotatedClass

        val hasAnnotation = NonAnnotatedClass::class.hasAnnotation<Backtracking>()
        assertFalse(hasAnnotation)
    }
}
