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

abstract class DwarvenMineWorker {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FishingBoat::class.java)
    }

    private fun goToSleep() {
        LOGGER.info("{} goes to sleep.", name())
    }

    private fun wakeUp() {
        LOGGER.info("{} wakes up.", name())
    }

    fun goHome() {
        LOGGER.info("{} goes home.", name())
    }

    fun goToMine() {
        LOGGER.info("{} goes to the mine.", name())
    }

    fun performActions(vararg actions: Action) {
        actions.forEach {
            performAction(it)
        }
    }

    abstract fun work()

    abstract fun name(): String

    private fun performAction(action: Action) {
        when (action) {
            is Action.GoToSleep -> {
                goToSleep()
            }

            is Action.WakeUp -> {
                wakeUp()
            }

            is Action.GoHome -> {
                goHome()
            }

            is Action.GoToMine -> {
                goToMine()
            }

            is Action.Work -> {
                work()
            }
        }
    }

    sealed class Action {
        object GoToSleep : Action()
        object WakeUp : Action()
        object GoHome : Action()
        object GoToMine : Action()
        object Work : Action()
    }
}
