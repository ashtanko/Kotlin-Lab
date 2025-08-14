package dev.shtanko.api.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.http.HttpStatusCode
import io.ktor.util.AttributeKey

@Suppress("TooGenericExceptionCaught")
class RetryInterceptor private constructor(
    private val maxRetries: Int,
    private val retryOn: Set<HttpStatusCode>,
    private val baseDelayMs: Long,
) {

    class Config {
        var maxRetries: Int = 3
        var retryOn: Set<HttpStatusCode> = setOf(
            HttpStatusCode.InternalServerError,
            HttpStatusCode.BadGateway,
            HttpStatusCode.ServiceUnavailable,
            HttpStatusCode.GatewayTimeout,
        )
        var baseDelayMs: Long = 1000
    }

    companion object : HttpClientPlugin<Config, RetryInterceptor> {
        override val key = AttributeKey<RetryInterceptor>("RetryInterceptor")

        override fun prepare(block: Config.() -> Unit): RetryInterceptor {
            val config = Config().apply(block)
            return RetryInterceptor(config.maxRetries, config.retryOn, config.baseDelayMs)
        }

        override fun install(plugin: RetryInterceptor, scope: HttpClient) {
            scope.plugin(HttpSend).intercept { request ->
                var lastResponse: HttpClientCall? = null

        override fun install(plugin: RetryInterceptor, scope: HttpClient) {
            scope.plugin(HttpSend).intercept { request ->
                var lastResponse: HttpClientCall? = null

                repeat(plugin.maxRetries + 1) { attempt ->
                    try {
                        val call = execute(request)
                        val status = call.response.status

                        if (status in plugin.retryOn && attempt < plugin.maxRetries) {
                            // Free resources before retrying
                            try { call.response.close() } catch (_: Throwable) {}
                            val delayMs = plugin.baseDelayMs * (1L shl attempt) // Exponential backoff
                            println("🔄 Retry attempt ${attempt + 1} after ${delayMs}ms for $status")
                            kotlinx.coroutines.delay(delayMs)
                            lastResponse = call
                        } else {
                            return@intercept call
                        }
                    } catch (e: Exception) {
                        if (e is kotlinx.coroutines.CancellationException) throw e
                        if (attempt == plugin.maxRetries) throw e
                        val delayMs = plugin.baseDelayMs * (1L shl attempt)
                        println("🔄 Retry attempt ${attempt + 1} after ${delayMs}ms due to exception: ${e.message}")
                        kotlinx.coroutines.delay(delayMs)
                    }
                }

                // Fallback; normally we return inside the loop
                lastResponse ?: execute(request)
            }
        }

                lastResponse ?: execute(request)
            }
        }
    }
}
