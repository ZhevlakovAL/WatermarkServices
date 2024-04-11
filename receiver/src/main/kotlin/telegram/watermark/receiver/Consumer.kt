package telegram.watermark.receiver

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.objects.Update
import telegram.watermark.receiver.client.HandlerClient
import telegram.watermark.receiver.handler.TextHandler
import telegram.watermark.receiver.handler.DocumentHandler
import telegram.watermark.receiver.handler.PhotoHandler
import telegram.watermark.receiver.handler.VideoHandler
import java.util.UUID

class Consumer(private val handlerClient: HandlerClient) : LongPollingSingleThreadUpdateConsumer {

    private val textHandler: TextHandler = TextHandler(handlerClient)
    private val documentHandler: DocumentHandler = DocumentHandler()
    private val photoHandler: PhotoHandler = PhotoHandler()
    private val videoHandler: VideoHandler = VideoHandler()

    override fun consume(update: Update?) {
        if (update == null || !update.hasMessage()) {
            return
        }
        val requestId = UUID.randomUUID()
        if (update.message.hasText()) {
            textHandler.handle(update, requestId)
        } else if (update.message.hasPhoto()) {
            photoHandler.handle(update, requestId)
        } else if (update.message.hasVideo()) {
            videoHandler.handle(update, requestId)
        } else if (update.message.hasDocument()) {
            documentHandler.handle(update, requestId)
        }
    }
}

