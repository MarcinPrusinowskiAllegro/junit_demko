package com.example.demko_testy.static_dynamic

import com.example.demko_testy.item.domain.FirstEmbeddedChild
import com.example.demko_testy.item.domain.HugeDataClassWithEmbeddedObjects
import com.example.demko_testy.item.domain.Item
import com.example.demko_testy.item.domain.SecondEmbeddedChild

fun sampleItem(
    id: Int = 1,
) = Item(
    id = id
)

fun sampleFirstEmbeddedChild(
    id: Int = 1,
    name: String = "test-name",
) = FirstEmbeddedChild(
    id = id,
    name = name
)

fun sampleSecondEmbeddedChild(
    id: Int = 2,
    name: String = "test-name",
) = SecondEmbeddedChild(
    id = id,
    name = name
)

fun sampleHugeDataClassWithEmbeddedObjects(
    id: Int = 1,
    firstEmbeddedChild: FirstEmbeddedChild = sampleFirstEmbeddedChild(),
    secondEmbeddedChild: SecondEmbeddedChild = sampleSecondEmbeddedChild()
) = HugeDataClassWithEmbeddedObjects(
    id = id,
    firstEmbeddedChild = firstEmbeddedChild,
    secondEmbeddedChild = secondEmbeddedChild
)