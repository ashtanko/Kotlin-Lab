package dev.shtanko.api.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.AttributeKey

class RequestResponseProcessorInterceptor private constructor(
    private val requestProcessor: (HttpRequestBuilder) -> Unit,
    private val responseProcessor: suspend (HttpResponse) -> Unit
) {

    class Config {
        var requestProcessor: (HttpRequestBuilder) -> Unit = { }
        var responseProcessor: suspend (HttpResponse) -> Unit = { }
    }

    companion object : HttpClientPlugin<Config, RequestResponseProcessorInterceptor> {
        override val key = AttributeKey<RequestResponseProcessorInterceptor>("RequestResponseProcessorInterceptor")

        override fun prepare(block: Config.() -> Unit): RequestResponseProcessorInterceptor {
            val config = Config().apply(block)
            return RequestResponseProcessorInterceptor(config.requestProcessor, config.responseProcessor)
        }

        override fun install(plugin: RequestResponseProcessorInterceptor, scope: HttpClient) {
            // Process requests
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                plugin.requestProcessor(context)
            }

            // Process responses - use Transform stage and pass through the content
            scope.responsePipeline.intercept(HttpResponsePipeline.Transform) {
                plugin.responseProcessor(context.response)
                // Important: Continue with the original subject
                proceedWith(subject)
            }
        }
    }
}
