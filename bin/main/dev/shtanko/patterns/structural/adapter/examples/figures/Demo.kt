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

package dev.shtanko.patterns.structural.adapter.examples.figures

import dev.shtanko.patterns.structural.adapter.examples.figures.adapters.SquarePegAdapter
import dev.shtanko.patterns.structural.adapter.examples.figures.round.RoundHole
import dev.shtanko.patterns.structural.adapter.examples.figures.round.RoundPeg
import dev.shtanko.patterns.structural.adapter.examples.figures.square.SquarePeg

object Demo {

    private const val smallWith = 2.0
    private const val normalWith = 5.0
    private const val largeWith = 20.0

    @JvmStatic
    fun main(args: Array<String>) {
        // Round fits round, no surprise.
        val hole = RoundHole(normalWith)
        val rPeg = RoundPeg(normalWith)
        if (hole.fits(rPeg)) {
            println("Round peg normal fits round hole r5.")
        }

        val smallSqPeg = SquarePeg(smallWith)
        val largeSqPeg = SquarePeg(largeWith)
        // hole.fits(smallSqPeg); // Won't compile.

        // Adapter solves the problem.
        val smallSqPegAdapter = SquarePegAdapter(smallSqPeg)
        val largeSqPegAdapter = SquarePegAdapter(largeSqPeg)
        if (hole.fits(smallSqPegAdapter)) {
            println("Square peg small fits round hole r5.")
        }
        if (!hole.fits(largeSqPegAdapter)) {
            println("Square peg large does not fit into round hole r5.")
        }
    }
}
