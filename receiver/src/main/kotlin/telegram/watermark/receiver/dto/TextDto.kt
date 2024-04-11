package telegram.watermark.handler.dto

import java.util.UUID

data class TextDto(val text: String, val chatId: Long, val requestId: UUID)