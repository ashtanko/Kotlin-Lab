package dev.shtanko.api.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.util.AttributeKey

class ApiKeyInterceptor private constructor(
    private val apiKey: String,
    private val headerName: String,
) {

    class Config {
        var apiKey: String = ""
        var headerName: String = "X-API-Key"
    }

    companion object : HttpClientPlugin<Config, ApiKeyInterceptor> {
        override val key = AttributeKey<ApiKeyInterceptor>("ApiKeyInterceptor")

        override fun prepare(block: Config.() -> Unit): ApiKeyInterceptor {
            val config = Config().apply(block)
            require(config.apiKey.isNotEmpty()) { "API key cannot be empty" }
            return ApiKeyInterceptor(config.apiKey, config.headerName)
        }

        override fun install(plugin: ApiKeyInterceptor, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.State) {
                context.headers.append(plugin.headerName, plugin.apiKey)
                println("🔑 Added API key header: ${plugin.headerName}")
            }
        }
    }
}
