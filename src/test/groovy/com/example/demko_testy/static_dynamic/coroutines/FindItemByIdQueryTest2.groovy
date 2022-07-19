package com.example.demko_testy.static_dynamic.coroutines

import spock.lang.Specification

import static com.example.demko_testy.item.domain.query.FindItemByIdQueryKt.firstCall


class FindItemByIdQueryTest2 extends Specification {

    def "test coroutine"() {
        // continuation
        when:
        def result = firstCall()

        then:
        result == 1
    }

}
