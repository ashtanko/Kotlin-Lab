package dev.shtanko.http

import java.sql.Connection
import java.sql.ResultSet
import java.time.Instant

class HttpLogRepository(private val connection: Connection) {

    companion object {
        private const val CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS http_logs (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                method TEXT NOT NULL,
                url TEXT NOT NULL,
                request_headers TEXT NOT NULL,
                response_code INTEGER NOT NULL,
                response_headers TEXT NOT NULL,
                duration_ms REAL NOT NULL,
                timestamp TEXT NOT NULL
            )
        """

        private const val INSERT_SQL = """
            INSERT INTO http_logs (
                method, url, request_headers, response_code,
                response_headers, duration_ms, timestamp
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
        """

        private const val SELECT_ALL_SQL = "SELECT * FROM http_logs ORDER BY timestamp DESC"
    }

    fun init() {
        connection.createStatement().use { stmt ->
            stmt.execute(CREATE_TABLE_SQL)
        }
    }

    fun insert(log: HttpLogEntry) {
        connection.prepareStatement(INSERT_SQL).use { pstmt ->
            pstmt.apply {
                setString(1, log.method)
                setString(2, log.url)
                setString(3, log.requestHeaders)
                setInt(4, log.responseCode)
                setString(5, log.responseHeaders)
                setDouble(6, log.durationMs)
                setString(7, log.timestamp.toString())
            }.executeUpdate()
        }
    }

    fun getAll(): List<HttpLogEntry> {
        return connection.createStatement().use { stmt ->
            stmt.executeQuery(SELECT_ALL_SQL).use { rs ->
                generateSequence { if (rs.next()) rs.toHttpLogEntry() else null }
                    .toList()
            }
        }
    }

    fun getById(id: Long): HttpLogEntry? {
        return connection.prepareStatement("SELECT * FROM http_logs WHERE id = ?").use { pstmt ->
            pstmt.setLong(1, id)
            pstmt.executeQuery().use { rs ->
                if (rs.next()) rs.toHttpLogEntry() else null
            }
        }
    }

    fun getByUrlPattern(urlPattern: String): List<HttpLogEntry> {
        return connection.prepareStatement("SELECT * FROM http_logs WHERE url LIKE ? ORDER BY timestamp DESC")
            .use { pstmt ->
                pstmt.setString(1, "%$urlPattern%")
                pstmt.executeQuery().use { rs ->
                    generateSequence { if (rs.next()) rs.toHttpLogEntry() else null }
                        .toList()
                }
            }
    }

    fun deleteOlderThan(timestamp: Instant): Int {
        return connection.prepareStatement("DELETE FROM http_logs WHERE timestamp < ?").use { pstmt ->
            pstmt.setString(1, timestamp.toString())
            pstmt.executeUpdate()
        }
    }

    fun count(): Long {
        return connection.createStatement().use { stmt ->
            stmt.executeQuery("SELECT COUNT(*) as count FROM http_logs").use { rs ->
                if (rs.next()) rs.getLong("count") else 0
            }
        }
    }

    private fun ResultSet.toHttpLogEntry(): HttpLogEntry {
        return HttpLogEntry(
            id = getLong("id"),
            method = getString("method"),
            url = getString("url"),
            requestHeaders = getString("request_headers"),
            responseCode = getInt("response_code"),
            responseHeaders = getString("response_headers"),
            durationMs = getDouble("duration_ms"),
            timestamp = Instant.parse(getString("timestamp")),
        )
    }
}
