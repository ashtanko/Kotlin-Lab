/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.learn.bit

import dev.shtanko.algorithms.annotations.Bitwise

/**
 * The bitwise OR operation (|) takes two bits as operands and returns 1 if either of the bits is 1.
 */
@Bitwise
object Or {
    /**
     * The bitwise OR operation (|) takes two bits as operands and returns 1 if either of the bits is 1.
     * It returns 0 if both bits are 0.
     *
     * @param x The first integer.
     * @param y The second integer.
     * @return The result of the bitwise OR operation.
     */
    fun simpleOr(x: Int, y: Int): Int {
        return x or y
    }
}
