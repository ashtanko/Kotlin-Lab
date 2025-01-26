/*
 * Copyright 2024 Oleksii Shtanko
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

/**
 * A mock implementation of the InputReader interface for testing purposes.
 *
 * This class allows to simulate user input by providing a list of predefined inputs.
 * Each call to readLine() will return the next input from the list.
 * When all inputs have been returned, readLine() will return null.
 *
 * @property inputs The list of inputs that this reader will return.
 * @property currentIndex The index of the next input to return.
 */
class MockInputReader(private val inputs: List<String>) : InputReader {
    private var currentIndex = 0

    /**
     * Returns the next input from the list or null if all inputs have been returned.
     *
     * @return The next input from the list or null.
     */
    override fun readLine(): String? {
        if (currentIndex >= inputs.size) return null
        return inputs[currentIndex++]
    }
}
