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

import dev.shtanko.api.Repo
import dev.shtanko.api.RequestData
import dev.shtanko.api.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import retrofit2.Response

val log: Logger = LoggerFactory.getLogger("Contributors")

fun log(msg: String?) {
    log.info(msg)
}

fun logRepos(req: RequestData, response: Response<List<Repo>>) {
    val repos = response.body()
    if (!response.isSuccessful || repos == null) {
        log.error("Failed loading repos for ${req.org} with response: '${response.code()}: ${response.message()}'")
    } else {
        log.info("${req.org}: loaded ${repos.size} repos")
    }
}

fun logUsers(repo: Repo, response: Response<List<User>>) {
    val users = response.body()
    if (!response.isSuccessful || users == null) {
        log.error(
            "Failed loading contributors for ${repo.name} with response '${response.code()}: ${response.message()}'",
        )
    } else {
        log.info("${repo.name}: loaded ${users.size} contributors")
    }
}
