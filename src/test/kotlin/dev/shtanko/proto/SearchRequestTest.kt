package dev.shtanko.proto

import SearchRequestOuterClass.SearchRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SearchRequestTest {

    @Test
    fun testSearchRequest() {
        val searchRequest = SearchRequest.newBuilder()
            .setQuery("example query")
            .setPageNumber(1)
            .setResultPerPage(10)
            .build()

        assertEquals("example query", searchRequest.query)
        assertEquals(1, searchRequest.pageNumber)
        assertEquals(10, searchRequest.resultPerPage)
    }
}
