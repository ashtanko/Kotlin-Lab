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

package dev.shtanko.patterns.structural.decorator

import org.slf4j.LoggerFactory

interface Troll {
    fun attack()

    fun getAttackPower(): Int

    fun fleeBattle()
}

class SimpleTroll : Troll {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SimpleTroll::class.java)
        private const val TROLL_ATTACK_POWER = 10
    }

    override fun attack() {
        LOGGER.info("The troll tries to grab you!")
    }

    override fun getAttackPower(): Int {
        return TROLL_ATTACK_POWER
    }

    override fun fleeBattle() {
        LOGGER.info("The troll shrieks in horror and runs away!")
    }
}

class ClubbedTroll(private val decorated: Troll) : Troll {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ClubbedTroll::class.java)
        private const val TROLL_ATTACK_POWER = 10
    }

    override fun attack() {
        decorated.attack()
        LOGGER.info("The troll swings at you with a club!")
    }

    override fun getAttackPower(): Int {
        return decorated.getAttackPower() + TROLL_ATTACK_POWER
    }

    override fun fleeBattle() {
        decorated.fleeBattle()
    }
}
