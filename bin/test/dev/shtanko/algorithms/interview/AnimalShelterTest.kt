/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.interview

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class AnimalShelterTest {

    @Test
    internal fun `animal shelter test`() {
        val animalShelter = AnimalShelter()
        val dog1 = Dog("sean's dog")
        val cat1 = Cat("jim's cat 1")
        val cat2 = Cat("jim's cat 2")
        val dog2 = Dog("my friend's dog")

        animalShelter.enqueue(dog1)
        animalShelter.enqueue(cat1)
        animalShelter.enqueue(cat2)
        animalShelter.enqueue(dog2)

        assertTrue(animalShelter.dequeueAny() == dog1)
        assertTrue(animalShelter.dequeueAny() == cat1)

        assertTrue(animalShelter.dequeueCat() == cat2)
        assertTrue(animalShelter.dequeueDogs() == dog2)
    }
}
