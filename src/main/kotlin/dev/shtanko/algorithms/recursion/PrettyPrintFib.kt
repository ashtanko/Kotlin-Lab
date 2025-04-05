package dev.shtanko.algorithms.recursion

internal interface PrettyPrintFib {
    operator fun invoke(n: Int, depth: Int = 0, log: StringAppender = StringAppenderImpl()): Int
}

internal class PrettyPrintFibRecursion : PrettyPrintFib {

    private fun logCall(n: Int, depth: Int, log: StringAppender) {
        log.append("  ".repeat(depth) + "Call: fib($n)\n")
    }

    private fun logReturn(n: Int, depth: Int, result: Int, log: StringAppender) {
        log.append("  ".repeat(depth) + "Return: fib($n) = $result\n")
    }

    override fun invoke(n: Int, depth: Int, log: StringAppender): Int {
        logCall(n, depth, log)

        // Base case: return 0 or 1 for n = 0 or n = 1
        if (n == 0) {
            logReturn(n, depth, 0, log)
            return 0
        }
        if (n == 1) {
            logReturn(n, depth, 1, log)
            return 1
        }

        // Recursive calls to calculate fib(n-1) and fib(n-2)
        val result = invoke(n - 1, depth + 1, log) + invoke(n - 2, depth + 1, log)

        logReturn(n, depth, result, log)
        return result
    }
}

internal class PrettyPrintFibOptimized : PrettyPrintFib {

    private val memo = mutableMapOf<Int, Int>() // Cache to store Fibonacci results

    private fun logCall(n: Int, depth: Int, log: StringAppender) {
        log.append("  ".repeat(depth) + "Call: fib($n)\n")
    }

    private fun logReturn(n: Int, depth: Int, result: Int, log: StringAppender) {
        log.append("  ".repeat(depth) + "Return: fib($n) = $result\n")
    }

    override fun invoke(n: Int, depth: Int, log: StringAppender): Int {
        logCall(n, depth, log)

        // Check the memoization cache before proceeding with recursion
        memo[n]?.let {
            logReturn(n, depth, it, log)
            return it // Return cached value
        }

        // Base case: return 0 or 1 for n = 0 or n = 1
        if (n == 0) {
            logReturn(n, depth, 0, log)
            return 0
        }
        if (n == 1) {
            logReturn(n, depth, 1, log)
            return 1
        }

        // Recursive calls to calculate fib(n-1) and fib(n-2), and store the result in the cache
        val result = invoke(n - 1, depth + 1, log) + invoke(n - 2, depth + 1, log)

        memo[n] = result // Cache the result
        logReturn(n, depth, result, log)
        return result
    }
}

internal class PrettyPrintFibTailRec : PrettyPrintFib {

    // Tail-recursive function for Fibonacci
    private tailrec fun fibTailRec(n: Int, depth: Int, log: StringAppender, prev: Int = 0, current: Int = 1): Int {
        // Log the current call with depth
        log.append("  ".repeat(depth) + "Call: fib($n, prev=$prev, current=$current)\n")

        // Base case: return the current value when n reaches 0
        if (n == 0) {
            log.append("  ".repeat(depth) + "Return: fib($n) = $prev\n")
            return prev
        }

        // Recursive case: move to the next Fibonacci number
        return fibTailRec(n - 1, depth + 1, log, current, prev + current)
    }

    override fun invoke(n: Int, depth: Int, log: StringAppender): Int {
        return fibTailRec(n, depth, log)
    }
}
