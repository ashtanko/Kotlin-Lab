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

                repeat(plugin.maxRetries + 1) { attempt ->
                    try {
                        val response = execute(request)

                        if (attempt == 0 || response.response.status !in plugin.retryOn) {
                            return@intercept response
                        }

                        lastResponse = response
                        val delay = plugin.baseDelayMs * (1 shl attempt) // Exponential backoff
                        println("🔄 Retry attempt $attempt after ${delay}ms for ${response.response.status}")
                        kotlinx.coroutines.delay(delay)

                    } catch (e: Exception) {
                        if (attempt == plugin.maxRetries) throw e
                        val delay = plugin.baseDelayMs * (1 shl attempt)
                        println("🔄 Retry attempt $attempt after ${delay}ms due to exception: ${e.message}")
                        kotlinx.coroutines.delay(delay)
                    }
                }

                lastResponse ?: execute(request)
            }
        }
    }
}
