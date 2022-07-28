package com.example.demko_testy.item.domain.query

import com.example.demko_testy.item.domain.Item
import kotlinx.coroutines.*
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

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

//        val result = webClient.get()
//            .uri(URI.create("http://localhost"))
//            .awaitExchange {
//                it.bodyToMono(String::class.java)
//            }

        val result = coroutineScope {

            val first = async { firstCall() }
            val second = async { secondCall() }
            val third = async { thirdCall() }

            CallResult(
                first = first.await(),
                second = second.await(),
                third = third.await()
            )
        }


        println(result)
        return findItemByIdRepository.findById(id)
    }

}

suspend fun firstCall(): Int {
    delay(3000)
    println("1")
    return 1
}

suspend fun secondCall(): Int {
    delay(2000)
    println("2")
    return 2
}

suspend fun thirdCall(): Int {
    delay(1000)
    println("3")
    return 3
}
