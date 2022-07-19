package com.example.demko_testy.mocking

import com.example.demko_testy.item.domain.Item
import com.example.demko_testy.item.domain.query.FindItemByIdQuery
import com.example.demko_testy.item.domain.query.FindItemByIdRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class FindItemByQueryTest {

    @Test
    fun `mock interface`() = runBlocking {
        val findItemByIdRepositoryMock = mockk<FindItemByIdRepository>()
        val findItemByIdQuery = FindItemByIdQuery(
            findItemByIdRepository = findItemByIdRepositoryMock
        )
        every { findItemByIdRepositoryMock.findById(1) } returns Item(1)

        val result = findItemByIdQuery.execute(1)

        Assertions.assertEquals(
            1,
            result!!.id
        )
    }

    @Test
    fun `mock final class`() = runBlocking {
        val findItemByIdQuery: FindItemByIdQuery = mockk()
        coEvery { findItemByIdQuery.execute(1) } returns Item(1)

        val result = findItemByIdQuery.execute(1)

        Assertions.assertEquals(
            1,
            result!!.id
        )
    }
}