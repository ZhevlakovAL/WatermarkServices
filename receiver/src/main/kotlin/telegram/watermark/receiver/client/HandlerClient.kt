package telegram.watermark.receiver.client

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import telegram.watermark.handler.dto.TextDto


@Component
class HandlerClient(private val client: WebClient) {

    suspend fun sendText(text: TextDto) {
        client
            .post()
            .uri("/text")
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(text))
            .retrieve()
            .bodyToMono(String.Companion::class.java)
            .awaitSingleOrNull()
    }
}