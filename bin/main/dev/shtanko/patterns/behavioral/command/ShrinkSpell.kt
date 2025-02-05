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

class ShrinkSpell : Command {

    private lateinit var oldSize: Size

    private lateinit var target: Target

    override fun execute(target: Target) {
        oldSize = target.size
        target.size = Size.SMALL
        this.target = target
    }

    override fun undo() {
        val temp = target.size
        target.size = oldSize
        oldSize = temp
    }

    override fun redo() {
        undo()
    }
}
