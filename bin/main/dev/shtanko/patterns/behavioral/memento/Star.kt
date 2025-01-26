/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.patterns.behavioral.memento

import dev.shtanko.patterns.behavioral.memento.StarType.DEAD
import dev.shtanko.patterns.behavioral.memento.StarType.RED_GIANT
import dev.shtanko.patterns.behavioral.memento.StarType.SUN
import dev.shtanko.patterns.behavioral.memento.StarType.SUPERNOVA
import dev.shtanko.patterns.behavioral.memento.StarType.WHITE_DWARF
import java.util.Locale

class Star(private var type: StarType, private var ageYears: Int, private var massTons: Int) {
    /**
     * Makes time pass for the star.
     */
    fun timePasses() {
        ageYears *= 2
        massTons *= 8
        when (type) {
            RED_GIANT -> type = WHITE_DWARF
            SUN -> type = RED_GIANT
            SUPERNOVA -> type = DEAD
            WHITE_DWARF -> type = SUPERNOVA
            DEAD -> {
                ageYears *= 2
                massTons = 0
            }
        }
    }

    fun getMemento(): StarMemento {
        return StarMementoInternal(type, ageYears, massTons)
    }

    fun setMemento(memento: StarMemento) {
        val (type1, ageYears1, massTons1) = memento as StarMementoInternal
        type = type1
        ageYears = ageYears1
        massTons = massTons1
    }

    override fun toString(): String =
        String.format(Locale.getDefault(), "%s age: %d years mass: %d tons", type.toString(), ageYears, massTons)

    private data class StarMementoInternal(val type: StarType, val ageYears: Int, val massTons: Int) : StarMemento
}
