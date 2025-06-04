package dev.shtanko.algorithms.codingbat.string2

fun interface GetSandwich {
    operator fun invoke(str: String): String
}

class GetSandwichTwoSlidingWindow : GetSandwich {
    override fun invoke(str: String): String {
        if (str.length < 10) {
            return ""
        } else {
            var start = 0
            var startIndex = -1
            var endIndex = -1
            var end = str.length
            while (start <= end) {
                if (str.substring(start, start + 5) == "bread") {
                    startIndex = start + 5
                } else {
                    start++
                }
                if (str.substring(end - 5, end) == "bread") {
                    endIndex = end - 5
                } else {
                    end--
                }
                if (startIndex != -1 && endIndex != -1) {
                    return str.substring(startIndex, endIndex)
                }
            }
        }
        return ""
    }
}

class GetSandwichTwoPointers : GetSandwich {
    override fun invoke(str: String): String {
        val bread = "bread"
        val start = str.indexOf(bread)
        val end = str.lastIndexOf(bread)
        if (start == -1 || end == -1 || start == end) {
            return ""
        }
        return str.substring(start + bread.length, end)
    }
}
