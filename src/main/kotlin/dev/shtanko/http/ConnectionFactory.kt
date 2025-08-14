package dev.shtanko.http

import java.sql.Connection

fun interface ConnectionFactory {
    operator fun invoke(): Connection
}

class FileSqliteConnectionFactory(
    private val filePath: String = "test.db",
) : ConnectionFactory {
    override fun invoke(): Connection {
        val url = "jdbc:sqlite:$filePath"
        return java.sql.DriverManager.getConnection(url)
    }
}

class InMemorySqliteConnectionFactory : ConnectionFactory {
    override fun invoke(): Connection {
        val url = "jdbc:sqlite::memory:"
        return java.sql.DriverManager.getConnection(url)
    }
}
