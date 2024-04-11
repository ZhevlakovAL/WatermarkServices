package telegram.watermark.receiver.handler

import org.telegram.telegrambots.meta.api.objects.Update
import java.util.UUID

interface IHandle {
    fun handle(update: Update, requestId: UUID)
}