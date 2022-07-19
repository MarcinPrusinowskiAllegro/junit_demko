package com.example.demko_testy.item.domain.query

import com.example.demko_testy.item.domain.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitExchange
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI

interface FindItemByIdRepository {
    fun findById(id: Int): Item?
}

data class CallResult(
    val first: Int,
    val second: Int,
    val third: Int,
)

@Component
class FindItemByIdQuery(
    private val findItemByIdRepository: FindItemByIdRepository,
) {

    private val webClient: WebClient = WebClient.create()

    suspend fun execute(id: Int): Item? {

        val result = webClient.get()
            .uri(URI.create("http://localhost"))
            .awaitExchange {
                it.bodyToMono(String::class.java)
            }

        CallResult(
            first = firstCall(),
            second = secondCall(),
            third = thirdCall()
        )

        return findItemByIdRepository.findById(id)
    }

}


suspend fun firstCall(): Int {
    delay(1000)
    return 1
}

suspend fun secondCall(): Int {
    delay(1000)
    return 2
}

suspend fun thirdCall(): Int {
    delay(1000)
    return 3
}