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

package dev.shtanko.patterns.behavioral.command

import java.util.Deque
import java.util.LinkedList
import org.slf4j.LoggerFactory

class Wizard {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(Wizard::class.java)
    }

    private val undoStack: Deque<Command> = LinkedList()
    private val redoStack: Deque<Command> = LinkedList()

    fun castSpell(command: Command, target: Target) {
        LOGGER.info("{} casts {} at {}", this, command, target)
        command.execute(target)
        undoStack.offerLast(command)
    }

    fun undoLastSpell() {
        if (undoStack.isNotEmpty()) {
            val previousSpell = undoStack.pollLast()
            redoStack.offerLast(previousSpell)
            LOGGER.info("{} undoes {}", this, previousSpell)
            previousSpell.undo()
        }
    }

    fun redoLastSpell() {
        if (redoStack.isNotEmpty()) {
            val previousSpell = redoStack.pollLast()
            undoStack.offerLast(previousSpell)
            LOGGER.info("{} redoes {}", this, previousSpell)
            previousSpell.redo()
        }
    }

    override fun toString(): String {
        return "Wizard"
    }
}
