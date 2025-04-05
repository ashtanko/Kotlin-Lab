package dev.shtanko.utils

fun String.isContentEqualIgnoringWhitespace(expected: String): Boolean {
    val normalizedThis = this.normalizeString()
    val normalizedExpected = expected.normalizeString()
    return normalizedThis == normalizedExpected
}

// Helper function to normalize the string by removing spaces, newlines, and trimming it
private fun String.normalizeString(): String {
    return this.replace(Regex("\\s+"), "").trim()
}
