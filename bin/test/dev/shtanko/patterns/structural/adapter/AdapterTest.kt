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

package dev.shtanko.patterns.structural.adapter

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

internal class AdapterTest {

    companion object {
        private const val FISHING_BEAN = "fisher"

        private const val ROWING_BEAN = "captain"
    }

    private val beans: MutableMap<String, Any> by lazy {
        hashMapOf()
    }

    @BeforeEach
    fun setUp() {
        val fishingBoatAdapter = spy(FishingBoatAdapter())
        beans[FISHING_BEAN] = fishingBoatAdapter

        val adapter = beans[FISHING_BEAN] as FishingBoatAdapter
        val captain = Captain(adapter)
        beans[ROWING_BEAN] = captain
    }

    @Test
    internal fun `adapter pattern test test`() {
        val captain = beans[ROWING_BEAN] as Captain

        // when captain moves
        captain.row()

        // the captain internally calls the battleship object to move
        val adapter = beans[FISHING_BEAN] as RowingBoat
        verify(adapter).row()
    }
}
