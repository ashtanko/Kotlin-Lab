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

package dev.shtanko.kotlinlang.guard

object GuardExample {
    @JvmStatic
    fun main(args: Array<String>) {
        feedAnimal(Animal.Cat(false))
    }

    private fun feedAnimal(animal: Animal) {
        when (animal) {
            // Branch with only the primary condition. Returns `feedDog()` when `Animal` is `Dog`
            is Animal.Dog -> animal.feedDog()
            // Branch with both primary and guard conditions. Returns `feedCat()` when `Animal` is `Cat` and is not `mouseHunter`
            is Animal.Cat if !animal.mouseHunter -> animal.feedCat()
            // Returns "Unknown animal" if none of the above conditions match
            else -> println("Unknown animal")
        }
    }

    sealed interface Animal {
        data class Cat(val mouseHunter: Boolean) : Animal {
            fun feedCat() {
                // ignore
            }
        }

        data class Dog(val breed: String) : Animal {
            fun feedDog() {
                // ignore
            }
        }
    }
}
