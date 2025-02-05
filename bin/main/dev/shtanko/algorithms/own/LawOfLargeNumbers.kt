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

package dev.shtanko.algorithms.own

import kotlin.random.Random

object LawOfLargeNumbers {

    private val whites: MutableList<Int> = mutableListOf()
    private val blacks: MutableList<Int> = mutableListOf()

    @JvmStatic
    fun main(args: Array<String>) {
        repeat(2) {
            val r = Random.nextInt(2)
            if (r == 0) {
                whites.add(r)
            }
        }

        repeat(3) {
            val r = Random.nextInt(2)
            if (r == 1) {
                blacks.add(r)
            }
        }

        println(whites.size)
        println(blacks.size)
        println(whites.size.toFloat() / blacks.size.toFloat())
    }
}
