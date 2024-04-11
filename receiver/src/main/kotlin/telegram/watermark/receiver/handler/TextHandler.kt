package telegram.watermark.receiver.handler

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.api.objects.Update
import telegram.watermark.handler.dto.TextDto
import telegram.watermark.receiver.client.HandlerClient
import java.util.UUID

class TextHandler(private val client: HandlerClient): IHandle {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handle(update: Update, requestId: UUID) {
        val text = TextDto(update.message.text, update.message.chatId, requestId)
        logger.info(text.toString())
        GlobalScope.launch {
            client.sendText(text)
        }
    }

}