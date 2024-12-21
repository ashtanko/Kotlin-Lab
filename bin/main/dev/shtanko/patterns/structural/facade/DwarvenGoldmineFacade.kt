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

class DwarvenGoldmineFacade {

    private val workers: List<DwarvenMineWorker> = listOf(
        DwarvenGoldDigger(),
        DwarvenCartOperator(),
        DwarvenTunnelDigger(),
    )

    fun startNewDay() {
        makeActions(workers, DwarvenMineWorker.Action.WakeUp, DwarvenMineWorker.Action.GoToMine)
    }

    fun digOutGold() {
        makeActions(workers, DwarvenMineWorker.Action.Work)
    }

    fun endDay() {
        makeActions(workers, DwarvenMineWorker.Action.GoHome, DwarvenMineWorker.Action.GoToSleep)
    }

    @Suppress("SpreadOperator")
    private fun makeActions(
        workers: Collection<DwarvenMineWorker>,
        vararg actions: DwarvenMineWorker.Action,
    ) {
        workers.forEach {
            it.performActions(*actions)
        }
    }
}
