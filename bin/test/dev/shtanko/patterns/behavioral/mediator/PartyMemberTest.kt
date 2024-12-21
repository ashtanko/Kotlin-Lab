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

package dev.shtanko.patterns.behavioral.mediator

import dev.shtanko.patterns.utils.InMemoryAppender
import java.util.function.Supplier
import java.util.stream.Stream
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class PartyMemberTest {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of()
    }

    companion object {
        @JvmStatic
        fun dataProvider(): List<Any> {
            return listOf(
                arrayOf<Supplier<PartyMember>>(Supplier { Hobbit() }),
                arrayOf<Supplier<PartyMember>>(Supplier { Hunter() }),
                arrayOf<Supplier<PartyMember>>(Supplier { Rogue() }),
                arrayOf<Supplier<PartyMember>>(Supplier { Wizard() }),
            )
        }
    }

    private lateinit var appender: InMemoryAppender

    @BeforeEach
    fun setUp() {
        appender = InMemoryAppender(PartyMemberBase::class.java)
    }

    @AfterEach
    fun tearDown() {
        appender.stop()
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `party action test`(memberSupplier: Supplier<PartyMember>) {
        val member = memberSupplier.get()
        for (action in Action.values()) {
            member.partyAction(action)
            assertEquals(member.toString() + " " + action.description, appender.lastMessage)
        }
        assertEquals(Action.values().size, appender.logSize)
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `act test`(memberSupplier: Supplier<PartyMember>) {
        val member = memberSupplier.get()
        member.act(Action.GOLD)
        assertEquals(0, appender.logSize)

        val party = mock(Party::class.java)
        member.joinedParty(party)
        assertEquals("$member joins the party", appender.lastMessage)

        for (action in Action.values()) {
            member.act(action)
            assertEquals("$member $action", appender.lastMessage)
            verify<Party>(party).act(member, action)
        }

        assertEquals(Action.values().size + 1, appender.logSize)
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `to string test`(memberSupplier: Supplier<PartyMember>) {
        val member = memberSupplier.get()
        val memberClass: Class<out PartyMember?> = member::class.java
        assertEquals(memberClass.simpleName, member.toString())
    }
}
