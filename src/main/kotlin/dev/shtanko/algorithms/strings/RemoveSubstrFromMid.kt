package dev.shtanko.algorithms.strings

/**
 * Problem: Remove a given substring from the middle of a string.
 */
internal fun interface RemoveMiddleSubstring {
    operator fun invoke(input: String, toRemove: String): String
}

class RemoveMiddleSubstringStd : RemoveMiddleSubstring {
    override fun invoke(input: String, toRemove: String): String = input.replaceFirst(toRemove, "")
}

class RemoveMiddleSubstringLoop : RemoveMiddleSubstring {

    /**
     * Removes all occurrences of a specified substring from the input string.
     *
     * The function iterates through the input string, checking for occurrences of `toRemove`.
     * If a match is found, it skips over `toRemove.length` characters; otherwise, it appends
     * the current character to the result.
     *
     * Steps:
     * 1. Initialize an empty `StringBuilder` to store the modified string.
     * 2. Traverse the input string using a while loop until `i <= input.length - toRemove.length`.
     * 3. For each position `i`, check if `toRemove` matches the substring starting at `i`.
     * 4. If a match is found, skip `toRemove.length` characters.
     * 5. If no match is found, append `input[i]` to the result and move to the next character.
     * 6. After exiting the main loop, append any remaining characters.
     * 7. Return the final modified string.
     *
     * Example Execution:
     * Input: "HelloWorldHello", "World"
     *
     * Iteration Process:
     * ```
     * Input: "HelloWorldHello"
     * Remove: "World"
     *
     * Step 1: "H" ✅ → "H"
     * Step 2: "He" ✅ → "He"
     * Step 3: "Hel" ✅ → "Hel"
     * Step 4: "Hell" ✅ → "Hell"
     * Step 5: "Hello" ✅ → "Hello"
     * Step 6: "HelloW" ❌ (Match starts checking)
     * Step 7: "HelloWorld" ✅ (Match found, skipping "World")
     * Step 8: "HelloHello" (Appending remaining "Hello")
     * ```
     *
     * Table Representation:
     *
     * | `i`  | Current Window | Match? | Action |
     * |------|---------------|--------|--------|
     * | 0    | "Hello"       | ❌     | Append 'H' |
     * | 1    | "elloW"       | ❌     | Append 'e' |
     * | 2    | "lloWo"       | ❌     | Append 'l' |
     * | 3    | "loWor"       | ❌     | Append 'l' |
     * | 4    | "oWorl"       | ❌     | Append 'o' |
     * | 5    | "World"       | ✅     | Skip "World", move `i` forward by `5` |
     * | 10   | "Hello"       | -      | Append remaining "Hello" |
     *
     * Output: "HelloHello"
     *
     * Time Complexity:
     * - **Best case (`toRemove` not found at all)**: O(n)
     * - **Worst case (`toRemove` occurs multiple times)**: O(n * m), where `m` is `toRemove.length`
     *
     * @param input The original string from which the substring should be removed.
     * @param toRemove The substring to be removed.
     * @return A new string with all occurrences of `toRemove` removed.
     */
    override fun invoke(input: String, toRemove: String): String {
        if (toRemove.isEmpty()) return input

        val result = StringBuilder()
        var i = 0
        var removed = false

        while (i < input.length) {
            if (!removed && i + toRemove.length <= input.length && input.regionMatches(
                    i,
                    toRemove,
                    0,
                    toRemove.length,
                )
            ) {
                i += toRemove.length
                removed = true
            } else {
                result.append(input[i])
                i++
            }
        }

        return result.toString()
    }
}
