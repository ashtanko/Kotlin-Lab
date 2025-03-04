/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.interview

import java.util.LinkedList

sealed class Animal
data class Dog(val name: String) : Animal()
data class Cat(val name: String) : Animal()

internal class AnimalShelter {

    private val dogs = LinkedList<Timestamped<Dog>>()
    private val cats = LinkedList<Timestamped<Cat>>()

    data class Timestamped<T>(val inner: T, val time: Int) {
        companion object {
            fun <T> make(inner: T): Timestamped<T> = Timestamped(inner, lastCount++)
        }
    }

    fun enqueue(animal: Animal) {
        when (animal) {
            is Dog -> dogs.add(Timestamped.make(animal))
            is Cat -> cats.add(Timestamped.make(animal))
        }
    }

    fun dequeueDogs(): Dog {
        val dog = dogs.first().inner
        dogs.removeFirst()
        return dog
    }

    fun dequeueCat(): Cat {
        val cat = cats.first().inner
        cats.removeFirst()
        return cat
    }

    fun dequeueAny(): Animal {
        val oldestDog = dogs.first()
        val oldestCat = cats.first()

        return if (oldestDog.time < oldestCat.time) {
            dequeueDogs()
        } else {
            dequeueCat()
        }
    }

    companion object {
        private var lastCount = 0
    }
}
