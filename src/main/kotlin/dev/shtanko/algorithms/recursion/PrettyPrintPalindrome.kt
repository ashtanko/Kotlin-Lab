package dev.shtanko.algorithms.recursion

internal interface PrettyPrintPalindrome {
    operator fun invoke(
        str: String,
        left: Int = 0,
        right: Int = str.length - 1,
        depth: Int = 0,
        log: StringAppender = StringAppenderImpl(),
    ): Boolean
}

internal class PrettyPrintPalindromeRecursion : PrettyPrintPalindrome {

    private fun logCall(str: String, left: Int, right: Int, depth: Int, log: StringAppender) {
        log.append("  ".repeat(depth) + "Call: isPalindrome(\"$str\", left=$left, right=$right)\n")
    }

    private fun logReturn(left: Int, right: Int, result: Boolean, depth: Int, log: StringAppender) {
        log.append("  ".repeat(depth) + "Return: isPalindrome(left=$left, right=$right) = $result\n")
    }

    override fun invoke(str: String, left: Int, right: Int, depth: Int, log: StringAppender): Boolean {
        logCall(str, left, right, depth, log)

        // Base case: if pointers have met or crossed, it's a palindrome
        if (left >= right) {
            logReturn(left, right, true, depth, log)
            return true
        }

        // If characters at left and right don't match, return false
        if (str[left] != str[right]) {
            logReturn(left, right, false, depth, log)
            return false
        }

        // Recursive call moving inward
        val result = invoke(str, left + 1, right - 1, depth + 1, log)

        logReturn(left, right, result, depth, log)
        return result
    }
}

fun main() {
    val palindromeChecker = PrettyPrintPalindromeRecursion()
    val log = StringAppenderImpl()

    val input = "racecar"
    val result = palindromeChecker.invoke(input, 0, input.length - 1, 0, log)

    println("Is \"$input\" a palindrome? $result")
    println("Log:\n${log.get()}")
}
