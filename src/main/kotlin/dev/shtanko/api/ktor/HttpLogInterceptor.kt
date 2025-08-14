package dev.shtanko.api.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.AttributeKey

private val RequestStartTimeKey = AttributeKey<Long>("RequestStartTime")

class HttpLogInterceptor private constructor(
    private val logRequest: Boolean,
    private val logResponse: Boolean,
    private val logHeaders: Boolean,
) {

    class Config {
        var logRequest: Boolean = true
        var logResponse: Boolean = true
        var logHeaders: Boolean = false
    }

    companion object : HttpClientPlugin<Config, HttpLogInterceptor> {
        override val key = AttributeKey<HttpLogInterceptor>("HttpLogInterceptor")

        override fun prepare(block: Config.() -> Unit): HttpLogInterceptor {
            val config = Config().apply(block)
            return HttpLogInterceptor(config.logRequest, config.logResponse, config.logHeaders)
        }

        override fun install(plugin: HttpLogInterceptor, scope: HttpClient) {
            // Request interceptor
            if (plugin.logRequest) {
                scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                    val startTime = System.currentTimeMillis()
                    context.attributes.put(RequestStartTimeKey, startTime)

                    println("🚀 REQUEST: ${context.method.value} ${context.url}")
                    if (plugin.logHeaders) {
                        println("   Headers: ${context.headers.entries()}")
                    }
                }
            }

            // Response interceptor
            if (plugin.logResponse) {
                scope.responsePipeline.intercept(HttpResponsePipeline.Receive) {
                    val startTime = context.request.attributes.getOrNull(RequestStartTimeKey)
                    val duration = startTime?.let { System.currentTimeMillis() - it } ?: 0

                    println("📥 RESPONSE: ${context.response.status} (${duration}ms)")
                    if (plugin.logHeaders) {
                        println("   Headers: ${context.response.headers.entries()}")
                    }
                }
            }
        }
    }
}
