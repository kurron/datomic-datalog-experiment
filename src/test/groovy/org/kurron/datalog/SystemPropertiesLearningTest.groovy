package org.kurron.datalog

import spock.lang.Specification

/**
 * Learning test to see how Datalog can be used to query the System properties object (a map).
 */
class SystemPropertiesLearningTest extends Specification {

    def 'bob'( def builder, def unimportant )
    {
        given: 'a JSON serializer'

        when: 'an object is serialized into JSON'

        then: 'printout JSON'
        true

        where:
        builder                         |  unimportant
        'bob'                           | 'bob'
    }
}
