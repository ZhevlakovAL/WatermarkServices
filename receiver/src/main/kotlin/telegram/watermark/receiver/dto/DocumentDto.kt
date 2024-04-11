package telegram.watermark.handler.dto

import java.util.UUID

data class DocumentDto(val fileId: String, val chatId: Long, val requestId: UUID)