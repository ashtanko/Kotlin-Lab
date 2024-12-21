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

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class PartyTest {

    @Test
    fun `party action test`() {
        val partyMember1 = mock(PartyMember::class.java)
        val partyMember2 = mock(PartyMember::class.java)
        val party: Party = PartyImpl()
        party.addMember(partyMember1)
        party.addMember(partyMember2)

        verify(partyMember1).joinedParty(party)
        verify(partyMember2).joinedParty(party)

        party.act(partyMember1, Action.GOLD)
        verify(partyMember2).partyAction(Action.GOLD)

        verifyNoMoreInteractions(partyMember1, partyMember2)
    }
}
