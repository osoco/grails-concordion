package es.osoco.grails.plugins.concordion.runner


import org.junit.runner.Runner



import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.junit4.runner.GrailsTestCaseRunnerBuilder
import org.codehaus.groovy.grails.test.support.GrailsTestMode
import org.springframework.context.ApplicationContext


class ConcordionGrailsRunnerBuilder extends GrailsTestCaseRunnerBuilder {

    ConcordionGrailsRunnerBuilder(GrailsTestTargetPattern[] testTargetPatterns) {
        super(testTargetPatterns)
    }

    ConcordionGrailsRunnerBuilder(GrailsTestMode mode, ApplicationContext appCtx, GrailsTestTargetPattern[] testTargetPatterns) {
        super(mode, appCtx, testTargetPatterns)
    }

    @Override
    Runner runnerForClass(Class testClass) {
        if (mode) {
            new ConcordionGrailsRunner(testClass, mode, appCtx, *testTargetPatterns)
        } else {
            new ConcordionGrailsRunner(testClass, *testTargetPatterns)
        }
    }

}