package com.example.demko_testy.item.controller

import com.example.demko_testy.item.domain.command.InsertItemCommand
import com.example.demko_testy.item.domain.command.InsertItemRequest
import com.example.demko_testy.item.domain.query.FindItemByIdQuery
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(
    private val insertItemCommand: InsertItemCommand,
    private val findItemByIdQuery: FindItemByIdQuery,
) {

    @GetMapping("/items/{id}")
    suspend fun fetchAllItems(@PathVariable id: Int) {
        findItemByIdQuery.execute(id)
    }

    @PostMapping("/items")
    fun insertItem(@RequestBody insertItemRequest: InsertItemRequest) {
        insertItemCommand.execute(
            insertItemRequest
        )
    }

}