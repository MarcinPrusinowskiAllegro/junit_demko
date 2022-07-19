package com.example.demko_testy.item.domain

data class Item(
    val id: Int
)


data class FirstEmbeddedChild(
    val id: Int,
    val name: String
)

data class SecondEmbeddedChild(
    val id: Int,
    val name: String
)

data class HugeDataClassWithEmbeddedObjects(
    val id: Int,
    val firstEmbeddedChild: FirstEmbeddedChild,
    val secondEmbeddedChild: SecondEmbeddedChild
)