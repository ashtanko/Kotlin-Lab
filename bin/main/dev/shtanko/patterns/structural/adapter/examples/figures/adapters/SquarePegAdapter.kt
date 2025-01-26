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

package dev.shtanko.patterns.structural.adapter.examples.figures.adapters

import dev.shtanko.patterns.structural.adapter.examples.figures.round.RoundPeg
import dev.shtanko.patterns.structural.adapter.examples.figures.square.SquarePeg
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Adapter allows fitting square pegs into round holes.
 */
class SquarePegAdapter(private val peg: SquarePeg) : RoundPeg(2.0) {
    // Calculate a minimum circle radius, which can fit this peg.
    override var radius: Double
        get() {
            // Calculate a minimum circle radius, which can fit this peg.
            return sqrt((peg.width / 2).pow(2.0) * 2)
        }
        set(radius) {
            super.radius = radius
        }
}
