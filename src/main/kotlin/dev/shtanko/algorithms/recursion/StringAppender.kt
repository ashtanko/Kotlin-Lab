package dev.shtanko.algorithms.recursion

/**
 * An interface defining a contract for appending strings and retrieving the final result.
 */
internal interface StringAppender {

    /**
     * Appends the given [str] to the internal string representation.
     *
     * @param str The string to append.
     */
    fun append(str: String)

    /**
     * Retrieves the current concatenated string.
     *
     * @return The complete string built so far.
     */
    fun get(): String

    /**
     * Clears the internal string representation, resetting it to an empty state.
     */
    fun clear()
}

/**
 * A concrete implementation of [StringAppender] using [StringBuilder] for efficient string operations.
 */
internal class StringAppenderImpl : StringAppender {
    private val sb = StringBuilder()

    /**
     * Appends the given [str] to the internal [StringBuilder].
     *
     * @param str The string to append.
     */
    override fun append(str: String) {
        sb.append(str)
    }

    /**
     * Retrieves the concatenated string from the internal [StringBuilder].
     *
     * @return The complete string built so far.
     */
    override fun get(): String {
        return sb.toString()
    }

    /**
     * Clears the internal [StringBuilder], resetting it to an empty state.
     */
    override fun clear() {
        sb.setLength(0)
    }
}
