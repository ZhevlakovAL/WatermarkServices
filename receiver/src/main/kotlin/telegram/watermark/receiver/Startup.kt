package telegram.watermark.receiver
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import telegram.watermark.receiver.client.HandlerClient


@Component
class Startup(private val handlerClient: HandlerClient) : ApplicationRunner {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${telegram.bot.token}")
    lateinit var telegramBotToken: String

    override fun run(args: ApplicationArguments?) {
        logger.info("!!!!!!!!!!!! Receiver starting !!!!!!!!!!!!!!!!")
        try {
            val botsApplication = TelegramBotsLongPollingApplication()
            //TODO: Вынести telegram_id в переменные окрыжения
            botsApplication.registerBot(telegramBotToken, Consumer(handlerClient))
        } catch (e: TelegramApiException) {
            logger.error(e.message, e)
        }
    }
}