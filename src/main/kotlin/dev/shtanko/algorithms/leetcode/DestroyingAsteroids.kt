/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.leetcode

/**
 * 2126. Destroying Asteroids
 * @see <a href="https://leetcode.com/problems/destroying-asteroids/">Source</a>
 */
fun interface DestroyingAsteroids {
    operator fun invoke(mass: Int, asteroids: IntArray): Boolean
}

class DestroyingAsteroidsGreedy : DestroyingAsteroids {
    override operator fun invoke(mass: Int, asteroids: IntArray): Boolean {
        var m = mass.toLong()
        asteroids.sort()
        for (ast in asteroids) {
            if (m < ast) {
                return false
            }
            m += ast.toLong()
        }
        return true
    }
}
