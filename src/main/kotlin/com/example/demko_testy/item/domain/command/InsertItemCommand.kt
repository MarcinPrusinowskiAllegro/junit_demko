package com.example.demko_testy.item.domain.command

import com.example.demko_testy.item.domain.Item
import org.springframework.stereotype.Component

interface InsertItemRepository {
    fun insert(item: Item)
}

data class InsertItemRequest(
    val id: Int
)

@Component
class InsertItemCommand(
    private val insertItemRepository: InsertItemRepository,
) {
    fun execute(insertItemRequest: InsertItemRequest){
        val itemToBeInserted = Item(
            id = insertItemRequest.id
        )

        insertItemRepository.insert(
            itemToBeInserted
        )
    }
}

