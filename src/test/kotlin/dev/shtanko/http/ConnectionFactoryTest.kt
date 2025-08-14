package dev.shtanko.http

import java.io.File
import java.sql.ResultSet
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class ConnectionFactoryTest {
    private val dbFilePath = "test_factory.db"

    @AfterEach
    fun cleanupDbFile() {
        File(dbFilePath).delete()
    }

    @Test
    fun `file sqlite connection can create table`() {
        val factory = FileSqliteConnectionFactory(dbFilePath)
        factory().use { conn ->
            assertNotNull(conn)
            conn.createStatement().use { stmt ->
                stmt.execute("CREATE TABLE IF NOT EXISTS test(id INTEGER PRIMARY KEY, name TEXT)")
            }
            conn.createStatement().use { stmt ->
                stmt.execute("INSERT INTO test(name) VALUES ('Alice')")
            }
            val rs: ResultSet = conn.createStatement().executeQuery("SELECT name FROM test")
            assertTrue(rs.next())
            assertEquals("Alice", rs.getString("name"))
        }
    }

    @Test
    fun `in memory sqlite connection can insert and retrieve data`() {
        val factory = InMemorySqliteConnectionFactory()
        factory().use { conn ->
            assertNotNull(conn)
            conn.createStatement().use { stmt ->
                stmt.execute("CREATE TABLE test(id INTEGER PRIMARY KEY, value TEXT)")
            }
            conn.createStatement().use { stmt ->
                stmt.execute("INSERT INTO test(value) VALUES ('Hello')")
            }
            val rs: ResultSet = conn.createStatement().executeQuery("SELECT value FROM test")
            assertTrue(rs.next())
            assertEquals("Hello", rs.getString("value"))
        }
    }
}
