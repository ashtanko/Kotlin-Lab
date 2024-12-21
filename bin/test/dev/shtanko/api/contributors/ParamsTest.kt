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

package dev.shtanko.api.contributors

import java.util.prefs.Preferences
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ParamsTest {

    private lateinit var params: Params

    @BeforeEach
    fun setUp() {
        params = Params("username", "password", "org", Variant.BLOCKING)
    }

    @Test
    fun `loadStoredParams returns correct Params`() {
        val prefNode = Preferences.userRoot().node("ContributorsUI")
        prefNode.put("username", "username")
        prefNode.put("password", "password")
        prefNode.put("org", "org")
        prefNode.put("variant", Variant.BLOCKING.name)

        val expectedParams = Params("username", "password", "org", Variant.BLOCKING)
        val actualParams = loadStoredParams()

        assertEquals(expectedParams, actualParams)
    }

    @Test
    fun `loadStoredParams returns default Params when no stored Params`() {
        val prefNode = Preferences.userRoot().node("ContributorsUI")
        prefNode.removeNode()

        val expectedParams = Params("", "", "kotlin", Variant.BLOCKING)
        val actualParams = loadStoredParams()

        assertEquals(expectedParams, actualParams)
    }

    @Test
    fun `saveParams correctly stores Params`() {
        saveParams(params)

        val prefNode = Preferences.userRoot().node("ContributorsUI")
        val storedUsername = prefNode.get("username", "")
        val storedPassword = prefNode.get("password", "")
        val storedOrg = prefNode.get("org", "")
        val storedVariant = Variant.valueOf(prefNode.get("variant", ""))

        assertEquals(params.username, storedUsername)
        assertEquals(params.password, storedPassword)
        assertEquals(params.org, storedOrg)
        assertEquals(params.variant, storedVariant)
    }

    @Test
    fun `removeStoredParams correctly removes stored Params`() {
        saveParams(params)
        removeStoredParams()

        val prefNode = Preferences.userRoot().node("ContributorsUI")
        val storedUsername = prefNode.get("username", null)
        val storedPassword = prefNode.get("password", null)
        val storedOrg = prefNode.get("org", null)
        val storedVariant = prefNode.get("variant", null)

        assertEquals(null, storedUsername)
        assertEquals(null, storedPassword)
        assertEquals(null, storedOrg)
        assertEquals(null, storedVariant)
    }
}
