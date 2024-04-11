package telegram.watermark.handler.component.text

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import telegram.watermark.handler.client.HandlerClient
import telegram.watermark.handler.dto.TextDto

@Component
class TagHandler(private val client: HandlerClient) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun log(text: TextDto): Mono<TextDto> {
        logger.info("$text")
        return Mono.just(text)
    }

    fun startTag(text: TextDto): Mono<TextDto> {
        //logger.info("$text")
        if (text.text == "/start") {
            logger.info("start")
            GlobalScope.launch {
                client.sendText(
                    TextDto("Вам надо отправить PNG файл как файл. /reset сбросить отправленный файл",
                        text.chatId,
                        text.requestId
                    )
                )
            }
        }
        return Mono.just(text)
    }
}