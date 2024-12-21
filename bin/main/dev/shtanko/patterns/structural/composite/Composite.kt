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

package dev.shtanko.patterns.structural.composite

open class LetterComposite {

    private val children: MutableList<LetterComposite> = ArrayList()

    fun add(letter: LetterComposite) {
        children.add(letter)
    }

    fun count(): Int {
        return children.size
    }

    open fun printThisBefore() {
        // TODO comment explaining why the method is empty
    }

    open fun printThisAfter() {
        // TODO comment explaining why the method is empty
    }

    fun print() {
        printThisBefore()
        children.forEach(LetterComposite::print)
        printThisAfter()
    }
}

class Letter(private val character: Char) : LetterComposite() {
    override fun printThisBefore() {
        print(character)
    }
}

class Word(vararg letters: Char) : LetterComposite() {
    init {
        for (letter in letters) {
            this.add(Letter(letter))
        }
    }

    override fun printThisBefore() {
        print(" ")
    }
}

class Sentence(words: List<Word>) : LetterComposite() {
    init {
        words.forEach(this::add)
    }

    override fun printThisAfter() {
        print(".")
    }
}

class Messenger {
    fun messageFromOrcs(): LetterComposite {
        val words = listOf(
            Word('W', 'h', 'e', 'r', 'e'),
            Word('t', 'h', 'e', 'r', 'e'),
            Word('i', 's'),
            Word('a'),
            Word('w', 'h', 'i', 'p'),
            Word('t', 'h', 'e', 'r', 'e'),
            Word('i', 's'),
            Word('a'),
            Word('w', 'a', 'y'),
        )
        return Sentence(words)
    }

    fun messageFromElves(): LetterComposite {
        val words = listOf(
            Word('M', 'u', 'c', 'h'),
            Word('w', 'i', 'n', 'd'),
            Word('p', 'o', 'u', 'r', 's'),
            Word('f', 'r', 'o', 'm'),
            Word('y', 'o', 'u', 'r'),
            Word('m', 'o', 'u', 't', 'h'),
        )
        return Sentence(words)
    }
}
