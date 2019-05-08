import org.springframework.context.annotation.Bean

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import qkiss.TestBean

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    TestBean testBean() {
        return new TestBean()
    }
}
