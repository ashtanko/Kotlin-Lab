/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.github.data.network.service

import com.skydoves.sandwich.ApiResponse
import dev.shtanko.github.data.model.Order
import dev.shtanko.github.data.model.SearchResponseModel
import dev.shtanko.github.data.model.Sort
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/search/repositories")
    suspend fun search(
        @Query("q") query: String,
        @Query("order") order: Order? = Order.DESC,
        @Query("per_page") perPage: Int? = 1,
        @Query("sort") sort: Sort? = null,
        @Query("limit") limit: Int? = null,
    ): ApiResponse<SearchResponseModel>
}
