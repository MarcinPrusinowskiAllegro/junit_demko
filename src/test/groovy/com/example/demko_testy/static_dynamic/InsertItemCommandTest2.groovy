package com.example.demko_testy.static_dynamic

import com.example.demko_testy.item.domain.command.InsertItemCommand
import com.example.demko_testy.item.domain.command.InsertItemRequest
import com.example.demko_testy.item.domain.query.FindItemByIdQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InsertItemCommandTest2 extends Specification {

    @Autowired
    private InsertItemCommand insertItemCommand

    @Autowired
    private FindItemByIdQuery findItemByIdQuery

    def "positive test"() {
        given:
        def insertItemRequest = new InsertItemRequest(2)

        when:
        insertItemCommand.execute(insertItemRequest)

        then:
        def insertedItem = findItemByIdQuery.execute(insertItemRequest.id)
        insertedItem.id == 2
    }

    def "negative test"() {
        given:
        def insertItemRequest = new InsertItemRequest(3)

        when:
        insertItemCommand.execute(insertItemRequest)

        then:
        def insertedItem = findItemByIdQuery.execute(insertItemRequest.id)
        insertedItem.id == 2
    }

    @Unroll
    def "positive parametrized"() {
        given:
        def insertItemRequest = new InsertItemRequest(3)

        when:
        insertItemCommand.execute(insertItemRequest)

        then:
        def insertedItem = findItemByIdQuery.execute(insertItemRequest.id)
        insertedItem.id == result

        where:
        id | result
        10 | 10
        11 | 11
        12 | 12
    }

    @Unroll
    def "negative parametrized"() {
        given:
        def insertItemRequest = new InsertItemRequest(3)

        when:
        insertItemCommand.execute(insertItemRequest)

        then:
        def insertedItem = findItemByIdQuery.execute(insertItemRequest.id)
        insertedItem.id == result

        where:
        id | result
        10 | 10
        11 | 0
        12 | 12
    }
}
