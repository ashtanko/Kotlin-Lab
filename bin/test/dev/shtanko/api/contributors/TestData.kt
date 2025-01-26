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

val testRequestData = RequestData("username", "password", "org")

data class TestRepo(val name: String, val delay: Long, val users: List<User>)

data class TestResults(val timeFromStart: Long, val users: List<User>)

const val REPOS_DELAY = 1000L

val testRepos = listOf(
    TestRepo(
        "repo-1",
        1000,
        listOf(
            User("user-1", 10),
            User("user-2", 20),
        ),
    ),
    TestRepo(
        "repo-2",
        1200,
        listOf(
            User("user-2", 30),
            User("user-1", 40),
        ),
    ),
    TestRepo(
        "repo-3",
        800,
        listOf(
            User("user-2", 50),
            User("user-3", 60),
        ),
    ),
)

val repos = testRepos.mapIndexed { index, testRepo -> Repo(index.toLong(), testRepo.name) }

val reposMap = testRepos.associateBy { it.name }

val expectedResults = TestResults(
    4000, // 1000 + (1000 + 1200 + 800)
    listOf(
        User("user-2", 100),
        User("user-3", 60),
        User("user-1", 50),
    ),
)

val expectedConcurrentResults = TestResults(
    2200, // 1000 + max(1000, 1200, 800)
    expectedResults.users,
)

val progressResults = listOf(
    TestResults(
        2000, // 1000 + 1000
        listOf(User(login = "user-2", contributions = 20), User(login = "user-1", contributions = 10)),
    ),
    TestResults(
        3200, // 2000 + 1200
        listOf(User(login = "user-2", contributions = 50), User(login = "user-1", contributions = 50)),
    ),
    expectedResults,
)

val concurrentProgressResults = listOf(
    TestResults(
        1800, // 1000 + 800
        listOf(
            User(login = "user-3", contributions = 60),
            User(login = "user-2", contributions = 50),
        ),
    ),
    TestResults(
        2000, // 1000 + max(800, 1000)
        listOf(
            User(login = "user-2", contributions = 70),
            User(login = "user-3", contributions = 60),
            User(login = "user-1", contributions = 10),
        ),
    ),
    expectedConcurrentResults,
)
