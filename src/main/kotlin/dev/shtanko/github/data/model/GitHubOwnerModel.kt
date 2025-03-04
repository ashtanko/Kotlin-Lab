/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.github.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubOwnerModel(
    val login: String? = null,
    val id: Int? = null,
    @SerialName("node_id") val nodeId: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("gravatar_id") val gravatarId: String? = null,
    val url: String? = null,
    @SerialName("html_url") val htmlUrl: String? = null,
    @SerialName("followers_url") val followersUrl: String? = null,
    @SerialName("following_url") val followingUrl: String? = null,
    @SerialName("gists_url") val gistsUrl: String? = null,
    @SerialName("starred_url") val starredUrl: String? = null,
    @SerialName("subscriptions_url") val subscriptionsUrl: String? = null,
    @SerialName("organizations_url") val organizationsUrl: String? = null,
    @SerialName("repos_url") val reposUrl: String? = null,
    @SerialName("events_url") val eventsUrl: String? = null,
    @SerialName("received_events_url") val receivedEventsUrl: String? = null,
    val type: String? = null,
    @SerialName("user_view_type") val userViewType: String? = null,
    @SerialName("site_admin") val siteAdmin: Boolean? = null,
)
