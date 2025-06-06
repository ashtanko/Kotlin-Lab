package dev.shtanko.algorithms.codingbat.string2

sealed interface StarOut {
    operator fun invoke(string: String): String

    object Iterative : StarOut {
        override fun invoke(string: String): String {
            val result = StringBuilder()

            for (i in string.indices) {
                val char = string[i]

                // Skip if current character is a star
                if (char == '*') continue

                // Skip if there's a star to the left
                if (i > 0 && string[i - 1] == '*') continue

                // Skip if there's a star to the right
                if (i < string.length - 1 && string[i + 1] == '*') continue

                // If we reach here, the character should be kept
                result.append(char)
            }

            return result.toString()
        }
    }

    object Stack : StarOut {
        override fun invoke(string: String): String {
            val toRemove = BooleanArray(string.length)

            // First pass: mark characters to remove
            for (i in string.indices) {
                if (string[i] == '*') {
                    toRemove[i] = true
                    if (i > 0) toRemove[i - 1] = true
                    if (i < string.lastIndex) toRemove[i + 1] = true
                }
            }

            // Second pass: build result with a stack
            val stack = ArrayDeque<Char>()
            for (i in string.indices) {
                if (!toRemove[i]) {
                    stack.addLast(string[i])
                }
            }

            return stack.joinToString("")
        }
    }
}
