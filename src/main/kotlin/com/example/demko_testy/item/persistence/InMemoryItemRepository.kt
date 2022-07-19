package com.example.demko_testy.item.persistence

import com.example.demko_testy.item.domain.Item
import com.example.demko_testy.item.domain.command.InsertItemRepository
import com.example.demko_testy.item.domain.query.FindItemByIdRepository
import com.example.demko_testy.item.persistence.ItemDocumentMapper.mapToItem
import org.springframework.stereotype.Component
import java.util.*

object ItemDocumentMapper {
    fun mapFromItem(item: Item): ItemDocument = ItemDocument(
        id = item.id
    )

    fun mapToItem(itemDocument: ItemDocument) = Item(
        id = itemDocument.id
    )

}

data class ItemDocument(
    val id: Int
)

@Component
class InMemoryItemRepository : FindItemByIdRepository, InsertItemRepository {

    private val data = mutableMapOf(
        1 to ItemDocument(1)
    )

    override fun insert(item: Item) {
        val documentToBeInserted = ItemDocumentMapper.mapFromItem(item)

        data[documentToBeInserted.id] = documentToBeInserted
    }

    override fun findById(id: Int): Item? {
        return data[id]?.let(::mapToItem) ?: throw ItemNotFoundException(id)
    }
}

data class ItemNotFoundException(val id: Int) : RuntimeException()