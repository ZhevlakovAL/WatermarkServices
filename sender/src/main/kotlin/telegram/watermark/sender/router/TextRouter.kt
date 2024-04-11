package telegram.watermark.sender.router

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import telegram.watermark.sender.dto.TextDto
import telegram.watermark.sender.handler.TextHandler

@Configuration(proxyBeanMethods = false)
class TextRouter {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun route(textHandler: TextHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(
                POST("/text").and(accept(MediaType.APPLICATION_JSON)),
                textHandler::text
            )
    }
}