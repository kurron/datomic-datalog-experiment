package org.kurron.datalog

import spock.lang.Specification
import static datomic.Peer.q

/**
 * Learning test to see how Datalog can be used to query the System properties object (a map).
 */
class SystemPropertiesLearningTest extends Specification {

    def 'try various queries'( def query, def input )
    {
        given: 'a query and some input'
        assert query != null
        assert input != null

        when: 'the query is applied'
        def results = q( query, input )

        then: 'the results are printed'
        println "results = $results"
        true

        where:
        query                         | input
        // find all properties
        '[:find ?k :in [[?k]]]'       | System.properties
    }
}
