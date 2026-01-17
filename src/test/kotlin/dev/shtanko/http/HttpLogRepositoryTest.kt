package dev.shtanko.http

import java.sql.Connection
import java.sql.DriverManager
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertNull

class HttpLogRepositoryTest {

    private lateinit var connection: Connection
    private lateinit var repository: HttpLogRepository

    @BeforeEach
    fun setUp() {
        // In-memory SQLite DB
        connection = DriverManager.getConnection("jdbc:sqlite::memory:")
        repository = HttpLogRepository(connection)
        repository.init()
    }

    @Test
    fun `insert and retrieve single log entry`() {
        val log = HttpLogEntry(
            id = null,
            method = "GET",
            url = "https://example.com",
            requestHeaders = "User-Agent: Test",
            responseCode = 200,
            responseHeaders = "Content-Type: application/json",
            durationMs = 123.45,
            timestamp = Instant.now(),
        )

        repository.insert(log)

        val logs = repository.getAll()
        assertEquals(1, logs.size)

        val retrieved = logs.first()
        assertEquals("GET", retrieved.method)
        assertEquals("https://example.com", retrieved.url)
        assertEquals(200, retrieved.responseCode)
        assertTrue(retrieved.requestHeaders.contains("User-Agent"))
        assertTrue(retrieved.responseHeaders.contains("Content-Type"))
    }

    @Test
    fun `insert and retrieve multiple log entries`() {
        val now = Instant.now()
        val entries = listOf(
            HttpLogEntry(null, "GET", "https://example.com/1", "UA: Test1", 200, "CT: json", 100.0, now),
            HttpLogEntry(null, "POST", "https://example.com/2", "UA: Test2", 201, "CT: json", 150.0, now),
            HttpLogEntry(null, "DELETE", "https://example.com/3", "UA: Test3", 204, "CT: json", 50.0, now),
        )

        entries.forEach { repository.insert(it) }

        val logs = repository.getAll()
        assertEquals(3, logs.size)

        // Verify order is insertion order (SQLite default without ORDER BY is by rowid)
        assertEquals("GET", logs[0].method)
        assertEquals("POST", logs[1].method)
        assertEquals("DELETE", logs[2].method)

        // Verify URLs are preserved
        assertEquals("https://example.com/1", logs[0].url)
        assertEquals("https://example.com/2", logs[1].url)
        assertEquals("https://example.com/3", logs[2].url)
    }

    @Test
    fun `insert and getAll returns inserted entry`() {
        val entry = sampleLogEntry()
        repository.insert(entry)

        val logs = repository.getAll()
        assertEquals(1, logs.size)
        assertEquals(entry.method, logs[0].method)
        assertEquals(entry.url, logs[0].url)
    }

    @Test
    fun `getById returns correct entry`() {
        val entry = sampleLogEntry()
        repository.insert(entry)

        val logs = repository.getAll()
        val id = logs.first().id
        assertNotNull(id)

        val fetched = repository.getById(id)
        assertNotNull(fetched)
        assertEquals(entry.url, fetched.url)
    }

    @Test
    fun `getById returns null for non-existing id`() {
        assertNull(repository.getById(999))
    }

    @Test
    fun `getByUrlPattern returns matching entries`() {
        repository.insert(sampleLogEntry(url = "https://example.com/abc"))
        repository.insert(sampleLogEntry(url = "https://test.com/xyz"))

        val result = repository.getByUrlPattern("example.com")
        assertEquals(1, result.size)
        assertTrue(result.first().url.contains("example.com"))
    }

    @Test
    fun `deleteOlderThan removes only old entries`() {
        val oldEntry = sampleLogEntry(timestamp = Instant.parse("2020-01-01T00:00:00Z"))
        val newEntry = sampleLogEntry(timestamp = Instant.now())

        repository.insert(oldEntry)
        repository.insert(newEntry)

        val deletedCount = repository.deleteOlderThan(Instant.now().minusSeconds(60))
        assertEquals(1, deletedCount)
        assertEquals(1, repository.count())
    }

    @Test
    fun `count returns number of rows`() {
        assertEquals(0, repository.count())
        repository.insert(sampleLogEntry())
        repository.insert(sampleLogEntry())
        assertEquals(2, repository.count())
    }

    @Test
    fun `stress test with many entries`() {
        val totalEntries = 10_000
        val startTime = System.currentTimeMillis()

        repeat(totalEntries) { i ->
            repository.insert(
                sampleLogEntry(
                    method = if (i % 2 == 0) "GET" else "POST",
                    url = "https://example.com/api/$i",
                    responseCode = 200 + (i % 5),
                    timestamp = Instant.now().minusSeconds((i % 100).toLong()),
                ),
            )
        }

        val insertDuration = System.currentTimeMillis() - startTime
        println("Inserted $totalEntries entries in ${insertDuration}ms")

        // Verify count is correct
        assertEquals(totalEntries.toLong(), repository.count())

        // Fetch all and verify first few
        val logs = repository.getAll()
        assertEquals(totalEntries, logs.size)
        assertTrue(logs.all { it.url.startsWith("https://example.com/api/") })

        val totalDuration = System.currentTimeMillis() - startTime
        println("Total stress test duration: ${totalDuration}ms")
    }

    private fun sampleLogEntry(
        method: String = "GET",
        url: String = "https://example.com",
        responseCode: Int = 200,
        timestamp: Instant = Instant.now(),
    ) = HttpLogEntry(
        id = null,
        method = method,
        url = url,
        requestHeaders = "Header1: Value1",
        responseCode = responseCode,
        responseHeaders = "Header2: Value2",
        durationMs = 123.45,
        timestamp = timestamp,
    )
}
