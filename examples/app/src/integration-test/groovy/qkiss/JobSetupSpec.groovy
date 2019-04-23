package qkiss

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration

@Integration
@Rollback
class JobSetupSpec extends spock.lang.Specification {

    void "verify jobs run from config"() {
        expect:
        Org.countByName("springBeanJobFromConfig") == 1
        Org.countByName("ClosureJob") == 1
    }
}
