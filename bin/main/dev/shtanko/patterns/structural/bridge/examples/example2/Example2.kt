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

package dev.shtanko.patterns.structural.bridge.examples.example2

// Implementation hierarchy: Colors
fun interface Color {
    fun applyColor()
}

class RedColor : Color {
    override fun applyColor() {
        println("Applying red color")
    }
}

class BlueColor : Color {
    override fun applyColor() {
        println("Applying blue color")
    }
}

// Abstraction hierarchy: Shapes
abstract class Shape(protected val color: Color) {
    abstract fun draw()
}

class Circle(color: Color) : Shape(color) {
    override fun draw() {
        println("Drawing a circle")
        color.applyColor()
    }
}

class Square(color: Color) : Shape(color) {
    override fun draw() {
        println("Drawing a square")
        color.applyColor()
    }
}
