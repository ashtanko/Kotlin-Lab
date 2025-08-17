package dev.shtanko.api

import dev.shtanko.api.model.Repo
import dev.shtanko.api.model.User
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class KtorGitHubApiTest {

    private val testJson = Json { ignoreUnknownKeys = true }

    @Test
    fun `getOrgRepos should parse JSON into Repo list`() = runTest {
        val expectedRepos = listOf(
            Repo(id = 1, name = "Repo1"),
            Repo(id = 2, name = "Repo2"),
        )
        val mockEngine = MockEngine { _ ->
            respond(
                testJson.encodeToString(expectedRepos),
                HttpStatusCode.OK,
                headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) { json(testJson) }
        }

        val api = KtorGitHubApi(client)
        val repos = api.getOrgRepos("testOrg")
        assertEquals(expectedRepos, repos)
    }

    @Test
    fun `getRepoContributors should parse JSON into User list`() = runTest {
        val expectedUsers = listOf(
            User(login = "alice", contributions = 10),
            User(login = "bob", contributions = 20),
        )
        val mockEngine = MockEngine { _ ->
            respond(
                testJson.encodeToString(expectedUsers),
                HttpStatusCode.OK,
                headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) { json(testJson) }
        }

        val api = KtorGitHubApi(client)
        val users = api.getRepoContributors("testOwner", "testRepo")
        assertEquals(expectedUsers, users)
    }

    @Test
    fun `createKtorClient sets correct headers`() = runTest {
        var capturedRequest: HttpRequestData? = null

        val mockEngine = MockEngine { request ->
            capturedRequest = request
            respond(
                content = "[]",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }

        val client = createKtorClient("user", "pass", mockEngine)
        client.get("orgs/test/repos")

        val headers = capturedRequest!!.headers
        assertEquals("application/vnd.github.v3+json", headers[ACCEPT_HEADER])
        assertTrue(headers[HttpHeaders.Authorization]?.startsWith("Basic ") == true)
    }
}
