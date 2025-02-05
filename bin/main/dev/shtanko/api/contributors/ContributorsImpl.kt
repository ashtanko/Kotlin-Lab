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

import dev.shtanko.api.User
import java.awt.event.ActionListener
import kotlinx.coroutines.Job

class ContributorsImpl : Contributors {
    override val job = Job()

    init {
        // TODO
    }

    override fun getSelectedVariant() = Variant.BLOCKING

    override fun updateContributors(users: List<User>) {
        if (users.isNotEmpty()) {
            // TODO
        } else {
            // TODO
        }
    }

    override fun setLoadingStatus(text: String, inProgress: Boolean) {
        // TODO
    }

    override fun setActionsStatus(newLoadingEnabled: Boolean, cancellationEnabled: Boolean) {
        // TODO
    }

    override fun addCancelListener(listener: ActionListener) {
        // TODO
    }

    override fun removeCancelListener(listener: ActionListener) {
        // TODO
    }

    override fun addLoadListener(listener: () -> Unit) {
        // TODO
    }

    override fun addOnWindowClosingListener(listener: () -> Unit) {
        // TODO
    }

    override fun setParams(params: Params) {
        getParams()
        if (params.username.isEmpty() && params.password.isEmpty()) {
            removeStoredParams()
        } else {
            saveParams(params)
        }
    }

    override fun getParams(): Params {
        return Params("ashtanko", "", "org", getSelectedVariant())
    }
}
