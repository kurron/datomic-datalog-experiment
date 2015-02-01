package org.kurron.datalog

import spock.lang.Specification
import static datomic.Peer.q

/**
 * Learning test to see how Datalog can be used to query the System properties object (a map).
 */
class SystemPropertiesLearningTest extends Specification {

    def 'try various queries'( String query, Properties input )
    {
        given: 'a query and some input'
        assert query != null
        assert input != null

        when: 'the query is applied'
        def results = q( query, input )

        then: 'the results are printed'
        results.each {
            println it
        }

        where:
        query                                      | input
        '[:find ?key :in [[?key]]]'                | System.properties
        '[:find ?key ?value :in [[?key ?value]] ]' | System.properties
    }

    def 'Which system properties are path-related?'()
    {
        given: 'a query and some input'
        def query = '''[:find ?key ?value
                        :in [[?key ?value]]
                        :where [(.endsWith ?key "path")]]'''
        def input = System.properties

        when: 'the query is applied'
        def results = q( query, input )

        then: 'the results are printed'
        results.each {
            println it
        }
    }

    def 'What path elements are mentioned in system properties?'()
    {
        given: 'a query and some input'
        def query = '''[:find ?pathElem
                        :in [[?key ?value]]
                        :where [(.endsWith ?key "path")]
                               [(.split ?value ":") [?pathElem ...]]]'''
        def input = System.properties

        when: 'the query is applied'
        def results = q( query, input )

        then: 'the results are printed'
        results.each {
            println it
        }
    }

    def 'What JAR files are in my system property paths?'()
    {
        given: 'a query and some input'
        def query = '''[:find ?pathElem
                        :in [[?key ?value]]
                        :where [(.endsWith ?key "path")]
                               [(.split ?value ":") [?pathElem ...]]
                               [(.endsWith ?pathElem ".jar")]]'''
        def input = System.properties

        when: 'the query is applied'
        def results = q( query, input )

        then: 'the results are printed'
        results.each {
            println it
        }
    }
}
