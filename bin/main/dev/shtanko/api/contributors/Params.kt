/*
 * Copyright 2022 Oleksii Shtanko
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

private fun prefNode(): Preferences = Preferences.userRoot().node("ContributorsUI")

data class Params(val username: String, val password: String, val org: String, val variant: Variant)

fun loadStoredParams(): Params {
    return prefNode().run {
        Params(
            get("username", ""),
            get("password", ""),
            get("org", "kotlin"),
            Variant.valueOf(get("variant", Variant.BLOCKING.name)),
        )
    }
}

fun removeStoredParams() {
    prefNode().removeNode()
}

fun saveParams(params: Params) {
    prefNode().apply {
        put("username", params.username)
        put("password", params.password)
        put("org", params.org)
        put("variant", params.variant.name)
        sync()
    }
}
