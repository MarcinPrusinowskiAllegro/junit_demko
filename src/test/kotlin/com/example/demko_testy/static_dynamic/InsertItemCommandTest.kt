package com.example.demko_testy.static_dynamic

import com.example.demko_testy.item.domain.command.InsertItemCommand
import com.example.demko_testy.item.domain.command.InsertItemRequest
import com.example.demko_testy.item.domain.query.FindItemByIdQuery
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class InsertItemCommandTest(
    @Autowired private val insertItemCommand: InsertItemCommand,
    @Autowired private val findItemByIdQuery: FindItemByIdQuery,
) {

    @Test
    fun `sample test`() {
        val hugeDataClass = sampleHugeDataClassWithEmbeddedObjects(
            id = 1,
            firstEmbeddedChild = sampleFirstEmbeddedChild(
                id = 1,
                name = "test-name"
            ),
            secondEmbeddedChild = sampleSecondEmbeddedChild(
                id = 2,
                name = "test-name"
            )
        )

        Assertions.assertEquals(
            hugeDataClass,
            sampleHugeDataClassWithEmbeddedObjects()
        )
    }

    @Test
    fun `positive test`() = runBlocking {
        val insertItemRequest = InsertItemRequest(
            id = 2
        )

        insertItemCommand.execute(
            insertItemRequest
        )

        val insertedItem = findItemByIdQuery.execute(
            insertItemRequest.id
        )

        Assertions.assertEquals(
            2, insertedItem!!.id
        )
    }


    @Test
    fun `negative test`() = runBlocking {
        val insertItemRequest = InsertItemRequest(
            id = 3
        )

        insertItemCommand.execute(
            insertItemRequest
        )

        val insertedItem = findItemByIdQuery.execute(
            insertItemRequest.id
        )

        Assertions.assertEquals(
            2, insertedItem!!.id
        )
    }

    @ParameterizedTest
    @CsvSource(
        "11, 11",
        "12, 12",
        "13, 13"
    )
    fun `positive parametrized`(
        input: Int, expected: Int
    ) = runBlocking {

        val insertItemRequest = InsertItemRequest(
            id = input
        )

        insertItemCommand.execute(
            insertItemRequest
        )

        val insertedItem = findItemByIdQuery.execute(
            insertItemRequest.id
        )

        Assertions.assertEquals(
            expected, insertedItem!!.id
        )
    }

    @ParameterizedTest
    @CsvSource(
        "11, 11",
        "12, 0",
        "13, 13",
    )
    fun `negative parametrized`(
        input: Int, expected: Int
    ) = runBlocking {

        val insertItemRequest = InsertItemRequest(
            id = input
        )

        insertItemCommand.execute(
            insertItemRequest
        )

        val insertedItem = findItemByIdQuery.execute(
            insertItemRequest.id
        )

        Assertions.assertEquals(
            expected, insertedItem!!.id
        )
    }


    @ParameterizedTest
    @MethodSource("squares")
    fun `params from method`(input: Int, expected: Int) {
        Assertions.assertEquals(expected, input * input)
    }

    companion object {
        @JvmStatic
        fun squares() = listOf(
            Arguments.of(1, 1),
            Arguments.of(2, 4)
        )
    }

}