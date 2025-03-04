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

package dev.shtanko.github

import com.skydoves.sandwich.ApiResponse
import dev.shtanko.github.data.cache.InMemoryNetworkResponseCache
import dev.shtanko.github.data.model.SearchResponseModel
import dev.shtanko.github.data.network.di.provideSearchService
import dev.shtanko.github.data.repository.SearchRepository
import dev.shtanko.github.data.repository.SearchRepositoryImpl
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

object GithubClient {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val searchService = provideSearchService().value
        val cache = InMemoryNetworkResponseCache<SearchResponseModel>(expiration = 10.seconds)
        val searchRepository: SearchRepository = SearchRepositoryImpl(searchService, cache, dispatcher = Dispatchers.IO)
        searchRepository.search(
            "android", order = null,
            perPage = null,
            sort = null,
            limit = null,
        ).collect {
            if (it is ApiResponse.Success<SearchResponseModel>) {
                val items = it.data.items
                items?.forEach { item ->
                    println(item.name)
                }
            }
        }
    }
}
