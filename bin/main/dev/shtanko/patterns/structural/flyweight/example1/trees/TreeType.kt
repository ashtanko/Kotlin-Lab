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

package dev.shtanko.patterns.structural.flyweight.example1.trees

import java.awt.Color
import java.awt.Graphics

class TreeType(val name: String, val color: Color, val otherTreeData: String) {
    fun draw(g: Graphics, x: Int, y: Int) {
        g.color = Color.BLACK
        g.fillRect(x - 1, y, 3, 5)
        g.color = color
        g.fillOval(x - 5, y - 10, 10, 10)
    }
}
