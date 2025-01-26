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

package dev.shtanko.api.tasks

import dev.shtanko.api.GitHubService
import dev.shtanko.api.RequestData
import dev.shtanko.api.User
import dev.shtanko.api.contributors.log
import dev.shtanko.api.contributors.logRepos
import dev.shtanko.api.contributors.logUsers
import java.util.Collections
import java.util.concurrent.CountDownLatch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun loadContributorsCallbacks(service: GitHubService, req: RequestData, updateResults: (List<User>) -> Unit) {
    service.getOrgReposCall(req.org).onResponse { responseRepos ->
        logRepos(req, responseRepos)
        val repos = responseRepos.bodyList()
        val allUsers = Collections.synchronizedList(mutableListOf<User>())
        val countDownLatch = CountDownLatch(repos.size)
        for (repo in repos) {
            service.getRepoContributorsCall(req.org, repo.name).onResponse { responseUsers ->
                logUsers(repo, responseUsers)
                val users = responseUsers.bodyList()
                allUsers += users
                countDownLatch.countDown()
            }
        }
        countDownLatch.await()
        updateResults(allUsers.aggregate())
    }
}

inline fun <T> Call<T>.onResponse(crossinline callback: (Response<T>) -> Unit) {
    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback(response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                log.error("Call failed", t)
            }
        },
    )
}
