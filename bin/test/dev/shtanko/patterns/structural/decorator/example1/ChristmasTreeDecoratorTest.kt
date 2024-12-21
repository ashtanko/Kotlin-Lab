/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.decorator.example1

import dev.shtanko.patterns.structural.decorator.examples.example1.BallsDecorator
import dev.shtanko.patterns.structural.decorator.examples.example1.ChristmasTree
import dev.shtanko.patterns.structural.decorator.examples.example1.LightsDecorator
import dev.shtanko.patterns.structural.decorator.examples.example1.SimpleChristmasTree
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ChristmasTreeDecoratorTest {
    @Test
    fun testSimpleChristmasTree() {
        val simpleTree: ChristmasTree = SimpleChristmasTree()
        Assertions.assertEquals("Simple Christmas tree", simpleTree.decorate())
    }

    @Test
    fun testLightsDecorator() {
        val simpleTree: ChristmasTree = SimpleChristmasTree()
        val treeWithLights: ChristmasTree = LightsDecorator(simpleTree)
        Assertions.assertEquals("Simple Christmas tree with Lights", treeWithLights.decorate())
    }

    @Test
    fun testBallsDecorator() {
        val simpleTree: ChristmasTree = SimpleChristmasTree()
        val treeWithBalls: ChristmasTree = BallsDecorator(simpleTree)
        Assertions.assertEquals("Simple Christmas tree with Balls", treeWithBalls.decorate())
    }

    @Test
    fun testLightsAndBallsDecorator() {
        val simpleTree: ChristmasTree = SimpleChristmasTree()
        val treeWithLightsAndBalls: ChristmasTree = BallsDecorator(LightsDecorator(simpleTree))
        Assertions.assertEquals("Simple Christmas tree with Lights with Balls", treeWithLightsAndBalls.decorate())
    }
}
