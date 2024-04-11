package telegram.watermark.sender.component

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.meta.generics.TelegramClient
import reactor.core.publisher.Mono
import telegram.watermark.sender.dto.TextDto

@Component
class TextSender(private val telegramClient: TelegramClient) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun log(text: TextDto): Mono<TextDto> {
        logger.info("$text")
        return Mono.just(text)
    }

    fun send(text: TextDto): Mono<TextDto> {
        val message: SendMessage = SendMessage
            .builder()
            .chatId(text.chatId)
            .text(text.text)
            .build()
        try {
            telegramClient.execute(message)
        } catch (e: TelegramApiException) {
            logger.error(e.message, e)
        }
        return Mono.just(text)
    }
}