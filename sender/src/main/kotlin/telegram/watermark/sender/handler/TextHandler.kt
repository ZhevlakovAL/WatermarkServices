package telegram.watermark.sender.handler

import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import telegram.watermark.sender.dto.TextDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse.ok
import telegram.watermark.sender.component.TextSender

@Component
class TextHandler(private val tagHandler: TextSender) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun text(request: ServerRequest?): Mono<ServerResponse> {
        return request!!
            .bodyToMono(TextDto::class.java)
            .single()
            .doOnEach {
                if (it.hasError()) {
                    logger.error(it.throwable?.message, it.throwable!!)
                }
            }
            .flatMap { tagHandler.log(it) }
            .flatMap { tagHandler.send(it) }
            .then((ok().build()))
    }
}