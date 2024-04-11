package telegram.watermark.handler.handler

import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import telegram.watermark.handler.dto.TextDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse.ok
import telegram.watermark.handler.component.text.TagHandler

@Component
class TextHandler(private val tagHandler: TagHandler) {
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
            .flatMap { tagHandler.startTag(it) }
            .then((ok().build()))
    }
}