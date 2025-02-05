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

package dev.shtanko.patterns.behavioral.strategy

import org.slf4j.LoggerFactory

@FunctionalInterface
interface DragonSlayingStrategy {
    fun execute()
}

class DragonSlayer(var strategy: DragonSlayingStrategy) {

    fun changeStrategy(strategy: DragonSlayingStrategy) {
        this.strategy = strategy
    }

    fun goToBattle() {
        strategy.execute()
    }
}

class MeleeStrategy : DragonSlayingStrategy {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(MeleeStrategy::class.java)
    }

    override fun execute() {
        LOGGER.info("With your Excalibur you sever the dragon's head!")
    }
}

class ProjectileStrategy : DragonSlayingStrategy {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ProjectileStrategy::class.java)
    }

    override fun execute() {
        LOGGER.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!")
    }
}

class SpellStrategy : DragonSlayingStrategy {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SpellStrategy::class.java)
    }

    override fun execute() {
        LOGGER.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!")
    }
}
