package dev.shtanko.http

import okhttp3.Interceptor
import okhttp3.Response

private const val NANOS_PER_MILLI = 1_000_000.0

class CustomInterceptor private constructor(
    private val logger: HttpLogger = DefaultHttpLogger(),
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        logger.logRequest(request)

        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs = (System.nanoTime() - startNs) / NANOS_PER_MILLI

        logger.logResponse(response, tookMs)
        return response
    }

    companion object {
        fun create(logger: HttpLogger = DefaultHttpLogger()): CustomInterceptor =
            CustomInterceptor(logger)
    }
}
