package telegram.watermark.sender.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.meta.generics.TelegramClient

@Configuration
class Config {

    @Value("\${telegram.bot.token}")
    lateinit var telegramBotToken: String

    @Bean
    fun telegramClient(): TelegramClient = OkHttpTelegramClient(telegramBotToken)
}