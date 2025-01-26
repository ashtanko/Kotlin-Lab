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

import dev.shtanko.patterns.utils.InMemoryAppender
import java.util.stream.Stream
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class DragonSlayingStrategyTest {

    private lateinit var appender: InMemoryAppender

    @BeforeEach
    fun setUp() {
        appender = InMemoryAppender()
    }

    @AfterEach
    fun tearDown() {
        appender.stop()
    }

    /**
     * Test if executing the strategy gives the correct response.
     */
    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun testExecute(strategy: DragonSlayingStrategy, expectedResult: String) {
        strategy.execute()
        assertEquals(expectedResult, appender.lastMessage)
        assertEquals(1, appender.logSize)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = listOf(
            Arguments.of(MeleeStrategy(), "With your Excalibur you sever the dragon's head!"),
            Arguments.of(
                ProjectileStrategy(),
                "You shoot the dragon with the magical crossbow and it falls dead on the ground!",
            ),
            Arguments.of(
                SpellStrategy(),
                "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!",
            ),
        ).stream()
    }
}
