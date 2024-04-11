package telegram.watermark.receiver.handler

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.UUID

class DocumentHandler: IHandle {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handle(update: Update, requestId: UUID) {
        logger.info("chatId ${update.message.chatId} requestId ${requestId} file")
    }
}