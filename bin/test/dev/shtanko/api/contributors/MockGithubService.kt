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

import dev.shtanko.api.GitHubService
import dev.shtanko.api.Repo
import dev.shtanko.api.User
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls

object MockGithubService : GitHubService {
    override fun getOrgReposCall(org: String): Call<List<Repo>> {
        return Calls.response(repos)
    }

    override fun getRepoContributorsCall(owner: String, repo: String): Call<List<User>> {
        return Calls.response(reposMap.getValue(repo).users)
    }

    override suspend fun getOrgRepos(org: String): Response<List<Repo>> {
        delay(REPOS_DELAY)
        return Response.success(repos)
    }

    override suspend fun getRepoContributors(owner: String, repo: String): Response<List<User>> {
        val testRepo = reposMap.getValue(repo)
        delay(testRepo.delay)
        return Response.success(testRepo.users)
    }
}
