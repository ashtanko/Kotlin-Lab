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

import org.slf4j.LoggerFactory

abstract class PartyMemberBase : PartyMember {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(PartyMemberBase::class.java)
    }

    protected var party: Party? = null

    override fun joinedParty(party: Party) {
        LOGGER.info("$this joins the party")
        this.party = party
    }

    override fun partyAction(action: Action) {
        LOGGER.info("$this ${action.description}")
    }

    override fun act(action: Action) {
        if (party != null) {
            LOGGER.info("$this $action")
            party?.act(this, action)
        }
    }

    abstract override fun toString(): String
}
