package dev.shtanko.api

import dev.shtanko.api.model.Repo
import dev.shtanko.api.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import java.util.Base64

fun createKtorClient(
    username: String,
    password: String,
    engine: HttpClientEngine = CIO.create(),
): HttpClient {
    val authToken = String.format(
        AUTH_BASIC,
        Base64.getEncoder().encodeToString("$username:$password".toByteArray(Charsets.UTF_8)),
    )

    return HttpClient(engine) {
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            level = LogLevel.INFO
        }
        defaultRequest {
            header(ACCEPT_HEADER, ACCEPT_HEADER_VALUE)
            header(HttpHeaders.Authorization, authToken)
            url(BASE_URL)
        }
    }
}

class KtorGitHubApi(private val client: HttpClient) {

    suspend fun getOrgRepos(org: String): List<Repo> {
        return client.get("orgs/$org/repos") {
            parameter("per_page", 100)
        }.body()
    }

    suspend fun getRepoContributors(owner: String, repo: String): List<User> {
        return client.get("repos/$owner/$repo/contributors") {
            parameter("per_page", 100)
        }.body()
    }
}
