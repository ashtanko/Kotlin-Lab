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

package dev.shtanko.patterns.structural.facade

import dev.shtanko.patterns.structural.adapter.FishingBoat
import org.slf4j.LoggerFactory

class DwarvenCartOperator : DwarvenMineWorker() {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FishingBoat::class.java)
    }

    override fun work() {
        LOGGER.info("{} moves gold chunks out of the mine.", name())
    }

    override fun name(): String {
        return "Dwarf cart operator"
    }
}
