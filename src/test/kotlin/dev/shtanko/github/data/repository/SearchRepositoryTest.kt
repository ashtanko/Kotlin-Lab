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
import dev.shtanko.github.data.model.GitHubItemModel
import dev.shtanko.github.data.model.SearchResponseModel
import dev.shtanko.github.data.network.service.SearchService
import java.io.IOException
import kotlin.test.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertInstanceOf
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchRepositoryTest {

    private lateinit var searchRepository: SearchRepository
    private val mockSearchService: SearchService = mock()
    private val mockCache: NetworkResponseCache<SearchResponseModel> = mock()
    private val testDispatcher = UnconfinedTestDispatcher()
    val testScope = TestScope(testDispatcher)

    @BeforeAll
    @Throws(IOException::class)
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        searchRepository = SearchRepositoryImpl(
            searchService = mockSearchService,
            cache = mockCache,
            dispatcher = testDispatcher,
        )
    }

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @Disabled("rework needed")
    fun `search repositories - should return an error`() = runTest {
        Mockito.`when`(
            mockSearchService.search(
                query = "test",
                order = null,
                perPage = null,
                sort = null,
                limit = null,
            ),
        ).thenReturn(ApiResponse.exception(Error()))
        val result = searchRepository.search(
            query = "test",
            order = null,
            perPage = null,
            sort = null,
            limit = null,
        )
        assertInstanceOf<ApiResponse.Failure<*>>(result.firstOrNull())
    }

    @Test
    fun `search repositories - should throw an error`() = runTest {
        Mockito.`when`(
            mockSearchService.search(
                query = "test",
                order = null,
                perPage = null,
                sort = null,
                limit = null,
            ),
        ).thenThrow(Error())
        val result = searchRepository.search(
            query = "test",
            order = null,
            perPage = null,
            sort = null,
            limit = null,
        )
        assertThrows<Error> {
            result.firstOrNull()
        }
    }

    @Test
    fun `search repositories - should return an empty result`() = runTest {
        Mockito.`when`(
            mockSearchService.search(
                query = "test",
                order = null,
                perPage = null,
                sort = null,
                limit = null,
            ),
        ).thenReturn(ApiResponse.of { SearchResponseModel(0, false, emptyList()) })
        val result = searchRepository.search(
            query = "test",
            order = null,
            perPage = null,
            sort = null,
            limit = null,
        )
        assertInstanceOf<ApiResponse.Success<*>>(result.firstOrNull())
        if (result.firstOrNull() is ApiResponse.Success) {
            val data = (result.firstOrNull() as ApiResponse.Success).data
            assertTrue(data.items?.isEmpty() == true)
        }
    }

    @Test
    fun `search repositories - should return data`() = runTest {
        val mockItem = GitHubItemModel()
        Mockito.`when`(
            mockSearchService.search(
                query = "test",
                order = null,
                perPage = null,
                sort = null,
                limit = null,
            ),
        ).thenReturn(ApiResponse.of { SearchResponseModel(0, false, listOf(mockItem)) })
        val result = searchRepository.search(
            query = "test",
            order = null,
            perPage = null,
            sort = null,
            limit = null,
        )
        assertInstanceOf<ApiResponse.Success<*>>(result.firstOrNull())
        if (result.firstOrNull() is ApiResponse.Success) {
            val data = (result.firstOrNull() as ApiResponse.Success).data
            assertTrue(data.items?.isNotEmpty() == true)
        }
    }
}
