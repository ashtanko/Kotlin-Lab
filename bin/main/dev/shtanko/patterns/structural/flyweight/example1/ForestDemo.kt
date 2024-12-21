/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.flyweight.example1

import dev.shtanko.patterns.structural.flyweight.example1.forest.Forest
import java.awt.Color
import kotlin.math.floor

object ForestDemo {
    private const val CANVAS_SIZE = 500
    const val TREES_TO_DRAW = 1000000
    private const val TREE_TYPES = 2
    private const val MB = 1024
    private const val THIRTY = 30
    private const val THIRTY_EIGHT = 38

    @JvmStatic
    fun main(args: Array<String>) {
        createForest()
        displayForestStats()
    }

    fun createForest(): Forest {
        val forest = Forest()
        var i = 0
        while (i < floor((TREES_TO_DRAW / TREE_TYPES).toDouble())) {
            plantTrees(forest)
            i++
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE)
        forest.isVisible = true
        return forest
    }

    fun plantTrees(forest: Forest) {
        forest.plantTree(
            random(0, CANVAS_SIZE),
            random(0, CANVAS_SIZE),
            "Summer Oak",
            Color.GREEN,
            "Oak texture stub",
        )
        forest.plantTree(
            random(0, CANVAS_SIZE),
            random(0, CANVAS_SIZE),
            "Autumn Oak",
            Color.ORANGE,
            "Autumn Oak texture stub",
        )
    }

    private fun displayForestStats() {
        println("$TREES_TO_DRAW trees drawn")
        println("---------------------")
        println("Memory usage:")
        println("Tree size (8 bytes) * $TREES_TO_DRAW")
        println("+ TreeTypes size (~30 bytes) * $TREE_TYPES")
        println("---------------------")
        val totalRamMb = (TREES_TO_DRAW * 8 + TREE_TYPES * THIRTY) / MB / MB
        val oldRamMb = TREES_TO_DRAW * THIRTY_EIGHT / MB / MB
        println(
            "Total: $totalRamMb (instead of  $oldRamMb MB)",
        )
    }

    fun random(min: Int, max: Int): Int {
        return min + (Math.random() * (max - min + 1)).toInt()
    }
}
