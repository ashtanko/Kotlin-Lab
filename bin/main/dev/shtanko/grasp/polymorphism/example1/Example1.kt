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

package dev.shtanko.grasp.polymorphism.example1

fun interface Shape {
    fun area(): Double
}

class Circle(private val radius: Double) : Shape {
    override fun area(): Double = Math.PI * radius * radius
}

class Rectangle(private val width: Double, private val height: Double) : Shape {
    override fun area(): Double = width * height
}

class Triangle(private val base: Double, private val height: Double) : Shape {
    companion object {
        private const val HALF = 0.5
    }

    override fun area(): Double = HALF * base * height
}

class ShapeRenderer {
    fun render(shape: Shape) {
        println("Rendering shape with area: ${shape.area()}")
    }
}

fun main() {
    val circle = Circle(5.0)
    val rectangle = Rectangle(3.0, 4.0)
    val triangle = Triangle(6.0, 8.0)

    val shapeRenderer = ShapeRenderer()
    shapeRenderer.render(circle)
    shapeRenderer.render(rectangle)
    shapeRenderer.render(triangle)
}
