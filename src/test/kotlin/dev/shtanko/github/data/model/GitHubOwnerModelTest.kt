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

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class GitHubOwnerModelTest {

    private val json = Json { prettyPrint = true }

    @Test
    fun `serialize GitHubOwnerModel to JSON`() {
        val owner = GitHubOwnerModel(
            login = "octocat",
            id = 1,
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            url = "https://api.github.com/users/octocat",
            htmlUrl = "https://github.com/octocat",
            followersUrl = "https://api.github.com/users/octocat/followers",
            followingUrl = "https://api.github.com/users/octocat/following{/other_user}",
            gistsUrl = "https://api.github.com/users/octocat/gists{/gist_id}",
            starredUrl = "https://api.github.com/users/octocat/starred{/owner}{/repo}",
            subscriptionsUrl = "https://api.github.com/users/octocat/subscriptions",
            organizationsUrl = "https://api.github.com/users/octocat/orgs",
            reposUrl = "https://api.github.com/users/octocat/repos",
            eventsUrl = "https://api.github.com/users/octocat/events{/privacy}",
            receivedEventsUrl = "https://api.github.com/users/octocat/received_events",
            type = "User",
            userViewType = "Admin",
            siteAdmin = true,
        )

        val serialized = json.encodeToString(GitHubOwnerModel.serializer(), owner)

        assertNotNull(serialized)
        println(serialized)

        val expectedJson = """
            {
                "login": "octocat",
                "id": 1,
                "node_id": "MDQ6VXNlcjE=",
                "avatar_url": "https://avatars.githubusercontent.com/u/1?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/octocat",
                "html_url": "https://github.com/octocat",
                "followers_url": "https://api.github.com/users/octocat/followers",
                "following_url": "https://api.github.com/users/octocat/following{/other_user}",
                "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
                "organizations_url": "https://api.github.com/users/octocat/orgs",
                "repos_url": "https://api.github.com/users/octocat/repos",
                "events_url": "https://api.github.com/users/octocat/events{/privacy}",
                "received_events_url": "https://api.github.com/users/octocat/received_events",
                "type": "User",
                "user_view_type": "Admin",
                "site_admin": true
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))
    }

    @Test
    fun `deserialize JSON to GitHubOwnerModel`() {
        val jsonInput = """
            {
                "login": "octocat",
                "id": 1,
                "node_id": "MDQ6VXNlcjE=",
                "avatar_url": "https://avatars.githubusercontent.com/u/1?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/octocat",
                "html_url": "https://github.com/octocat",
                "followers_url": "https://api.github.com/users/octocat/followers",
                "following_url": "https://api.github.com/users/octocat/following{/other_user}",
                "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
                "organizations_url": "https://api.github.com/users/octocat/orgs",
                "repos_url": "https://api.github.com/users/octocat/repos",
                "events_url": "https://api.github.com/users/octocat/events{/privacy}",
                "received_events_url": "https://api.github.com/users/octocat/received_events",
                "type": "User",
                "user_view_type": "Admin",
                "site_admin": true
            }
        """.trimIndent()

        val owner = json.decodeFromString<GitHubOwnerModel>(jsonInput)

        assertNotNull(owner)
        assertEquals("octocat", owner.login)
        assertEquals(1, owner.id)
        assertEquals("MDQ6VXNlcjE=", owner.nodeId)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", owner.avatarUrl)
        assertEquals("", owner.gravatarId)
        assertEquals("https://api.github.com/users/octocat", owner.url)
        assertEquals("https://github.com/octocat", owner.htmlUrl)
        assertEquals("https://api.github.com/users/octocat/followers", owner.followersUrl)
        assertEquals("https://api.github.com/users/octocat/following{/other_user}", owner.followingUrl)
        assertEquals("https://api.github.com/users/octocat/gists{/gist_id}", owner.gistsUrl)
        assertEquals("https://api.github.com/users/octocat/starred{/owner}{/repo}", owner.starredUrl)
        assertEquals("https://api.github.com/users/octocat/subscriptions", owner.subscriptionsUrl)
        assertEquals("https://api.github.com/users/octocat/orgs", owner.organizationsUrl)
        assertEquals("https://api.github.com/users/octocat/repos", owner.reposUrl)
        assertEquals("https://api.github.com/users/octocat/events{/privacy}", owner.eventsUrl)
        assertEquals("https://api.github.com/users/octocat/received_events", owner.receivedEventsUrl)
        assertEquals("User", owner.type)
        assertEquals("Admin", owner.userViewType)
        assertEquals(true, owner.siteAdmin)
    }

    @Test
    fun `handle null values during serialization - should keep null value`() {
        val owner = GitHubOwnerModel()

        val json = Json {
            prettyPrint = true
            explicitNulls = true
            encodeDefaults = true
        }

        val serialized = json.encodeToString(GitHubOwnerModel.serializer(), owner)

        val expectedJson = """
            {
                "login": null,
                "id": null,
                "node_id": null,
                "avatar_url": null,
                "gravatar_id": null,
                "url": null,
                "html_url": null,
                "followers_url": null,
                "following_url": null,
                "gists_url": null,
                "starred_url": null,
                "subscriptions_url": null,
                "organizations_url": null,
                "repos_url": null,
                "events_url": null,
                "received_events_url": null,
                "type": null,
                "user_view_type": null,
                "site_admin": null
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))
    }

    @Test
    fun `handle null values during serialization - should not keep null value`() {
        val owner = GitHubOwnerModel()

        val json = Json {
            prettyPrint = true
            explicitNulls = false
            encodeDefaults = false
        }

        val serialized = json.encodeToString(GitHubOwnerModel.serializer(), owner)

        print(serialized)

        val expectedJson = """
            {
            }
        """.trimIndent()

        assertEquals(expectedJson.replace("\\s".toRegex(), ""), serialized.replace("\\s".toRegex(), ""))
    }
}
