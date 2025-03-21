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

package dev.shtanko.github.data.repository

import com.skydoves.sandwich.ApiResponse
import dev.shtanko.github.data.cache.NetworkResponseCache
import dev.shtanko.github.data.model.Order
import dev.shtanko.github.data.model.SearchResponseModel
import dev.shtanko.github.data.model.Sort
import dev.shtanko.github.data.network.service.SearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface SearchRepository {
    fun search(
        query: String,
        order: Order?,
        perPage: Int?,
        sort: Sort?,
        limit: Int?,
    ): Flow<ApiResponse<SearchResponseModel>>
}

class SearchRepositoryImpl(
    private val searchService: SearchService,
    private val cache: NetworkResponseCache<SearchResponseModel>,
    private val dispatcher: CoroutineDispatcher,
) : SearchRepository {
    override fun search(
        query: String,
        order: Order?,
        perPage: Int?,
        sort: Sort?,
        limit: Int?,
    ): Flow<ApiResponse<SearchResponseModel>> = flow {
        val response = searchService.search(
            query = query,
            order = order,
            perPage = perPage,
            sort = sort,
            limit = limit,
        )
        emit(response)
    }.flowOn(dispatcher)
}
