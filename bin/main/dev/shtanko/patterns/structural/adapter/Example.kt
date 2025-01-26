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

package dev.shtanko.patterns.structural.adapter

import org.slf4j.LoggerFactory

fun interface RowingBoat {
    fun row()
}

data class Captain(private val rowingBoat: RowingBoat? = null) {
    fun row() {
        rowingBoat?.row()
    }
}

class FishingBoat {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FishingBoat::class.java)
    }

    fun sail() {
        LOGGER.info("The fishing boat is sailing")
    }
}

class FishingBoatAdapter(private val boat: FishingBoat = FishingBoat()) : RowingBoat {
    override fun row() {
        boat.sail()
    }
}
