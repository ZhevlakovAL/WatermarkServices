package telegram.watermark.receiver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Config {

    @Value("\${handler.server.url}")
    lateinit var storageServerUrl: String

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient =
            builder
                    .baseUrl(storageServerUrl)
                    .build()

}